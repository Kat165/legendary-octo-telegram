package com.company;

public class Name {
    int rok;
    String imie;
    int liczba;
    String plec;

    int getLiczba(){
        return liczba;
    }

    @Override
    public String toString(){
        return "Imiona " +
                "Rok: " + rok
                + " imie: " + imie
                + " liczba: " + liczba
                + " płeć: " + plec;
    }
}
