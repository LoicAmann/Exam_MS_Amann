package com.example.demo.controller;

import com.example.demo.model.MatchModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.delegate.MatchesDelegate;

import java.util.ArrayList;
import java.util.List;

@Api(value = "MatchService", description = "REST APIs related to Match Entity!!!!")
public class MatchService {

    @Autowired
    MatchesDelegate matchesDelegate;
    MatchModel match1 = new MatchModel(1, 1, 2, 3, 2);
    MatchModel match2 = new MatchModel(2, 2, 1, 2, 1);

    List<MatchModel> listMatchs = new ArrayList<MatchModel>();{
        listMatchs.add(match1);
        listMatchs.add(match2);
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
        return "Here it is the index of MatchService.";
    }


//    - `GET /matches/{id}` : Renvoie les détails d'un match par son identifiant.
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved list"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
    @GetMapping("/matches/{id}")
    @ApiOperation(value = "Get a match by id", response = MatchModel.class)
    public String getMatchs(@PathVariable(value="id") int id) {
        for (MatchModel match : listMatchs) {
            if (match.getId() == id) {
                String result = "Id = " + match.getId() + "<br>" +
                        "Team1 = " + matchesDelegate.callTeam(match.getTeam1()) + "<br>" +
                        "Team2 = " + matchesDelegate.callTeam(match.getTeam2() )+ "<br>" +
                        "ScoreTeam1 = " + match.getScoreTeam1() + "<br>" +
                        "ScoreTeam2 = " + match.getScoreTeam2();
                return match.toString();
            }
        }
        return "Match not found";
    }
//    - `POST /matches` : Permet d'ajouter un nouveau match.
    @PostMapping("/matches")
    @ApiOperation(value = "Add a match by id", response = MatchModel.class)
    public String addMatchs() {
        listMatchs.add(new MatchModel(3, 1, 2, 3, 1));
        return "Match added : <br>" + listMatchs.get(2).toString();
    }
//    - `PUT /matches/{id}` : Met à jour les informations d'un match existant.
    @PutMapping("/matches/{id}")
    @ApiOperation(value = "Update a match by id", response = MatchModel.class)
    public String putMatchs(@PathVariable(value="id") int id) {
        for (MatchModel match : listMatchs) {
            if (match.getId() == id) {
                match.setScoreTeam1(4);
                match.setScoreTeam2(2);
                return "Match updated : <br>" + match.toString();
            }
        }
        return "Match not found";
    }
//    - `DELETE /matches/{id}` : Supprime un match par son identifiant.
    @DeleteMapping("/matches/{id}")
    @ApiOperation(value = "Delete a match by id", response = MatchModel.class)
    public String deleteMatchs(@PathVariable(value="id") int id) {
        for (MatchModel match : listMatchs) {
            if (match.getId() == id) {
                listMatchs.remove(match);
                return "Match deleted, list of matches : <br>" + listMatchs;
            }
        }
        return "Match not found";
    }
}
