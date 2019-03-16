package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private static StatisticManager statisticManager;
    private StatisticStorage statisticStorage;

    private StatisticManager() {
        statisticStorage = new StatisticStorage();
    }

    public static StatisticManager getInstance() {
        if (statisticManager == null) {
            statisticManager = new StatisticManager();
        }
        return statisticManager;
    }

    public void register(EventDataRow data) {
        if (data != null)
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType et : EventType.values()) {
                storage.put(et, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }


    }
}
