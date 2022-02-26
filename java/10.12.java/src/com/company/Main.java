package com.company;

public class Main {
    public static void main (String[] args){
        if (3<=1) {
            System.out.println("3>1");
        }
        else {
            System.out.println("3>1");
        }

        int i=0;
        while (i<10){
            System.out.println("Wartosc i w pentli: "+i);
            i++;
        }
        int wynik = 937%10;
        System.out.println("Wynik 937%10: "+wynik);

//        zadanie1();//WSZYSTKIE 3 CYFROWE LICZBY KTORYCHSUMA SZESCIANOW POSZCZEGOLNYCH LICZB = DANEJ LICZBIE
//        zadanie2();//WSZYSTKIE LICZBY DWOCYFROWE : PODWOJONY SZESCIAN DANEJ LICZBY JEST WIELOKROTNOSCIA SUMY JEJ LICZB
//        zadanie3();// WSZYSTKIE 4 CYFROWE LICZBY PALINDROMICZNE
//        zadanie4();//SORTOWANIE
//        zadanie5();//SORTOWANIE
    }
    public static void zadanie1(){
        for (int i=100;i<=999;i++){
            int l3=i%10;
            int l2=(i%100)/10;
            int l1=i/100;
            if (i==(Math.pow(l1,3)+Math.pow(l2,3)+Math.pow(l3,3))){
                System.out.println(i);
            }
        }
    }
    public static void zadanie2(){
        for (int i=10;i<100;i++){
            int suma=i%10 + i/10;
            if(2*(i*i*i)%suma == 0 ){
    System.out.println(i);
            }
        }
    }
    public static void zadanie3(){
        int []cyfry=new int [4];
        int temp;
        for(int i=1000;i<10000;i++){
            temp=i;
            for(int j=0;j<4;j++)
            {
                cyfry[j]=temp%10;
                temp/=10;
            }
            if (cyfry[0] == cyfry[3] && cyfry[1] == cyfry[2])
            {
                System.out.println(i);
            }
        }
    }

    public static int[] zadanie4(int [] arr){
        int pom;

        for(int j=0;j<arr.length;j++){
            for(int i=0;i<arr.length-1-j;i++){
                if(arr[i]>arr[i+1]){
                    pom=arr[i+1];
                    arr[i+1]=arr[i];
                    arr[i]=pom;
                }
            }
        }

        return arr;
    }

    public static int[] zadanie5(int [] arr){
        return arr;
    }

}

