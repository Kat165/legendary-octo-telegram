package com.company;

import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children;


    void fixMissingValues() {
        if (density > 0 && population > 0)
            return;

        AdminUnit parentUnit = parent;

        while (parentUnit != null) {
            if (parentUnit.density <0)
                parentUnit = parentUnit.parent;
            else
                break;
        }

        if (parentUnit == null)
            return;

        density = parent.density;
        population = area * density;
    }

    @Override
    public String toString(){
        return "admin_unit{"
                +" name = " +name
                +" adminlevel = "+adminLevel
                +" population = "+population
                +" area = "+area
                +" density = "+density
                +" "+bbox.toString() + " "
                +" parent = "+parent.name
                +"}\n";
    }
}
