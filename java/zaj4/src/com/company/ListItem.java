package com.company;

import java.io.PrintStream;

public class ListItem {
    String content;

    public ListItem(){
        this.content = "";
    }

    public ListItem(String content){
        this.content = content;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>%s</li>",content);
    }
}
