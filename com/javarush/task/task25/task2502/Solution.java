package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            if (loadWheelNamesFromDB().length != 4) {
                throw new IllegalArgumentException();
            }
            else {
                wheels = new ArrayList<>();
                Wheel[] ourWheels = Wheel.values();
              //  for (Wheel w : ourWheels) System.out.println(w);
                for (int i = 0; i < loadWheelNamesFromDB().length; i++) {
                    for (int j = 0; j < ourWheels.length; j++) {
                        if (loadWheelNamesFromDB()[i].equals(ourWheels[j].toString())) {
                            wheels.add(ourWheels[j]);
                            break;
                        } else {
                            if (j == (ourWheels.length-1)) throw new IllegalArgumentException();
                        }

                    }
                }
            }
            System.out.println(wheels);
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}
