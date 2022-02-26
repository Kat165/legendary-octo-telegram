package com.company;

import java.util.Random;
import static java.lang.Math.*;

public class Matrix {
    double[]data;
    int rows;
    int cols;

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }



//2.1 Zaimplementuj konstruktor
    Matrix(double[][] d){
        int newcols = 0;
        for (double[] doubles : d) {
            if (doubles.length > newcols) {
                newcols = doubles.length;
            }
        }
        this.cols = newcols;
        this.rows = d.length;
        data = new double[rows*cols];
        for (int i = 0; i < d.length; ++i)
            for (int j = 0; j < d[i].length; ++j)
            {
                if (j >= d[i].length)

                    data[i*cols +j] = 0;
                else
                    data[i*cols +j] = d[i][j];
            }
    }

//2.2 Zaimplementuj metodę która zwraca tablicę dwuwymiarową
    double[][] asArray(){
        double[][] nArray = new double[rows][cols];
        for (int i =0; i<nArray.length;i++)
        {
            for (int j=0;j<nArray[i].length;j++)
            {
                nArray[i][j] = data[(i*(cols)) + j];
            }
        }
        return nArray;
    }


//2.3 Zaimplementuj metody dostępu do elementów (settery i gettery)
    double get(int r,int c)
    {
        if (r>=rows || c>=cols || r<0 ||c<0)
        {
            throw new IllegalArgumentException("zle wymiary");
        }

        return data[(r*(cols)) + c];
    }

    void set (int r,int c, double value)
    {
        if (r>=rows || c>=cols || r<0 ||c<0)
        {
            throw new IllegalArgumentException("zle wymiary");
        }
        data[r*cols+c]=value;
    }


//2.4 Zaimplementuj metodę toString
    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for (int j= 0; j<cols; j++)
            {
                if(j!=0)buf.append(",");
                    buf.append(Double.toString(data[(i*(cols)) + j]));

            }
            buf.append("]");
        }
        buf.append("]");
        return buf.toString();
}

//2.5 Zaimplementuj metodę reshape
    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        rows = newRows;
        cols = newCols;
    }

//2.6 Zaimplementuj metodę shape
    int[] shape()
    {
        int [] tab = new int[2];
        tab[0] = cols;
        tab[1] = rows;
        return tab;
    }


//2.7 Zaimplementuj metodę add
    Matrix add(Matrix m)
    {
        if (m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("nie można dodać macierzy"));

       Matrix n = new Matrix(rows,cols);
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                n.data[(i*(cols)) + j] = data[(i*(cols)) + j]+m.data[(i*(cols)) + j];
            }
        }
        return n;
    }


///2.8 Analogicznie zaimplementuj metody
    Matrix sub(Matrix m){
        if (m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("nie można odjąć macierzy"));

        Matrix n = new Matrix(rows,cols);
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                n.data[(i*(cols)) + j] = data[(i*(cols)) + j]-m.data[(i*(cols)) + j];
            }
        }
        return n;
    }

    Matrix mul(Matrix m)
    {
        if (m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("nie można mnożyć macierzy"));

        Matrix n = new Matrix(rows,cols);
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                n.data[(i*(cols)) + j] = data[(i*(cols)) + j]*m.data[(i*(cols)) + j];
            }
        }
        return n;
    }


    Matrix div(Matrix m){

        if (m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("nie można dzielić macierzy"));

        Matrix n = new Matrix(rows,cols);
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                n.data[(i*(cols)) + j] = data[(i*(cols)) + j]/m.data[(i*(cols)) + j];
            }
        }
        return n;

    }


    Matrix add(double w){
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                data[(i*(cols)) + j] = data[(i*(cols)) + j] + w;
            }
        }
        return this;
    } // dodaje wartość w do każdego elementu


    Matrix sub(double w){
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                data[(i*(cols)) + j] = data[(i*(cols)) + j] - w;
            }
        }
        return this;
    } // odejmuje wartośc w od kazdego elementu

    Matrix mul(double w){
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                data[(i*(cols)) + j] = data[(i*(cols)) + j] * w;
            }
        }
        return this;
    } // mnoży każdy element przez skalar w

    Matrix div(double w){
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                data[(i*(cols)) + j] = data[(i*(cols)) + j] / w;
            }
        }
        return this;
    } // dzieli każdy element przez skalar w



//2.9 Zaimplementuj zwykłe mnożenie macierzy.
    Matrix dot(Matrix m){
        if (cols!=m.rows)
            throw new RuntimeException(String.format("nie można mnożyć macierzy"));

        Matrix n = new Matrix(rows,m.cols);


        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < m.cols; ++j) {
                for (int k = 0; k < m.rows; ++k)
                    n.data[(i*(cols)) + j ] += data[(i*(cols)) + k] * m.data[(k*(m.cols)) + j];

            }
        }
        return n;

       //Matrix result = new Matrix(rows, m.cols);

       //for (int y = 0; y < cols; y++) {
       //    for (int x = 0; x < m.cols; x++) {
       //        double sum = 0;
       //        for (int k = 0; k < cols; k++) {
       //            sum += get(y, k) * m.get(k, x);
       //        }
       //        result.set(y, x, sum);
       //    }
       //}

       //return result;

    }

//2.10 Zaimplementuj normę Frobeniusa

    double frobenius()
    {
        double sum = 0;
        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
               sum += pow(data[(i*(cols)) + j],2);
            }
        }
        return sqrt(sum);
    }

//2.11 Metody statyczne budujące macierze

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        m.set(0,0,r.nextDouble());
        //... wypełnij wartościami losowymi

        for (int i =0; i<rows; i++)
        {
            for (int j = 0; j<cols; j++)
            {
                m.data[(i*(cols)) + j] = r.nextDouble();
            }
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        //... wypełnij jedynkami na przekątnej

        for (int i =0; i<n; i++)
        {
            for (int j = 0; j<n; j++)
            {
                if (j==i)
                m.data[(i*(n)) + j] = 1;
            }
        }
        return m;
    }
}
