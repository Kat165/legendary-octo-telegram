package com.company;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
	// write your code here
/*
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());

        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x,3))
                .add(-2,new Power(x,2))
                .add(-1,x)
                .add(2);
        System.out.println(exp.toString());
        for(double v=-5;v<5;v+=0.1){
            x.setValue(v);
            System.out.printf(Locale.US,"f(%f)=%f\n",v,exp.evaluate());
        }
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

        double xv = 100*(Math.random()-.5);
        double yv = 100*(Math.random()-.5);
        x.setValue(xv);
        y.setValue(yv);
        double fv = circle.evaluate();
        System.out.print(String.format("Punkt (%f,%f) leży %s koła %s",xv,yv,(fv<0?"wewnątrz":"na zewnątrz"),circle.toString()));


        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());
*/
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7)
                .add(new Cosinus(x));
        System.out.println(exp.toString());
        Node dy = exp.diff(x);
        System.out.println(dy.toString());
    }
}
