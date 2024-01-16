package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

public class JoueurModel {
    @ApiModelProperty(notes = "The database generated joueur ID")
    private int id;
    @ApiModelProperty(notes = "The database generated joueur name")
    private String name;
    @ApiModelProperty(notes = "The database generated joueur age")
    private int age;
    @ApiModelProperty(notes = "The database generated joueur poste")
    private String poste;

    public JoueurModel(int id, String name, int age, String poste) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.poste = poste;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPoste() {
        return poste;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public String toString() {
        return "JoueurModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", poste='" + poste + '\'' +
                '}';
    }
}
