package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentSkipListMap;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    //Start cooking - Your order: [Soup, Juice, Water] of Tablet{number=5}, cooking time 23min
    @Override
    public void update(Observable o, Object order) {
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " +
                ((Order)order).getTotalCookingTime() + "min");
        this.setChanged();
        this.notifyObservers(order);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(((Order) order).getTablet().toString(), name,
                ((Order) order).getTotalCookingTime()*60, ((Order) order).getDishes()));
    }

    /*ConsoleHelper
.register
 this.setChanged();
 this.notifyObservers(order);
    }*/
}
