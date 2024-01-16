package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.JoueurModel;

import java.util.List;
import java.util.ArrayList;

@Api(value = "JoueurService", description = "REST APIs related to Joueur Entity!!!!")
@RestController
public class JoueurService {

    List<JoueurModel> joueurs = new ArrayList<JoueurModel>(); {
        joueurs.add(new JoueurModel(1, "Armand", 19, "gardien"));
        joueurs.add(new JoueurModel(2, "Boris", 22, "défenseur"));
        joueurs.add(new JoueurModel(3, "Célia", 21, "défenseur"));
        joueurs.add(new JoueurModel(4, "Didier", 20, "défenseur"));
        joueurs.add(new JoueurModel(5, "Etienne", 19, "défenseur"));
        joueurs.add(new JoueurModel(6, "Florence", 22, "milieu"));
        joueurs.add(new JoueurModel(7, "Gabrielle", 21, "milieu"));
        joueurs.add(new JoueurModel(8, "Hector", 20, "milieu"));
        joueurs.add(new JoueurModel(9, "Isabelle", 19, "milieu"));
        joueurs.add(new JoueurModel(10, "Jérôme", 22, "attaquant"));
        joueurs.add(new JoueurModel(11, "Karine", 21, "attaquant"));
        joueurs.add(new JoueurModel(12, "Léon", 20, "gardien"));
        joueurs.add(new JoueurModel(13, "Marie", 19, "défenseur"));
        joueurs.add(new JoueurModel(14, "Nicolas", 22, "défenseur"));
        joueurs.add(new JoueurModel(15, "Olivier", 21, "défenseur"));
        joueurs.add(new JoueurModel(16, "Pierre", 20, "milieu"));
        joueurs.add(new JoueurModel(17, "Quentin", 19, "milieu"));
        joueurs.add(new JoueurModel(18, "Romain", 22, "milieu"));
        joueurs.add(new JoueurModel(19, "Sébastien", 21, "milieu"));
        joueurs.add(new JoueurModel(20, "Thierry", 20, "milieu"));
        joueurs.add(new JoueurModel(21, "Ursule", 19, "attaquant"));
        joueurs.add(new JoueurModel(22, "Victor", 22, "attaquant"));
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
        return "Here it is the index of JoueurService.";
    }

//    - `GET /players/{id}` : Renvoie les détails d'un joueur par son identifiant.
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/players/{id}")
    @ApiOperation(value = "Get a player by id", response = JoueurModel.class)
    public String getPlayers(@PathVariable(value = "id") int id) {
        for (JoueurModel joueur : joueurs) {
            if (joueur.getId() == id) {
                return joueur.toString();
            }
        }
        return "Joueur non trouvé";
    }
//    - `POST /players` : Permet d'ajouter un nouveau joueur.
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/players")
    @ApiOperation(value = "Add a player in the list", response = JoueurModel.class)
    public String postPlayers() {
        joueurs.add(new JoueurModel(23, "Willy", 19, "attaquant"));
        return "Joueur ajouté à la liste : + <br>" + joueurs;
    }
//    - `PUT /players/{id}` : Met à jour les informations d'un joueur existant.
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping("/players/{id}")
    @ApiOperation(value = "Update a player by id", response = JoueurModel.class)
    public String putPlayers(@PathVariable(value = "id") int id) {
        for (JoueurModel joueur : joueurs) {
            if (joueur.getId() == id) {
                joueur.setName("Willy");
                joueur.setAge(19);
                joueur.setPoste("attaquant");
                return "Joueur mis à jour : <br>" + joueur.toString();
            }
        }
        return "Joueur non trouvé, impossible de le mettre à jour";
    }
//    - `DELETE /players/{id}` : Supprime un joueur par son identifiant.
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully retrieved list"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("/players/{id}")
    @ApiOperation(value = "Delete a player by id", response = JoueurModel.class)
    public String deletePlayers(@PathVariable(value = "id") int id) {
        for (JoueurModel joueur : joueurs) {
            if (joueur.getId() == id) {
                joueurs.remove(joueur);
                return "Joueur supprimé : <br>" + joueur.toString();
            }
        }
        return "Joueur non trouvé, impossible de le supprimer";
    }
}
