package com.example.wordapplication.entity;


import org.litepal.crud.DataSupport;

//词根表
public class RootWord extends DataSupport {
    private int id;
    private String rootWord;//词根
    private String rootMeaning;//词根意思
    private Eword eword;//对词根例子的引用,外键

    public RootWord(){}
    public RootWord(String rootWord,String rootMeaning){
        this.rootWord=rootWord;
        this.rootMeaning=rootMeaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRootWord() {
        return rootWord;
    }

    public void setRootWord(String rootWord) {
        this.rootWord = rootWord;
    }

    public String getRootMeaning() {
        return rootMeaning;
    }

    public void setRootMeaning(String rootMeaning) {
        this.rootMeaning = rootMeaning;
    }

    public Eword getEword() {
        return eword;
    }

    public void setEword(Eword eword) {
        this.eword = eword;
    }
}
