package com.company;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    private HTMLList list;

    public ParagraphWithList() {
        super();
        this.list = new UnorderedList();
    }

    public ParagraphWithList(String content) {
        super(content);
        this.list = new UnorderedList();
    }

    public ParagraphWithList(String content, HTMLList list) {
        super(content);
        this.list = list;
    }

    public ParagraphWithList setContent(String content) {
        super.setContent(content);
        return this;
    }

    public ParagraphWithList addListItem(String content) {
        list.addItem(content);
        return this;
    }

    @Override
    public void writeHTML(PrintStream out) {
        super.writeHTML(out);
        list.writeHTML(out);
    }
}
