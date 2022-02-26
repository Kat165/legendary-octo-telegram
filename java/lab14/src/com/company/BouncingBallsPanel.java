package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BouncingBallsPanel extends JPanel {

    List<Ball> balls = new ArrayList<>();
    private final AtomicBoolean paused = new AtomicBoolean(true);

    static class Ball{
        int x;
        int y;
        double vx;
        double vy;
        Color color;
        static final int BALL_SIZE=10;
        double speed=1;

        Ball(int x, int y, double vx, double vy, Color color){
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
        }
    }


    public void paint(Graphics g)
    {
        super.paint(g);
        for (Ball b : balls){
            g.setColor(b.color);
            g.fillOval( b.x - Ball.BALL_SIZE,
                    b.y - Ball.BALL_SIZE,
                    2* Ball.BALL_SIZE, 2* Ball.BALL_SIZE);
            g.setColor(Color.black);
            g.drawOval( b.x - Ball.BALL_SIZE,
                    b.y - Ball.BALL_SIZE,
                    2* Ball.BALL_SIZE, 2* Ball.BALL_SIZE);
        }
    }


    class AnimationThread extends Thread{
        public void run(){
            for(;;){
                //przesuń kulki
                //wykonaj odbicia od ściany
                //wywołaj repaint
                //uśpij
                moveBalls();
                detectCollision();
                ballCollision();
                repaint();
                try{
                    sleep(50);
                    synchronized (paused) {
                        if (paused.get()) {
                            paused.wait();
                        }
                    }
                }
                catch(InterruptedException e){}
            }
        }
    }

    void moveBalls(){
        for (Ball b: balls){
            b.x += b.speed * b.vx;
            b.y += b.speed * b.vy;
        }
        repaint();
    }

    void detectCollision()
    {
        Dimension d=getSize();
        for (Ball b: balls){
            if(b.x<Ball.BALL_SIZE){b.x=Ball.BALL_SIZE;b.vx*=-1;}
            if(b.x>d.width-Ball.BALL_SIZE){
                b.x=d.width-Ball.BALL_SIZE;b.vx*=-1;}
            if(b.y<Ball.BALL_SIZE){b.y=Ball.BALL_SIZE;b.vy*=-1;}
            if(b.y>d.height-Ball.BALL_SIZE){
                b.y=d.height-Ball.BALL_SIZE;b.vy*=-1;}

        }
        repaint();
    }

    private void ballCollision() {  //nietety coś nie za bardzo działa ani pierwszy ani drugi sposób
        for (Ball b1:balls)
        {
            for (Ball b2:balls){
                if (Math.sqrt((b1.x - b2.x)^2 + (b1.y - b2.y)^2)<=Ball.BALL_SIZE/2.f && b1!=b2){
                    double t = b1.vx;
                    b1.vx = b2.vx;
                    b2.vx = t;
                    t= b1.vy;
                    b1.vy = b2.vy;
                    b2.vy = t;
                    //System.out.println("zderzenie");
                }
            }
        }


        //for (Ball b1 : balls){
        //    Rectangle r1 = getBoundingRect(b1);
        //    for (Ball b2: balls){
        //        if (b1!=b2){
        //            Rectangle r2 = getBoundingRect(b2);
        //            if (rectcoll(r1,r2))
        //            {
        //                double t = b1.vx;
        //                b1.vx = b2.vx;
        //                b2.vx = t;
        //                t= b1.vy;
        //                b1.vy = b2.vy;
        //                b2.vy = t;
        //            }
        //        }
//
        //    }
        //}

    }

    boolean rectcoll(Rectangle r1, Rectangle r2){
        return r1.x < r2.x + r2.width &&
                r1.x + r1.width > r2.x &&
                r1.y < r2.y + r2.height &&
                r1.height + r1.y > r2.y;
    }

    public Rectangle getBoundingRect(Ball ball)
    {
        int ballHeight = (int) (Ball.BALL_SIZE * 0.80f);
        int ballWidth = (int) (Ball.BALL_SIZE * 0.80f);
        int x = ball.x - ballWidth / 2;
        int y = ball.y - ballHeight / 2;

        return new Rectangle(x,y,ballWidth,ballHeight);
    }



    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        new AnimationThread().start();
    }

    void onStart(){
        System.out.println("Start or resume animation thread");
        synchronized (paused) {
            paused.set(false);
            paused.notifyAll();
            System.out.println("Start or resume animation thread");
        }
    }

    void onStop(){
        System.out.println("Suspend animation thread");
        synchronized (paused) {
            paused.set(true);
            paused.notifyAll();
            System.out.println("Suspend animation thread");
        }

    }

    void onPlus(){
        System.out.println("Add a ball");
        double hue = Math.random();
        int rgb = Color.HSBtoRGB((float) hue,0.5f,0.5f);
        Color color = new Color(rgb);
        Ball ball = new Ball((int) (Math.random()*600),(int) (Math.random()*600),(int) (Math.random()*10),(int) (Math.random()*10),color);
        balls.add(ball);
        repaint();
    }

    void onMinus(){
        if (balls.isEmpty()) {
            return;
        }
        System.out.println("Remove a ball");
        balls.remove(balls.size()-1);
        repaint();
    }


}
