package com.company;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    UnorderedList unorderedList;

    ParagraphWithList(){
        super();
        this.unorderedList = new UnorderedList();
    }

    ParagraphWithList(String content) {
        super(content);
        this.unorderedList = new UnorderedList();
    }

    ParagraphWithList(String content, UnorderedList list){
        super(content);
        this.unorderedList = list;
    }

    public ParagraphWithList setContent(String content) {
        this.content = content;
        return this;
    }
    public ParagraphWithList addItemToList(String item){
        unorderedList.addItem(item);
        return this;
    }

    public void writeHTML(PrintStream out){
        super.writeHTML(out);
        unorderedList.writeHTML(out);
    }
}
