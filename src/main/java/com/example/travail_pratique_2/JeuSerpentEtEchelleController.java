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
        chargerPage("acceuil");
    }

    @FXML
    private void onBtnReglesAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Règles du jeu");
        chargerPage("regles");
    }

    @FXML
    private void onBtnJouerUnePartieAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Jeu");
        chargerPage("menu-principal");
    }

    @FXML
    private void onBtnQuitterLeJeuAction() throws IOException {
        System.exit(0);
    }

    @FXML
    private void onBtnCommencerAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Jeu");
        chargerPage("menu-principal");
    }

    @FXML
    private void onBtnReglesAccueilAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Règles du jeu");
        chargerPage("regles");
    }

    @FXML
    private void onBtnQuitterAccueilAction() throws IOException {
        System.exit(0);
    }
}
