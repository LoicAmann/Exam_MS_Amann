package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

public class MatchModel {

    @ApiModelProperty(notes = "The database generated match ID")
    private int id;
    @ApiModelProperty(notes = "The database generated team1 ID")
    private int team1;
    @ApiModelProperty(notes = "The database generated team2 ID")
    private int team2;
    @ApiModelProperty(notes = "The database generated scoreTeam1 ID")
    private int scoreTeam1;
    @ApiModelProperty(notes = "The database generated scoreTeam2 ID")
    private int scoreTeam2;

    public MatchModel(int id, int team1, int team2, int score1, int score2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.scoreTeam1 = score1;
        this.scoreTeam2 = score2;
    }

    public int getId() {
        return id;
    }

    public int getTeam1() {
        return team1;
    }

    public int getTeam2() {
        return team2;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    @Override
    public String toString() {
        return "MatchModel{" +
                "id=" + id +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", score1=" + scoreTeam1 +
                ", score2=" + scoreTeam2 +
                '}';
    }

    public void setScoreTeam1(int i) {
        this.scoreTeam1 = i;
    }

    public void setScoreTeam2(int i) {
        this.scoreTeam2 = i;
    }
}
