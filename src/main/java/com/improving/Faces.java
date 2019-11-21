package com.improving;

public enum Faces {
    Zero(1, 0),
    One(1, 1),
    Two(1,2),
    Three(1,3),
    Four(1,4),
    Five(1,5),
    Six(1,6),
    Seven(1,7),
    Eight(1,8),
    Nine(1,9),
    Reverse(0,10),
    DrawTwo(0,11),
    Skip(0,12),
    DrawFour(-1,13),
    SpinColor(-1,14);

    public final int value;

    public final int pointValue;

    Faces(int value, int pointValue) {
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
