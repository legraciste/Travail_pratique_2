package com.example.travail_pratique_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class JeuController {

    @FXML
    private GridPane board;

    @FXML
    private Button boutonLancerDe;

    @FXML
    private ImageView imageFaceDuDe;

    @FXML
    private Button boutonRecommencerPartie;

    @FXML
    private Button boutonRetourAccueil;

    @FXML
    private Label labelTourDuJoueur;

    @FXML
    void onBtnLancerDeAction(ActionEvent event) {
        int numeroDuLancer = new Random().nextInt(6) + 1; // Simule le lancer de dé (1 à 6)
        try {
            String path = "/images/Face_de_de_numero_" + numeroDuLancer + ".png";
            System.out.println("Tentative de chargement de l'image : " + path);
            Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
            imageFaceDuDe.setPreserveRatio(true); // Préserver les proportions pour éviter la distorsion
            imageFaceDuDe.setImage(image); //Affichage de l'image du dé correspondant au lancer
            System.out.println("Image chargée avec succès pour le numéro : " + numeroDuLancer);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image pour le numéro " + numeroDuLancer + " : " + e.getMessage());
            e.printStackTrace();
        }
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
