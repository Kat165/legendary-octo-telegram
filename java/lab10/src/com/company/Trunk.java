package com.company;

import java.awt.*;

public class Trunk implements XmasShape{
    int width;
    int height;
    int x;
    int y;
    Color color;

    public static final Color NIGHT = new Color(23, 5, 89);
    public static final Color SNOW = new Color(204, 238, 238);

    Trunk (int width, int height, Color color, int x, int y){
        this.width = width;
        this.height = height;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(NIGHT);
        g2d.fillRect(-x,-y,1000,700);
        g2d.setColor(SNOW);
        g2d.fillRect(-x,250,1000,250);
        g2d.setColor(color);
        g2d.fillRect(x,y,width,height);
    }
}
