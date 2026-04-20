package com.example.travail_pratique_2;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

/**
 * Cette classe est le contrôleur principal de l'application de jeu de serpent et échelle.
 * Elle gère les interactions de l'utilisateur avec le menu principal de l'application,
 * qui permet aux utilisateurs d'accéder à différentes pages de l'application,
 * telles que la page d'accueil, la page des règles du jeu, la page de jeu et la page de quitter le jeu.
 * @author Joseph Legraciste Kamdem
 * @version 1.0
 ***/
public class JeuSerpentEtEchelleController{

    @FXML
    private BorderPane sectionsApplication; // Cette variable correspond à la section centrale de l'application, où les différentes pages seront chargées

    /**
     * Cette méthode permet d'accéder à la section centrale de l'application, où les différentes pages seront chargées.
     * Elle est utilisée par la classe JeuSerpentEtEchelleApplication pour charger les différentes pages dans cette section.
     * @return sectionsApplication - la section centrale de l'application, où les différentes pages seront chargées
     * **/
    public BorderPane getSectionsApplication() {
        return sectionsApplication;
    }

    /**
     * Methode pour accéder à la page d'accueil de l'application, qui est la première page que les utilisateurs
     * voient lorsqu'ils lancent l'application. Elle est appelée lorsque les utilisateurs cliquent sur le bouton
     * "Accueil" dans le menu principal de l'application.
     * **/
    @FXML
    private void onBtnAccueilAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("jeu de serpent et échelle"); // Mettre à jour le titre de la fenêtre pour refléter la page d'accueil
        JeuSerpentEtEchelleApplication.chargerPage("accueil-view"); // Charger la page d'accueil dans la section centrale de l'application
    }

    /**
     * Methode pour accéder à la page des règles du jeu, qui explique les règles du jeu de serpent et échelle aux utilisateurs.
     * Elle est appelée lorsque les utilisateurs cliquent sur le bouton "Règles du jeu" dans le menu principal de l'application.
     * **/
    @FXML
    private void onBtnReglesAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Règles du jeu");
        JeuSerpentEtEchelleApplication.chargerPage("regles-view");
    }

    /**
     * Methode pour accéder à la page de jeu, où les utilisateurs peuvent jouer au jeu de serpent et échelle contre l'ordinateur.
     * Elle est appelée lorsque les utilisateurs cliquent sur le bouton "Jouer une partie" dans le menu principal de l'application.
     * **/
    @FXML
    private void onBtnJouerUnePartieAction() throws IOException {
        JeuSerpentEtEchelleApplication.stage.setTitle("Jeu");
        JeuSerpentEtEchelleApplication.chargerPage("jeu-view");
    }

    /**
     * Methode pour quitter le jeu, qui affiche une boîte de dialogue de confirmation avant de fermer l'application.
     * Elle est appelée lorsque les utilisateurs cliquent sur le bouton "Quitter le jeu" dans le menu principal de l'application.
     * **/
    @FXML
    private void onBtnQuitterLeJeuAction() throws IOException {
        Alert confirmationQuitter = new Alert(Alert.AlertType.CONFIRMATION); // Créer une boîte de dialogue de confirmation pour quitter le jeu
        confirmationQuitter.setTitle("Confirmation de quitter le jeu"); // Définir le titre de la boîte de dialogue
        confirmationQuitter.setHeaderText("Êtes-vous sûr de vouloir quitter le jeu ?"); // Définir le texte d'en-tête de la boîte de dialogue
        confirmationQuitter.setContentText("Cliquez sur OK pour quitter, ou sur Annuler pour rester dans le jeu."); // Définir le texte de contenu de la boîte de dialogue
        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        if(confirmationQuitter.showAndWait().orElse(null) == ButtonType.OK){
            JeuSerpentEtEchelleApplication.stage.close(); // Fermer l'application si l'utilisateur confirme qu'il veut quitter
        }else{
            confirmationQuitter.close(); // Fermer la boîte de dialogue si l'utilisateur annule l'action de quitter
        }
    }
}
