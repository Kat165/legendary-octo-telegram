package com.company;

import java.awt.*;

public class Light implements XmasShape{
    int x;
    int x2;
    int y;
    int y2;
    Color color;


    Light(int x,int x2, int y,int y2, Color color){
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }


    @Override
    public void transform(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(6f));
        g2d.drawLine(x,y,x2,y2);
        g2d.setColor(color);
        g2d.fillOval(x+(x2-x)/5,y+(y2-y)/5,10,10);
        g2d.fillOval(x+2*(x2-x)/5,y+2*(y2-y)/5,10,10);
        g2d.fillOval(x+3*(x2-x)/5,y+3*(y2-y)/5,10,10);
        g2d.fillOval(x+4*(x2-x)/5,y+4*(y2-y)/5,10,10);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.translate(x,y);
    }
}
