package com.example.demo.controller;

import com.example.demo.delegate.TeamDelegate;
import com.example.demo.model.TeamModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "TeamService", description = "REST APIs related to Equipe Entity!!!!")
@RestController
public class TeamService {

    @Autowired
    TeamDelegate teamDelegate;

    List<Integer>listeJoueurTeam1 = new ArrayList<>(); {
        for(int i = 1; i < 12; i++)
            listeJoueurTeam1.add(i);
    }
    List<Integer>listeJoueurTeam2 = new ArrayList<>(); {
        for(int i = 1; i < 12; i++)
            listeJoueurTeam2.add(i+11);
    }

    TeamModel team1 = new TeamModel(1, "UST", listeJoueurTeam1);
    TeamModel team2 = new TeamModel(2, "VRP", listeJoueurTeam1);

    List<TeamModel> listTeam = new ArrayList<>(); {
        listTeam.add(team1);
        listTeam.add(team2);
    }

    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/")
    @ApiOperation(value = "Get hello", response = String.class)
    public String index() {
        return "Here it is the index of TeamService.";
    }


    //`GET /teams/{id}` : Renvoie les détails d'une équipe par son identifiant
   @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully get list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/teams/{id}")
    @ApiOperation(value = "Get a team by id", response = TeamModel.class)
    public String getTeams(@PathVariable(value = "id") int id) {
        for (TeamModel teamModel : listTeam) {
            System.out.println(listTeam);
            if(teamModel.getId() == id) {
                String result = "Team " + id + " : <br>" +
                        "Name : " + teamModel.getName() + "<br>" +
                        "Joueurs : <br>" +
                        teamDelegate.callPlayers(teamModel);
                return result;
            }
        }
        return "Team not found on the list, it can not get";
    }

    //`POST /teams` : Permet d'ajouter une nouvelle équipe
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully add item in list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/teams")
    @ApiOperation(value = "Add a team"  , response = TeamModel.class)

    public String addTeam(TeamModel newteam) {
        listTeam.add(newteam);
        return "New team add on the list : <br>" +
                team1;
    }

    //`PUT /teams/{id}` : Met à jour les informations d'une équipe existante
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully uptade item in the list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/teams/{id}")
    @ApiOperation(value = "Update a team by id", response = TeamModel.class)
    public String  updateTeam(@PathVariable(value = "id") int id, TeamModel updateTeam){
        for (TeamModel teamModel : listTeam) {
            if(teamModel.getId() == id) {
                teamModel = updateTeam;
                return "Team " + id + " was update : <br>" +
                        team1;
            }
        }
        return "Team not found, any update on the list !";
    }

    //`DELETE /teams/{id}` : Supprime une équipe par son identifiant
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully delete item in the list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/teams/{id}")
    @ApiOperation(value = "Delete a team by id", response = TeamModel.class)
    public String deleteTeam(@PathVariable(value = "id") int id) {
        for (TeamModel teamModel : listTeam) {
            //supprime la team de la liste
            if(teamModel.getId() == id) {
                listTeam.remove(teamModel);
                return "Team " + id + " removed.<br> New list :<br>"
                        + team1;
            }
        }
        return "Team id not found, any team removed !";
    }
}
