package com.example.wordapplication;

import org.litepal.crud.DataSupport;

public class DaySense extends DataSupport {
    private int id;
    private String word;
    private String interpretation;
    private int  imageId;
    public DaySense() {
    }

    public DaySense(String word, String interpretation,int imageId) {
        this.word = word;
        this.interpretation = interpretation;
        this.imageId=imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
