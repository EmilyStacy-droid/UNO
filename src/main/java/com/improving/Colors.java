package com.improving;

public enum Colors {
    Red(1,1),
    Green(1,2),
    Yellow(1,3),
    Blue(1,4),
    Wild(0,5);

    public final int value;
    public final int pointValue;

    Colors(int value, int pointValue) {

        this.value = value;
        this.pointValue = pointValue;
    }


    public int getintValue() {

        return value;
    }

    public int getPointValue() {
        return pointValue;
    }
}
