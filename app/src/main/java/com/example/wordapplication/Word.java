package com.example.wordapplication;

import org.litepal.crud.DataSupport;

public class Word extends DataSupport {
    private int id;
    private String word;
    private String interpretation;

    public Word() {
    }

    public Word(String word, String interpretation) {
        this.word = word;
        this.interpretation = interpretation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}
