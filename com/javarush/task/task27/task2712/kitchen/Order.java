package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
        this.tablet = tablet;
    }


    @Override
    public String toString() {
        if (dishes.isEmpty())
            return "";
        else {
            StringBuilder strBuilder = new StringBuilder("Your order:[");
            for (Dish d : dishes) {
                strBuilder.append(d.toString() + ", ");
            }
            return strBuilder.substring(0, strBuilder.length() - 2).toString() + "] of " + tablet.toString();
        }
    }

    public int getTotalCookingTime() {
        int duration = 0;

        for (Dish d: dishes) {
            duration += d.getDuration();
        }
        return duration;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }
}
