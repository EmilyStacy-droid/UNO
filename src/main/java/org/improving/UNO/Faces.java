package org.improving.UNO;

public enum Faces {
    Zero(1),
    One(1),
    Two(1),
    Three(1),
    Four(1),
    Five(1),
    Six(1),
    Seven(1),
    Eight(1),
    Nine(1),
    Reverse(0),
    DrawTwo(0),
    Skip(0),
    DrawFour(-1),
    SpinColor(-1);

    public final int value;
    Faces(int value) {
        this.value = value;
    }


    public int getintValue() {
        return value;
    }
}
