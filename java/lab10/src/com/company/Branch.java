package com.company;

import java.awt.*;

public class Branch implements XmasShape{
    int x;
    int y;
    double scale;
    Color color;

    public static final int BASE_WIDTH = 320;
    public static final int BASE_HEIGHT = 170;

    private static final int[] X_POINTS = new int[] {0, BASE_WIDTH / 2, BASE_WIDTH};
    private static final int[] Y_POINTS = new int[] {BASE_HEIGHT, 0, BASE_HEIGHT};

    Branch(int x, int y, double scale, Color color)
    {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillPolygon(X_POINTS, Y_POINTS, X_POINTS.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}
