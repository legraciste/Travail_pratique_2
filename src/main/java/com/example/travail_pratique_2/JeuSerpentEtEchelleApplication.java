package com.example.travail_pratique_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuSerpentEtEchelleApplication extends Application {

    static Stage stage;

    private static final int LARGEUR_APPLICATION = 800;
    private static final int HAUTEUR_APPLICATION = 600;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JeuSerpentEtEchelleApplication.class.getResource("menu-principal.fxml"));
        Scene accueil = new Scene(fxmlLoader.load(), LARGEUR_APPLICATION, HAUTEUR_APPLICATION);

        JeuSerpentEtEchelleApplication.stage = stage;
        stage.setTitle("Jeu de Serpent et Echelle");

        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setMaxWidth(1200);
        stage.setMaxHeight(700);

        stage.setScene(accueil);
        stage.show();
    }
}
