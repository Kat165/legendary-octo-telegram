package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.io.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    CSVReader reader;
    Map<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    Map<AdminUnit, Long> adminUnitToParentId = new HashMap<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    AdminUnitList(){};

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */

    public void read(String filename) throws IOException {
        reader = new CSVReader(filename,",",true);
        while (reader.next()){

            AdminUnit adminUnit = new AdminUnit();
            BoundingBox bbox = new BoundingBox();
            adminUnit.name = reader.get("name");
            adminUnit.adminLevel = reader.getInt("admin_level");
            adminUnit.population = reader.getDouble("population");
            adminUnit.area = reader.getDouble("area");
            adminUnit.density = reader.getDouble("density");

            bbox.xmin = reader.getDouble("x1", Double.NaN);
            bbox.xmax = reader.getDouble("x3", Double.NaN);
            bbox.ymin = reader.getDouble("y1", Double.NaN);
            bbox.ymax = reader.getDouble("y3", Double.NaN);

            adminUnit.bbox = bbox;

            idToAdminUnit.put(reader.getLong("id"), adminUnit);
            long parentId = reader.getLong("parent");
            adminUnitToParentId.put(adminUnit, parentId);

            if (parentid2child.containsKey(parentId))
                parentid2child.get(parentId).add(adminUnit);
            else {
                List<AdminUnit> children = new ArrayList<>();
                children.add(adminUnit);
                parentid2child.put(parentId, children);
                units.add(adminUnit);
            }
        }

        for (AdminUnit unit : units)
        {
            long parentId = adminUnitToParentId.get(unit);
            unit.parent = idToAdminUnit.getOrDefault(parentId, null);
            if (unit.parent != null)
                unit.parent.children = parentid2child.getOrDefault(parentId,new ArrayList<>());
        }
        fixMissingValues();
    }

    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     * @param out
     */
    void list(PrintStream out){
        for (AdminUnit unit : units)
            out.println(unit.toString());
    }
    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     * @param out - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out,int offset, int limit ){
        for (int i = offset; i<offset+limit; ++i)
        {
            out.println(units.get(i).toString());
        }
    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        ret.units = units.stream().filter(regex?p->p.name.matches(pattern):p->p.name.matches(pattern))
                .collect(Collectors.toList());
        //<--wyk 9
        return ret;
    }

    private void fixMissingValues(){
        units.forEach(AdminUnit::fixMissingValues);
    }


    /**
     * Zwraca listę jednostek sąsiadujących z jendostką unit na tym samym poziomie hierarchii admin_level.
     * Czyli sąsiadami wojweództw są województwa, powiatów - powiaty, gmin - gminy, miejscowości - inne miejscowości
     * @param unit - jednostka, której sąsiedzi mają być wyznaczeni
     * @param maxdistance - parametr stosowany wyłącznie dla miejscowości, maksymalny promień odległości od środka unit,
     *                    w którym mają sie znaleźć punkty środkowe BoundingBox sąsiadów
     * @return lista wypełniona sąsiadami
     */
    AdminUnitList getNeighbours(AdminUnit unit, double maxdistance) throws IllegalAccessException {
        AdminUnitList neighbours = new AdminUnitList();
        for (int i = 0; i<units.size(); ++i)
        {
            if (unit == units.get(i))
                continue;
            if (units.get(i).adminLevel== unit.adminLevel)
                if (unit.adminLevel < 8)
                {
                    if (unit.bbox.intersects(units.get(i).bbox))
                        neighbours.units.add(units.get(i));
                }
                else {
                    if (unit.bbox.distanceTo(units.get(i).bbox)<=maxdistance)
                        neighbours.units.add(units.get(i));
                }
        }
        return neighbours;
    }

    static class NameComparator implements Comparator<AdminUnit> {
        @Override
        public int compare(AdminUnit t, AdminUnit  t1)
        {
            return t.name.compareTo(t1.name);
        }
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByName(){
        units.sort(new NameComparator());
        return this;
    }



    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByArea(){
        units.sort(new Comparator<AdminUnit>(){
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                return Double.compare(t.area, t1.area);
            }});
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     * @return this
     */
    AdminUnitList sortInplaceByPopulation(){
        units.sort(new Comparator<AdminUnit>(){
            @Override
            public int compare(AdminUnit t, AdminUnit t1) {
                return Double.compare(t.population, t1.population);
            }});
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        // Tworzy wyjściową listę
        // Kopiuje wszystkie jednostki
        // woła sortInPlace
        AdminUnitList tmp = new AdminUnitList();
        tmp.units.addAll(this.units);
        tmp.sortInplace(cmp);
        return tmp;
    }

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return nową listę, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList ret = new AdminUnitList();
        ret.units = this.units.stream().filter(pred).collect(Collectors.toList());
        return ret;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList ret = new AdminUnitList();
        ret.units = this.units.stream().filter(pred).limit(limit).collect(Collectors.toList());
        return ret;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList ret = new AdminUnitList();
        ret.units = this.units.stream().filter(pred).skip(offset).limit(limit).collect(Collectors.toList());
        return ret;
    }
}
