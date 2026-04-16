package com.example.travail_pratique_2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javax.swing.*;
import java.io.IOException;

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
        Alert confirmationQuitter = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationQuitter.setTitle("Confirmation de quitter le jeu");
        confirmationQuitter.setHeaderText("Êtes-vous sûr de vouloir quitter le jeu ?");
        confirmationQuitter.setContentText("Cliquez sur OK pour quitter, ou sur Annuler pour rester dans le jeu.");
        if(confirmationQuitter.showAndWait().orElse(null) == ButtonType.OK){
            JeuSerpentEtEchelleApplication.stage.close();
        }else{
            confirmationQuitter.close();
        }
    }
}
