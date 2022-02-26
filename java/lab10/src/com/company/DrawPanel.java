package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    //public void paintComponent(Graphics g){
    //    g.setFont(new Font("Helvetica", Font.BOLD, 18));
    //    g.drawString("Hello World", 20, 20);
    //    System.out.println("painting");
//
    //}

    List<XmasShape> shapes = new ArrayList<>();
    public static final Color VERY_LIGHT_RED = new Color(255,102,102);
    public static final Color VERY_DARK_GREEN = new Color(0,102,0);
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);
    public static final Color Dark_blue = new Color(0,0,204);
    public static final Color PURPLE = new Color(102,0,204);
    public static final Color VERY_DARK_RED = new Color(102,0,15);
    public static final Color BROWN = new Color(78, 33, 1);
    public static final Color SILVER = new Color(131, 161, 158);
    public static final Color LIGHT = new Color(206, 197, 38, 124);
    public static final Color NIGHT = new Color(23, 5, 89);
    public static final Color RED_LIGHT = new Color(218, 9, 27, 208);


    DrawPanel() {

        Trunk trunk = new Trunk(50,50,BROWN,227,250);
        shapes.add(trunk);

        Branch branch = new Branch(320,340,1,VERY_DARK_GREEN);
        shapes.add(branch);

        Branch branch1 = new Branch(350,260,0.8,VERY_DARK_GREEN);
        shapes.add(branch1);

        Branch branch2 = new Branch(384,190,0.6,VERY_DARK_GREEN);
        shapes.add(branch2);

        Light light = new Light(445,560,230,280,RED_LIGHT);
        shapes.add(light);

        Light light1 = new Light(430,590,310,380,RED_LIGHT);
        shapes.add(light1);

        Light light2 = new Light(410,610,415,480,RED_LIGHT);
        shapes.add(light2);

        Bubble bubble = new Bubble(400,270, 0.3,VERY_LIGHT_RED,PURPLE);
        shapes.add(bubble);

        Bubble bubble1 = new Bubble(500,290, 0.32,VERY_LIGHT_BLUE,Dark_blue);
        shapes.add(bubble1);

        Bubble bubble2 = new Bubble(450,330, 0.35,VERY_LIGHT_RED,VERY_DARK_RED);
        shapes.add(bubble2);

        Bubble bubble3 = new Bubble(450,430, 0.2,Color.white,SILVER);
        shapes.add(bubble3);

        Bubble bubble4 = new Bubble(500,420, 0.3,VERY_LIGHT_RED,PURPLE);
        shapes.add(bubble4);

        Bubble bubble5 = new Bubble(550,470, 0.2,Color.white,SILVER);
        shapes.add(bubble5);

        Bubble bubble6 = new Bubble(450,240, 0.2,Color.white,SILVER);
        shapes.add(bubble6);

        Bubble bubble7 = new Bubble(520,340, 0.2,Color.white,SILVER);
        shapes.add(bubble7);

        Bubble bubble8 = new Bubble(580,500, 0.35,VERY_LIGHT_RED,VERY_DARK_RED);
        shapes.add(bubble8);

        Bubble bubble9 = new Bubble(370,480, 0.32,VERY_LIGHT_BLUE,Dark_blue);
        shapes.add(bubble9);

        Star star = new Star(260,70,Color.yellow,0.85);
        shapes.add(star);

        Star star1 = new Star(100,100,LIGHT,0.1);
        shapes.add(star1);

        Star star2 = new Star(150,400,LIGHT,0.1);
        shapes.add(star2);

        Star star3 = new Star(200,300,LIGHT,0.1);
        shapes.add(star3);

        Star star4 = new Star(110,360,LIGHT,0.1);
        shapes.add(star4);

        Star star5 = new Star(140,440,LIGHT,0.1);
        shapes.add(star5);

        Star star6 = new Star(220,90,LIGHT,0.1);
        shapes.add(star6);

        Star star7 = new Star(600,200,LIGHT,0.1);
        shapes.add(star7);

        Star star8 = new Star(660,70,Color.yellow,0.85);
        shapes.add(star8);

        Star star9 = new Star(600,100,LIGHT,0.1);
        shapes.add(star9);

        Star star10 = new Star(650,420,LIGHT,0.1);
        shapes.add(star10);

        Star star11 = new Star(700,300,LIGHT,0.1);
        shapes.add(star11);

        Star star12 = new Star(710,370,LIGHT,0.1);
        shapes.add(star12);

        Star star13 = new Star(640,445,LIGHT,0.1);
        shapes.add(star13);

        Star star14 = new Star(720,50,LIGHT,0.1);
        shapes.add(star14);

        Star star15 = new Star(610,250,LIGHT,0.1);
        shapes.add(star15);

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }





}
