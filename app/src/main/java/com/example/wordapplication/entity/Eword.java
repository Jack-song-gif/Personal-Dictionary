package com.example.wordapplication.entity;


import org.litepal.crud.DataSupport;

//词根扩展库（单词库）
public class Eword extends DataSupport {
    private int id;
    private String eword;//单词
    private String eMeaning;//单词意思
    private String  rootWord;
    private String rootWordId;

    public String getRootWordId() {
        return rootWordId;
    }

    public void setRootWordId(String rootWordId) {
        this.rootWordId = rootWordId;
    }

    public Eword(){}
    public Eword(String eword,String eMeaning,String  rootWord){
        this.eword=eword;
        this.eMeaning=eMeaning;
        this.rootWord=rootWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEword() {
        return eword;
    }

    public void setEword(String eword) {
        this.eword = eword;
    }

    public String geteMeaning() {
        return eMeaning;
    }

    public void seteMeaning(String eMeaning) {
        this.eMeaning = eMeaning;
    }

    public String getRootWord() {
        return rootWord;
    }

    public void setRootWord(String rootWord) {
        this.rootWord = rootWord;
    }
}
