package com.example.travail_pratique_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class JeuController {

    @FXML
    private GridPane board;

    @FXML
    private Button boutonLancerDe;

    @FXML
    private Button boutonRecommencerPartie;

    @FXML
    private Button boutonRetourAccueil;

    @FXML
    private Label labelTourDuJoueur;

    @FXML
    void onBtnLancerDeAction(ActionEvent event) {
    }

    @FXML
    void onBtnRecommencerPartieAction(ActionEvent event) throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("jeu");
        JeuSerpentEtEchelleApplication.chargerPage("jeu");
    }

    @FXML
    void onBtnRetournerAccueilAction(ActionEvent event) throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Jeu de serpent et échelle");
        JeuSerpentEtEchelleApplication.chargerPage("accueil");
    }

}
