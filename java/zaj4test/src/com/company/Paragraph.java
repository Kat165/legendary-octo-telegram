package com.company;

import java.io.PrintStream;

public class Paragraph implements HTMLElement {
    protected String content;

    public Paragraph() {}

    public Paragraph(String content) {
        this.content = content;
    }

    public Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>", content);
    }
}
