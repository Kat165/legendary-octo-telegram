package com.company;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class NameList {
    List<Name> imiona = new ArrayList<>();
    CSVReader reader;

    NameList() throws IOException {
        read();
        //b) Które imiona męskie i żeńskie były najpopularniejsze w ciągu 17 lat
        System.out.println("Najbardziej popularne imiona:");
        popular();
        //c) Wypisz ile dzieci urodziło się w kolejnych latach z zakresu 2000-2016
        System.out.println("\nIle dzieci urodziło się w kolejnych latach:");
        kids();
        //d) Podaj dla którego imienia męskiego i żeńskiego zaobserwowano największy przyrost popularności.
        popularv2();//nieskonczone

    }

    private void popularv2() {
        Map<String,Integer>ile = new HashMap<>();
        imiona.stream().filter(imie->imie.plec.equals("K")).forEach(imie->ile.compute(imie.imie, (k,v)->(v==null)?1:v+ imie.liczba));
        System.out.println(ile);
    }

    private void kids() {
        Map<Integer,Integer>ile = new HashMap<>();
        imiona.forEach(imie->ile.compute(imie.rok, (k,v)->(v==null)?1:v+1));
        System.out.println(ile);
    }

    void popular(){

        imiona.stream().filter(imie->imie.plec.equals("K")).sorted(Comparator.comparingInt(Name::getLiczba).reversed()).limit(1).collect(Collectors.toList()).forEach(System.out::println);

        imiona.stream().filter(imie->imie.plec.equals("M")).sorted(Comparator.comparingInt(Name::getLiczba).reversed()).limit(1).collect(Collectors.toList()).forEach(System.out::println);

    }


    void read() throws IOException {
        String filename = "imiona-2000-2016.csv";
        reader = new CSVReader(filename,";",true);
        while (reader.next()){
            Name name = new Name();
            name.rok = reader.getInt("Rok");
            name.imie = reader.get("Imię");
            name.liczba = reader.getInt("Liczba");
            name.plec = reader.get("Płeć");
            imiona.add(name);
        }
    }

    public static void main(String[] args) throws IOException {
        new NameList();
    }

}
