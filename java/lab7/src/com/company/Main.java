package com.company;

import java.io.IOException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException {
	// write your code here
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.read("admin-units.csv");
        PrintStream printStream = new PrintStream(System.out);

        System.out.println("\n");
        System.out.println("Sąsiedzi Bębła");
        AdminUnit unit = adminUnitList.selectByName("^Bębło$",true).units.get(0);
        adminUnitList.getNeighbours(unit,15).list(printStream);
        System.out.println("\n");

        System.out.println("Fitrowanie");
        adminUnitList.filter(a->a.name.startsWith("K"),3,8).list(printStream);
        System.out.println("\n");

        System.out.println("sortowanie");
        adminUnitList.selectByName(".*Kolonia.*",true).sortInplaceByName().list(printStream,0,10);
        System.out.println("\n");

        System.out.println("zapytanie");
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(adminUnitList)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("S"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(20);
        query.execute().list(printStream);

    }
}
