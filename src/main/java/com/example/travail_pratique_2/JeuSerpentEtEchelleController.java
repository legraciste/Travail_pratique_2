package com.example.travail_pratique_2;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class JeuSerpentEtEchelleController{

    @FXML
    private BorderPane sectionsApplication;

    public BorderPane getSectionsApplication() {
        return sectionsApplication;
    }

    @FXML
    private void onBtnAccueilAction() throws IOException {
        JeuSerpentEtEchelleApplication.chargerPage("JeuSerpentEtEchelle");
    }

    @FXML
    private void onBtnReglesAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Règles du jeu");
        JeuSerpentEtEchelleApplication.chargerPage("regles");
    }

    @FXML
    private void onBtnJouerUnePartieAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Jeu");
        JeuSerpentEtEchelleApplication.chargerPage("jeu");
    }

    @FXML
    private void onBtnQuitterLeJeuAction() throws IOException {
        System.exit(0);
    }
}
