package org.improving.UNO;

public enum Colors {
    Red(1),
    Green(1),
    Yellow(1),
    Blue(1),
    Wild(0);

    public final int value;

    Colors(int value) {
        this.value = value;
    }


    public int getintValue() {
        return value;
    }


}
