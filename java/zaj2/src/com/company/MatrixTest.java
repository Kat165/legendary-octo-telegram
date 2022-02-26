package com.company;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import static java.lang.Math.*;

class MatrixTest {

    @org.junit.jupiter.api.Test
    void testMatrix() {
        Matrix matrix = new Matrix(3,4);
        assertEquals(3, matrix.rows);
        assertEquals(4,matrix.cols);
        assertEquals(12,matrix.data.length, 0.1);
    }

    @org.junit.jupiter.api.Test
    void testMatrix2() {
        double[][] d1 = {{1,2,3},{1,2}};
        Matrix matrix = new Matrix(d1);
        assertEquals(2, matrix.rows);
        assertEquals(3,matrix.cols);
        assertEquals(6,matrix.data.length, 0.1);
        double[][] arr = matrix.asArray();
        assertEquals(arr[0].length,arr[1].length);
    }

    @org.junit.jupiter.api.Test
    void asArray() {
        double [][] test ={{1,2},{1,2,3,4}};
        Matrix matrix = new Matrix(test);
        double[][] array = matrix.asArray();
        assertEquals(array[0].length,array[1].length);
        assertEquals(0,array[0][3],0.1);
        assertEquals(0,array[0][2],0.1);

    }

    @org.junit.jupiter.api.Test
    void get() {
        double [][] test ={{1,2},{5,6,3,4}};
        Matrix matrix = new Matrix(test);
        assertEquals(1, matrix.get(0,0));
        assertEquals(2,matrix.get(0,1));
        assertEquals(0, matrix.get(0,2));
        assertEquals(0,matrix.get(0,3));
        assertEquals(5, matrix.get(1,0));
        assertEquals(6,matrix.get(1,1));
        assertEquals(3, matrix.get(1,2));
        assertEquals(4,matrix.get(1,3));

        try{
            matrix.get(4,4);
            fail("Exception wasn't thrown!");
        }catch (IllegalArgumentException exception){
            assertEquals("zle wymiary", exception.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void set() {
        double [][] test ={{1,2},{1,2,3,4}};
        Matrix matrix = new Matrix(test);
        try{
            matrix.set(4,4,2);
            fail("Exception wasn't thrown!");
        }catch (IllegalArgumentException exception){
            assertEquals("zle wymiary", exception.getMessage());
        }
        matrix.set(0,0,8);
        assertEquals(8, matrix.data[0]);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        //String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        //s= s.replaceAll("(\\[|\\]|\\s)+","");
        //String[] t = s.split("(,)+");
        //for(String x:t){
        //    System.out.println(String.format("\'%s\'",x ));
        //}
//
        //double[]d=new double[t.length];
        //for(int i=0;i<t.length;i++) {
        //    d[i] = Double.parseDouble(t[i]);
        //}
//
        //double arr[][]=new double[1][];
        //arr[0]=d;
//
        //for(int i=0;i<arr.length;i++){
        //    for(int j=0;j<arr[i].length;j++){
        //        System.out.println(arr[i][j]);
        //    }
        //}
        double[][] d = {{1,2,3},{1,2}};
        Matrix matrix = new Matrix(d);
        String s = matrix.toString();
        long count = 0;
        long count2 = 0;
        long count3 = 0;


        for (int i = 0; i<s.length();i++)
        {
            if (s.charAt(i) == ',')
                count++;
        }
        assertEquals(4,count);

        for (int i = 0; i<s.length();i++)
        {
            if (s.charAt(i) == '[')
                count2++;
        }

        for (int i = 0; i<s.length();i++)
        {
            if (s.charAt(i) == ']')
                count3++;
        }
        assertEquals(6,count2+count3);


    }

    @org.junit.jupiter.api.Test
    void reshape() {
        Matrix matrix= new Matrix(2,3);
        try {
            matrix.reshape(1,3);
            fail("Exception wasn't thrown!");
        } catch (RuntimeException e){
            assertEquals(String.format("%d x %d matrix can't be reshaped to 1 x 3", matrix.rows,matrix.cols),e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void add() {
        double[][] d = {{1,2},{3,4}};
        Matrix matrix = new Matrix(d);

        double[][] d1 = {{1,2},{3,4}};
        Matrix matrix1 = new Matrix(d1);
        Matrix matrix2 =  matrix.add(matrix1);
        assertEquals(sqrt(120),matrix2.frobenius(),0.01);
    }

    @org.junit.jupiter.api.Test
    void sub() {
        double[][] d = {{1,2},{3,4}};
        Matrix matrix = new Matrix(d);

        double[][] d1 = {{1,2},{3,4}};
        Matrix matrix1 = new Matrix(d1);
        Matrix matrix2 =  matrix.sub(matrix1);
        assertEquals(0,matrix2.frobenius(),0.01);
    }

    @org.junit.jupiter.api.Test
    void mul() {
        double[][] d = {{1,1},{2,2}};
        Matrix matrix = new Matrix(d);

        double[][] d1 = {{1,1},{2,2}};
        Matrix matrix1 = new Matrix(d1);
        Matrix matrix2 =  matrix.mul(matrix1);
        assertEquals(sqrt(34),matrix2.frobenius(),0.01);
    }

    @org.junit.jupiter.api.Test
    void div() {
        double[][] d = {{1,1},{2,2}};
        Matrix matrix = new Matrix(d);

        double[][] d1 = {{1,1},{2,2}};
        Matrix matrix1 = new Matrix(d1);
        Matrix matrix2 =  matrix.div(matrix1);
        assertEquals(sqrt(4),matrix2.frobenius(),0.01);
    }

    @org.junit.jupiter.api.Test
    void testAdd() {
        double[][] d = {{1,2},{3,4}};
        Matrix matrix = new Matrix(d);
        matrix.add(2);
        assertEquals(sqrt(86),matrix.frobenius());
    }

    @org.junit.jupiter.api.Test
    void testSub() {
        double[][] d = {{1,2},{3,4}};
        Matrix matrix = new Matrix(d);
        matrix.sub(1);
        assertEquals(sqrt(14),matrix.frobenius());
    }

    @org.junit.jupiter.api.Test
    void testMul() {
        double[][] d = {{1,2},{3,4}};
        Matrix matrix = new Matrix(d);
        matrix.mul(2);
        assertEquals(sqrt(120),matrix.frobenius());
    }

    @org.junit.jupiter.api.Test
    void testDiv() {
        double[][] d = {{1,2},{3,4}};
        Matrix matrix = new Matrix(d);
        matrix.div(2);
        assertEquals(sqrt(7.5),matrix.frobenius());
    }

    @org.junit.jupiter.api.Test
    void dot() {

        double[][] doubles0 = {{1,2},{3,4}};
        double[][] doubles11 = {{1,2},{4,5}};
        Matrix matrix0 = new Matrix(doubles0);
        Matrix matrix11 = new Matrix(doubles11);
        Matrix matrix21 = matrix0.dot(matrix11);
        double[] ans0 = {9,12,19,26};
        for (int i = 0; i<4;i++)
        {
            assertEquals(ans0[i],matrix21.data[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void frobenius() {
        double[][]doubles = {{33,12},{1,7}};
        Matrix matrix = new Matrix(doubles);
        assertEquals(sqrt(1283),matrix.frobenius(),0.01);
    }

    @org.junit.jupiter.api.Test
    void odwracanie() {
        double[][] doubles={{2,3},{4,5}};
        double[][] doubles1={{-2.5,1.5},{2,-1}};
        double[][] doubles2 = {{1,0},{0,1}};

        Matrix matrix = new Matrix(doubles);
        Matrix matrix1 = new Matrix(doubles1);
        Matrix matrix2 = matrix.dot(matrix1);
        Matrix matrix3 = new Matrix(doubles2);

        String s = matrix3.toString();
        String s1 = matrix2.toString();
        assertEquals(s,s1);


    }

    @org.junit.jupiter.api.Test
    void eye() {
        double m = Matrix.eye(3).frobenius();
        assertEquals(sqrt(3),m,0.01);
    }
}