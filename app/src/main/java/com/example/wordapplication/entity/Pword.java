package com.example.wordapplication.entity;

import org.litepal.crud.DataSupport;

public class Pword extends DataSupport {
    private int id;
    private String mysaying;//个人语录
    private String date;
    public Pword(){}
    public Pword(String mysaying,String date)
    {
        this.mysaying=mysaying;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMysaying() {
        return mysaying;
    }

    public void setMysaying(String mysaying) {
        this.mysaying = mysaying;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
