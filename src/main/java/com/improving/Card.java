package com.improving;

public class Card {
private Faces faces;
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

//    public void setColors(Colors colors) {
//        this.colors = colors;
//    }
//
//    public void setFaces(Faces faces) {
//        this.faces = faces;
//}

    @Override
public String toString() {
        return" " + colors.toString() + " of " + faces.toString();

}
}
