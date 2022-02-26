package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        //oblicz sumę wartości zwróconych przez wywołanie evaluate skłądników sumy
        for (Node arg : args) {
            result += arg.evaluate();
        }
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}


    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            if (!n.isZero(var))
                r.add(n.diff(var));
        }
        return r;
    }

    @Override
    boolean isZero(Variable var) {
        return args.stream().allMatch(n->n.isZero(var));
    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        //zaimplementuj
        int cnt = getArgumentsCount();
        for (int i = 0; i<cnt; ++i)
        {
            if (i!=0)
            b.append(" + ");
            b.append(args.get(i));
        }

        if(sign<0)b.append(")");
        return b.toString();
    }


}
