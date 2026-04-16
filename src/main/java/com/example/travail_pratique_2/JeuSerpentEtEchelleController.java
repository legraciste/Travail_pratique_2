package com.example.travail_pratique_2;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javax.swing.text.html.MinimalHTMLWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class JeuSerpentEtEchelleController{

    @FXML
    private BorderPane sectionsApplication;

    public BorderPane getSectionsApplication() {
        return sectionsApplication;
    }

    @FXML
    private void onBtnAccueilAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("jeu de serpent et échelle");
        JeuSerpentEtEchelleApplication.chargerPage("accueil-view");
    }

    @FXML
    private void onBtnReglesAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Règles du jeu");
        JeuSerpentEtEchelleApplication.chargerPage("regles-view");
    }

    @FXML
    private void onBtnJouerUnePartieAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Jeu");
        JeuSerpentEtEchelleApplication.chargerPage("jeu-view");
    }

    @FXML
    private void onBtnQuitterLeJeuAction() throws IOException {
        System.exit(0);
    }
}
