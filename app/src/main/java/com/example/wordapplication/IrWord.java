package com.example.wordapplication;

import org.litepal.crud.DataSupport;

public class IrWord extends DataSupport {
    private int id;
    private String proWord;
    private String comWord;
    public IrWord(){

    }
    public IrWord(String proWord,String comWord){
        this.proWord=proWord;
        this.comWord=comWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProWord() {
        return proWord;
    }

    public void setProWord(String proWord) {
        this.proWord = proWord;
    }

    public String getComWord() {
        return comWord;
    }

    public void setComWord(String comWord) {
        this.comWord = comWord;
    }
}
