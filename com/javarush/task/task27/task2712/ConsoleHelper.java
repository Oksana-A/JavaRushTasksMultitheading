package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        return bufReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage("Select a dish: " + Dish.allDishesToString());
        List<Dish> dishes = new ArrayList<>();

        String str;
        Dish[] allDishes = Dish.values();
        while (!(str = readString()).equals("exit")) {
            Dish chosenDish = null;
            for (Dish d: allDishes) {
                if (d.toString().equalsIgnoreCase(str)) {
                    chosenDish = d;
                    break;
                }
            }
            if ( chosenDish != null)
                dishes.add(chosenDish);
            else
                writeMessage("There is not such dishes");
        }
        return dishes;
    }
}
