package com.company;

import java.awt.*;

public class Star implements XmasShape{
    int x;
    int y;
    Color color;
    double scale;

    Star(int x, int y, Color color, double scale){
        this.x = x;
        this.y = y;
        this.color = color;
        this.scale = scale;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(6f));
        g2d.drawLine(x,y,x,y+100);
        g2d.drawLine(x-50,y+50,x+50,y+50);
        g2d.drawLine(x-35,y+85,x+35,y+15);
        g2d.drawLine(x-35,y+15,x+35,y+85);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
