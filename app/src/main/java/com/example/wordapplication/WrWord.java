package com.example.wordapplication;

import org.litepal.crud.DataSupport;

public class  WrWord   extends DataSupport {
    private int id;
    private String wword;
    private String interpretation;


    public WrWord() {
    }

    public WrWord(String wword, String interpretation) {
        this.wword = wword;
        this.interpretation = interpretation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWword() {
        return wword;
    }

    public void setWword(String wword) {
        this.wword = wword;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}
