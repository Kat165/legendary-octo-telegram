package com.company;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//Napisz program, który wypełnia tablicę int[] liczącą około 100mln elementów losowymi wartościami z zakresu [2,100000].


public class CountPrimes {
    static double[] array;
    static BlockingQueue<Integer> results = new ArrayBlockingQueue<Integer>(100000000);
    static void initArray(){
        array = new double[100000000];
        double size = 100000000;
        for(int i=0;i<size;i++){
            array[i]= (int)(Math.random()*99998)+2;
        }
    }

    static int countPrimesSequential(){
        int count = 0;
        double t1 = System.nanoTime()/1e6;
        for (int i = 0; i<array.length; ++i){
            count += isPrime(array[i]);
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"Czas countPrimesSequential:  t2-t1=%f \n", t2-t1);
        System.out.println("Ilość liczb pierwszych: ");
        System.out.println(count);
        return count;
    }

    static int isPrime(double liczba){
        if(liczba<2)
        {
            return 0;
        }
        for(int i=2; i<=liczba/2; i++)
        {
            if(liczba%i==0)
            {
                return 0;
            }
        }
        return 1;
    }

    static class count extends Thread{
        int start;
        int end;
        int cnt = 0;

        count(int s, int e){
            this.start = s;
            this.end = e;
        }

        public void run(){
            for (int i = start; i<end; ++i){
                cnt+=isPrime(array[i]);
            }
            try {
                results.put(cnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static int countPrimesParallel(int cnt) throws InterruptedException {
        count[]threads = new count[cnt];

        int length = array.length/cnt;
        double t1 = System.nanoTime()/1e6;

        for (int i = 0; i< cnt; ++i)
        {
            threads[i] = new count(i * length,(i+1)*length );
        }
        double t2 = System.nanoTime()/1e6;

        for(count mc:threads) {
            mc.start();
        }
        int c = 0;
        for (int i = 0; i<cnt; ++i){
            c+=results.take();
        }
        System.out.printf(Locale.US,"Czas countPrimesParallel:  t2-t1=%f \n", t2-t1);
        System.out.println("Ilość liczb pierwszych: ");
        System.out.println(c);
        return c;
    }

    public static void main(String[] args) throws InterruptedException {
        initArray();
        countPrimesSequential();
        countPrimesParallel(1000);
    }

}

