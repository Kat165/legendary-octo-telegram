package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel
{
    private ClockThread thread;
    private LocalTime time = LocalTime.now();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public ClockWithGui() {
        super();
        thread = new ClockThread();
        thread.start();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());

        int numberDistance = (int) (270 * 0.43);
        int hourHandLength = (int) (270 * 0.3);
        int secondHandLength = (int) (270 * 0.4);
        int minuteHandLength = (int) (270 * 0.35);

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(getWidth() / 2, getHeight() / 2);
        Point2D tmp = new Point2D.Float(0, -numberDistance);

        AffineTransform saveAT = g2d.getTransform();

        double minuteMove = time.getMinute() * 2 * Math.PI / 12 / 60;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.rotate(time.getHour() % 12 * 2 * Math.PI / 12 + minuteMove);
        g2d.drawLine(0, 0, 0, -hourHandLength);
        g2d.setTransform(saveAT);

        g2d.setColor(Color.BLACK);
        g2d.rotate(time.getMinute() * 2 * Math.PI / 60);
        g2d.drawLine(0, 0, 0, -minuteHandLength);
        g2d.setTransform(saveAT);

        g2d.setColor(Color.BLACK);
        g2d.rotate(time.getSecond() * 2 * Math.PI / 60);
        g2d.drawLine(0, 0, 0, -secondHandLength);
        g2d.setTransform(saveAT);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(-270 / 2, -270 / 2, 270, 270);

        for (int i = 1; i < 61; i++) {
            boolean hour = i % 5 == 0;
            g2d.rotate(2 * Math.PI / 60 * i);
            g2d.drawLine(0, -270 / 2, 0, -270 / 2 + (hour ? 10 : 5));
            g2d.setTransform(saveAT);
        }

        g2d.setColor(Color.BLACK);
        for (int i = 1; i < 13; i++) {
            AffineTransform aff = new AffineTransform();
            aff.rotate(2 * Math.PI / 12 * i);
            Point2D tar = new Point2D.Float();
            aff.transform(tmp, tar);

            String text = Integer.toString(i);
            int x = (int) (tar.getX() - g2d.getFontMetrics().stringWidth(text) / 2);
            int y = (int) (tar.getY() + g2d.getFontMetrics().getHeight() / 2 - 1);

            g2d.drawString(text, x, y);
        }

        //g.setColor(Color.darkGray);
        //g.fillRect(0, 0, getWidth(), getHeight());
        //Graphics2D g2d=(Graphics2D)g;
        //g2d.translate(getWidth()/2,getHeight()/2);
//
        //AffineTransform saveAT = g2d.getTransform();
        //g2d.setColor(Color.PINK);
//
        //for(int i=1;i<13;i++){
        //    AffineTransform at = new AffineTransform();
        //    at.rotate(2*Math.PI/12*i);
        //    Point2D src = new Point2D.Float(0,-120);
        //    Point2D trg = new Point2D.Float();
        //    at.transform(src,trg);
        //    g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());
        //}
//
        //g2d.drawOval(-270/2-10,-270/2-10,290,290);
//
        //for (int i = 1; i<61;++i){
        //    g2d.rotate(2 * Math.PI / 60 * i);
        //    g2d.drawLine(0, -270 / 2, 0, -270 / 2);
        //    g2d.setTransform(saveAT);
        //}
//
        //g2d.setColor(Color.cyan);
        //g2d.rotate(time.getHour() % 12 * 2 * Math.PI / 12 + time.getMinute() * 2 * Math.PI / 12 / 60);
        //g2d.drawLine(0, 0, 0, -(int) (270 * 0.3));
        //g2d.setTransform(saveAT);
//
        //g2d.setColor(Color.magenta);
        //g2d.rotate(time.getMinute() * 2 * Math.PI / 60);
        //g2d.drawLine(0, 0, 0, -(int) (270 * 0.35));
        //g2d.setTransform(saveAT);
//
        //g2d.setColor(Color.ORANGE);
        //g2d.rotate(time.getSecond() * 2 * Math.PI / 60);
        //g2d.drawLine(0, 0, 0, - (int) (270 * 0.4));
        //g2d.setTransform(saveAT);
    }

    class ClockThread extends Thread {
        @Override
        public void run() {
            while (true) {
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n", time.getHour(), time.getMinute(), time.getSecond());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}
