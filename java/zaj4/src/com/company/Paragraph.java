package com.company;

import java.io.PrintStream;

public class Paragraph {
    String content;

    public Paragraph(){
        this.content = "Jakis tekst";
    }

    public Paragraph(String content) {
        this.content = content;
    }

    public Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    public void writeHTML(PrintStream out){
        out.print("<p>");
        out.printf("%s",content);
        out.print("</p>");
    }
}
