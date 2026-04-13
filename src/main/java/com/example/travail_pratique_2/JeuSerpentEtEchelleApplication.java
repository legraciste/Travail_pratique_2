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
        stage.setScene(accueil);
        stage.show();
    }

    public static void changementScene(String sceneDesiree, String titreScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JeuSerpentEtEchelleApplication.class.getResource(sceneDesiree + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), LARGEUR_APPLICATION, HAUTEUR_APPLICATION);
        stage.setTitle(titreScene);
        stage.setScene(scene);
        stage.show();

        switch(sceneDesiree)
        {
            case "menu-principal":
                stage.setTitle("Menu Principal");

                break;
        }
    }
}
