package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;

    Section(){
        this.title = "Tytuł sekcji";
    }

    Section setTitle(String title){
        this.title = title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph paragraph = new Paragraph();
        paragraph.setContent(paragraphText);
        paragraps.add(paragraph);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<h2>%s</h2>",title);
        for (Paragraph paragraph : paragraps) {
            paragraph.writeHTML(out);
        }
    }
}
