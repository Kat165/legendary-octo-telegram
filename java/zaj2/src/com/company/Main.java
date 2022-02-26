package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //double[][] d = {{1,2},{3,4}};
        //Matrix matrix = new Matrix(d);
        //double[][]d1 = {{1,1},{1,1}};
        //Matrix matrix1 = new Matrix(d1);
        //Matrix matrix2 = matrix.dot(matrix1);
        //Matrix matrix3 = matrix.add(matrix1);
//
//
        //System.out.println(matrix.toString());
//
        //System.out.println(matrix2.toString());
//
        //System.out.println(matrix3.toString());
//
        //double x =matrix1.frobenius();
//
        //System.out.println(x);
        //x=matrix.frobenius();
        //System.out.println(x);
//
        //System.out.println(Arrays.toString(matrix.shape()));

        double[][] d3 = {{1,2},{1,2,3,4}};
        Matrix matrix4 = new Matrix(d3);
        System.out.println("tutaj");
        System.out.println(matrix4.toString());

        double[][] doubles = {{1,2},{3,4}};
        double[][] doubles1 = {{1,2,3},{4,5,6}};
        Matrix matrix = new Matrix(doubles);
        Matrix matrix1 = new Matrix(doubles1);
        Matrix matrix2 = matrix.dot(matrix1);
        System.out.println(matrix2.toString());


        //matrix.reshape(2,2);
        //System.out.println(matrix.toString());

    }


}
