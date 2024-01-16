package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TeamModel {
    @ApiModelProperty(notes = "The database generated team ID")
    private int id;
    @ApiModelProperty(notes = "The database generated team name")
    private String name;
    @ApiModelProperty(notes = "The database generated team joueurs")
    private List<Integer> joueurs;

    public TeamModel(int id, String name, List<Integer> joueurs) {
        this.id = id;
        this.name = name;
        this.joueurs = joueurs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getJoueurs() {
        return joueurs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJoueurs(List<Integer> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public String toString() {
        return "TeamModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joueurs=" + joueurs +
                '}';
    }

}
