package com.company;

public class Przyklad {

    public void wypisz(int liczbaLini){
        for (int i=0 ; i<liczbaLini; i++){
            for (int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }

}
