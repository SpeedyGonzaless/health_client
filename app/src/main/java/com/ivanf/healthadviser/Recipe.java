package com.ivanf.healthadviser;

public class Recipe {
    private String name; // название
    private String Company;  // столица
    private String img; // ресурс флага
    private String Doc;
    private String Data_Begin;
    private String Data_End;
    private String Use;
    private String Tutorial;

    public Recipe(String name, String Company, String img, String Doc, String Data_Begin, String Data_End, String Use, String Tutorial){

        this.name=name;
        this.Company=Company;
        this.img=img;
        this.Doc=Doc;
        this.Data_Begin=Data_Begin;
        this.Data_End=Data_End;
        this.Use=Use;
        this.Tutorial=Tutorial;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return this.Company;
    }

    public void setCompany(String capital) {
        this.Company = Company;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDoc() {return this.Doc; }

    public void setDoc(String Doc) {this.Doc = Doc;}

    public String getData_Begin() {
        return this.Data_Begin;
    }

    public void setData_Begin(String Data_Begin) {
        this.Data_Begin = Data_Begin;
    }
    public String getData_End() {
        return this.Data_End;
    }

    public void setData_End(String Data_End) {
        this.Data_End = Data_End;
    }
    public String getUse() {
        return this.Use;
    }

    public void setUse(String Use) {
        this.Use = Use;
    }
    public String getTutorial() {
        return this.Tutorial;
    }

    public void setTutorial(String Tutorial) {
        this.Tutorial = Tutorial;
    }
}