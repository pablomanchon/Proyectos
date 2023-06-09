package Entidades;

import java.awt.Color;

public class ColorDistance {

    private Color color;
    private double distance;

    public ColorDistance(Color color, double distance) {
        this.color = color;
        this.distance = distance;
    }

    public Color getColor() {
        return color;
    }

    public double getDistance() {
        return distance;
    }
}
