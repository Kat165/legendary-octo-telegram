package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(){
        this.title = "Tytul";
        this.photo =new Photo("...");
    }

    public Document(String t){
        this.title = t;
        this.photo =new Photo("...");
    }

    public Document setTitle(String title){
        this.title = title;
        return this;
    }

    public Document setPhoto(String photoUrl){
        this.photo =new Photo(photoUrl);
        return this;
    }

    public Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section section = new Section();
        section.setTitle(sectionTitle);
        sections.add(section);
        return section;
    }
    public Document addSection(Section s){
        sections.add(s);
        return this;
    }


    public void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        out.print("<html>");
        out.print("<head>");
        out.print("<meta charset=\"UTF-8\">");
        out.printf("<title>%s</title>",title);
        out.print("</head>");
        out.print("<body>");
        out.printf("<h1>%s</h1>",title);
        photo.writeHTML(out);
        for (Section section : sections) {
            section.writeHTML(out);
        }
        out.print("</body>");
        out.print("</html>");
    }
}
