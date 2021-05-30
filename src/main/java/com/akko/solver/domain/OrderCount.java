package com.akko.solver.domain;

public class OrderCount {
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        OrderCount.count = count;
    }
}
