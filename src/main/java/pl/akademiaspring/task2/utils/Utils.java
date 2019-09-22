package pl.akademiaspring.task2.utils;

import java.util.Random;

public class Utils {

    public static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }
}
