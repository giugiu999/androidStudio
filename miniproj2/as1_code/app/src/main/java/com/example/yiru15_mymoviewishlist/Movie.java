package com.example.yiru15_mymoviewishlist;



import java.io.Serializable;

public class Movie implements Serializable {
    private String name;
    private String dname;
    private String g;
    private int time;
    private Boolean status;

    public Movie(String name,String dname,String g,int time,Boolean status){
        this.name=name;
        this.dname=dname;
        this.g=g;
        this.time=time;
        this.status=status;
    }
    public String getName(){
        return name;
    }

    public String getDname() {
        return dname;
    }

    public String getG() {
        return g;
    }

    public Boolean getStatus() {
        return status;
    }

    public int getTime() {
        return time;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setDname(String dname){
        this.dname=dname;

    }

    public void setG(String g) {
        this.g = g;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public Boolean status(){
        return status;
    }
}

