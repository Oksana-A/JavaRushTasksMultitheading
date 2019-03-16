package com.javarush.task.task24.task2404;

import com.javarush.task.task24.task2404.HasHeight;
import com.javarush.task.task24.task2404.HasWidth;
import com.javarush.task.task24.task2404.Point;

/* 
Рефакторинг Rectangle
*/
public class Solution {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(1, 2, 3, 4);
//        System.out.println(getHeight(rectangle));
//        System.out.println(getWidth(rectangle));
        /////////////////////expected//////////////////
        System.out.println(getHeight(rectangle.castToHasHeight()));
        System.out.println(getWidth(rectangle.castToHasWidth()));
    }

    public static double getHeight(HasHeight rectangle) {
        return rectangle.getHeight();
    }

    public static double getWidth(HasWidth rectangle) {
        return rectangle.getWidth();
    }


    public static class Rectangle{
        private Point point1;
        private Point point2;

        public Rectangle(double x1, double y1, double x2, double y2) {
            point1 = new Point(x1, y1);
            point2 = new Point(x2, y2);
        }

        public HasHeight castToHasHeight() {
            return new HasHeight() {
                @Override
                public double getHeight() {
                    double height = point1.getY() - point2.getY();
                    if (height < 0) height = height * (-1.0);
                    return height;
                }
            };
        }

        public HasWidth castToHasWidth() {
            return new HasWidth() {
                @Override
                public double getWidth() {
                    double width = point1.getX() - point2.getX();
                    if (width < 0) width = width * (-1.0);
                    return width;
                }
            };
        }
    }
}
