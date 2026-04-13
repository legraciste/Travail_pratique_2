package com.example.travail_pratique_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class JeuSerpentEtEchelleController {

    @FXML
    private BorderPane sectionsApplication;

//    @FXML
//    private void initialize() throws IOException {
//        chargerPage("Accueil");
//    }

    @FXML
    private void chargerPage(String sceneDesiree) throws IOException {
        Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(sceneDesiree + ".fxml")));
        sectionsApplication.setCenter(page);
    }

    @FXML
    private void onBtnAccueilAction() throws IOException {
        chargerPage("Acceuil");
    }

    @FXML
    private void onBtnReglesAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Règles du jeu");
        chargerPage("Regles");
    }

    @FXML
    private void onBtnJouerUnePartieAction() throws IOException {
        chargerPage("Jeu");
    }

    @FXML
    private void onBtnQuitterLeJeuAction() throws IOException {
        System.exit(0);
    }
}
