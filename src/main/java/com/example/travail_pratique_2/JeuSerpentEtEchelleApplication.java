package com.example.travail_pratique_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JeuSerpentEtEchelleApplication extends Application {

    static Stage stage;
    private static JeuSerpentEtEchelleController controller;

    private static final int LARGEUR_APPLICATION = 800;
    private static final int HAUTEUR_APPLICATION = 600;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JeuSerpentEtEchelleApplication.class.getResource("menu_principal-view.fxml"));
        Scene accueil = new Scene(fxmlLoader.load(), LARGEUR_APPLICATION, HAUTEUR_APPLICATION);

        controller = fxmlLoader.getController();

        JeuSerpentEtEchelleApplication.stage = stage;
        stage.setTitle("Jeu de serpent et échelle");
        JeuSerpentEtEchelleApplication.chargerPage("accueil-view");

        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.setMaxWidth(1200);
        stage.setMaxHeight(700);

        stage.setScene(accueil);
        stage.show();
    }

    public static void chargerPage(String sceneDesiree) throws IOException {
        if (controller != null && controller.getSectionsApplication() != null) {
            Parent page = FXMLLoader.load(Objects.requireNonNull(JeuSerpentEtEchelleApplication.class.getResource(sceneDesiree + ".fxml")));
            controller.getSectionsApplication().setCenter(page);
        }
    }
}
