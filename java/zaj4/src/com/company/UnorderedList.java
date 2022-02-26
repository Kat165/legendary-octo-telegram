package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> listItems= new ArrayList<>();

    public UnorderedList(){
        this.listItems = new ArrayList<>();
    }

    public UnorderedList(String item){
        this.listItems = new ArrayList<>();
        listItems.add(new ListItem(item));
    }

    public UnorderedList addItem(ListItem item){
        listItems.add(item);
        return this;
    }

    public UnorderedList addItem(String item){
        listItems.add(new ListItem(item));
        return this;
    }

    public void writeHTML(PrintStream out){
        out.print("<ul style=\"list-style-type:disc;\">");
        for (ListItem listItem : listItems) {
            listItem.writeHTML(out);
        }
        out.print("</ul>");
    }
}
