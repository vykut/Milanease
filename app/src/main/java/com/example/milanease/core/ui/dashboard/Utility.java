package com.example.milanease.core.ui.dashboard;

import android.graphics.Color;

import com.example.milanease.R;

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

    public int getColor() {
        switch (this) {
            case water: return R.color.utility_water;
            case gas: return R.color.utility_gas;
            default: return R.color.utility_electricity;
        }
    }

    public int getColorTransparent() {
        switch (this) {
            case water: return R.color.utility_water_transparent;
            case gas: return R.color.utility_gas_transparent;
            default: return R.color.utility_electricity_transparent;
        }
    }
}