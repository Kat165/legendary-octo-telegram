package com.company;

public class Cosinus extends Node{
    Node arg;
    Cosinus(Node a){
        arg = a;
    }
    @Override
    double evaluate() {
        double argVal = arg.evaluate();
        return 0;
    }

    @Override
    Node diff(Variable var) {
        Prod r  = new Prod(new Sinus(arg));
        r.sign =-1;
        return r;
    }

    @Override
    boolean isZero(Variable var) {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(sign<0)
            b.append("-");
        int argSign = arg.getSign();
        boolean useBracket = false;
        if(argSign<0 )useBracket = true;
        String argString = arg.toString();
        if(useBracket)b.append("(");
        b.append("cos(");
        b.append(argString);
        b.append(')');
        if(useBracket)b.append(")");

        return b.toString();
    }
}
