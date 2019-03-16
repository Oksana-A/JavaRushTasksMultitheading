package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

//    public static void main(String[] args) {
//        AdvertisementManager advertisementManager = new AdvertisementManager(1200);
//        advertisementManager.processVideos();
//        //First Video is displaying... 50, 277
//    }

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty())
            throw  new NoVideoAvailableException();
        List<Advertisement> videosCanBeShown = storage.list().stream().
                filter(x -> x.getDuration() <= timeSeconds && x.getHits() > 0).
                sorted((a1, a2) -> Long.compare(a2.getAmountPerOneDisplaying(), a1.getAmountPerOneDisplaying())).
                collect(Collectors.toList());

        List<Advertisement> videosList = new ArrayList<>();
        int lastTime = timeSeconds;
        for (Advertisement adv: videosCanBeShown) {
            if (adv.getDuration() <= lastTime) {
                videosList.add(adv);
                lastTime -= adv.getDuration();
            }
        }

        long amount = 0;
        int duration = 0;
        for (Advertisement adv : videosList) {
            amount += adv.getAmountPerOneDisplaying();
            duration += adv.getDuration();
        }


//StatisticManager.getInstance().register(new VideoSelectedEventDataRow(listAdvToDisplaying, maxSum, maxTime));
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(videosList, amount,
                duration));
        for (Advertisement ad : videosList) {
            ConsoleHelper.writeMessage(ad.getName() + " is displaying... " +
                    ad.getAmountPerOneDisplaying() + ", " +
                    1000 * ad.getAmountPerOneDisplaying() / ad.getDuration());
            ad.revalidate();
        }
    }
}
