package com.company;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("accelerator.csv",";",true);
        while (reader.next()){
            double X = reader.getDouble("X");
            double Y = reader.getDouble("Y");
            double longitude = reader.getDouble("longitude");
            double latitude = reader.getDouble("latitude");
            double speed = reader.getDouble("speed");
            double time = reader.getDouble("time");
            double label = reader.getDouble("label");
            System.out.printf("%f %f %f %f %f %f %f \n",X, Y, longitude, latitude, speed, time, label);
        }
        System.out.println("\n\n");
        CSVReader reader1 = new CSVReader("no-header.csv",";");
        while(reader1.next()) {
            int x = reader1.getInt(0);
            String imie = reader1.get(1);
            String nazwisko = reader1.get(2);
            String ulica = reader1.get(3);
            int y = reader1.getInt(4);
            int z =reader1.getInt(5);
            System.out.printf("%d %s %s %s %d %d \n",x,imie,nazwisko,ulica,y,z);
        }
        System.out.println("\n\n");
        CSVReader reader2 = new CSVReader("with-header.csv",";",true);
        while (reader2.next()){
            int x = reader2.getInt("id");
            String imie = reader2.get("imie");
            String nazwisko = reader2.get("nazwisko");
            String ulica = reader2.get("ulica");
            int y = reader2.getInt("nrdomu");
            int z =reader2.getInt("nrmieszkania");
            System.out.printf("%d %s %s %s %d %d \n",x,imie,nazwisko,ulica,y,z);
        }

        System.out.println("\n\n");
        CSVReader reader3 = new CSVReader("admin-units.csv",",",true);
        while (reader3.next()){
            long id = reader3.getLong("id");
            long parent = reader3.getLong("parent");
            String name = reader3.get("name");
            long admin_level = reader3.getLong("admin_level");
            double population = reader3.getDouble("population");
            double area = reader3.getDouble("area");
            double density = reader3.getDouble("density");
            double x1 = reader3.getDouble("x1");
            double y1 = reader3.getDouble("y1");
            double x2 = reader3.getDouble("x2");
            double y2 = reader3.getDouble("y2");
            double x3 = reader3.getDouble("x3");
            double y3 = reader3.getDouble("y3");
            double x4 = reader3.getDouble("x4");
            double y4 = reader3.getDouble("y4");
            System.out.printf("%d %d %s %d %f %f %f %f %f %f %f %f %f %f %f",
                    id,parent,name,admin_level,population,area,density,x1,y1,x2,y2,x3,y3,x4,y4);
        }


    }
}
