package com.ivanf.healthadviser;

public class Drag {

    private String name; // название
    private String capital;  // столица
    private String flagResource; // ресурс флага
    private String Descr;

    public Drag(String name, String capital, String flag, String Descr){

        this.name=name;
        this.capital=capital;
        this.flagResource=flag;
        this.Descr=Descr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlagResource() {
        return this.flagResource;
    }

    public String getDescr(){return this.Descr;}

    public void setDescr(String Descr){this.Descr = Descr;}

    public void setFlagResource(String flagResource) {
        this.flagResource = flagResource;
    }
}
