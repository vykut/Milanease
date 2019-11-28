package com.example.milanease.core.ui.dashboard;

import java.util.Random;

public enum Utility {

    water,
    electricity,
    gas;

    public static Utility random() {
        switch(new Random().nextInt() % 3) {
            case 0: return Utility.water;
            case 1: return Utility.gas;
            default: return Utility.electricity;
        }
    }
}