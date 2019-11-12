package org.improving.UNO;

public class Card {
private final Faces faces;
private Colors colors;

    public Card(Faces faces, Colors colors) {
        this.faces = faces;
        this.colors = colors;
    }

    public Faces getFaces() {
        return faces;
    }

    public Colors getColors() {
        return colors;

    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    @Override
public String toString() {
        return" " + colors.toString() + " of " + faces.toString();

}
}
