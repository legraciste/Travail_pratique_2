package com.example.travail_pratique_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class JeuSerpentEtEchelleApplication extends Application {

    static Stage stage; // Permet de stocker la référence à la scène principale de l'application
    private static JeuSerpentEtEchelleController controller; // Permet de stocker la référence au contrôleur de l'application, qui gère les interactions avec les différentes pages de l'application

    private static final int LARGEUR_APPLICATION = 800; // Constante pour la largeur de la fenêtre de l'application
    private static final int HAUTEUR_APPLICATION = 600; // Constante pour la hauteur de la fenêtre de l'application


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JeuSerpentEtEchelleApplication.class.getResource("menu_principal-view.fxml")); // Charger le fichier FXML du menu principal de l'application, qui est la première page que les utilisateurs voient lorsqu'ils lancent l'application
        Scene accueil = new Scene(fxmlLoader.load(), LARGEUR_APPLICATION, HAUTEUR_APPLICATION); // Créer une nouvelle scène avec le contenu du menu principal et les dimensions spécifiées

        controller = fxmlLoader.getController(); // Récupérer le contrôleur associé au menu principal, qui gère les interactions avec les différentes pages de l'application

        JeuSerpentEtEchelleApplication.stage = stage; // Stocker la référence à la scène principale de l'application dans une variable statique pour pouvoir y accéder depuis d'autres classes de l'application
        stage.setTitle("Jeu de serpent et échelle"); // Définir le titre de la fenêtre de l'application
        JeuSerpentEtEchelleApplication.chargerPage("accueil-view"); // Charger la page d'accueil dans la scène principale de l'application, qui est la première page que les utilisateurs voient lorsqu'ils lancent l'application

        stage.setMinWidth(800); // Définir la largeur minimale de la fenêtre de l'application pour éviter que les utilisateurs ne redimensionnent la fenêtre à une taille trop petite qui pourrait rendre l'interface utilisateur difficile à utiliser
        stage.setMinHeight(600); // Définir la hauteur minimale de la fenêtre de l'application pour éviter que les utilisateurs ne redimensionnent la fenêtre à une taille trop petite qui pourrait rendre l'interface utilisateur difficile à utiliser

        stage.setMaxWidth(1200); // Définir la largeur maximale de la fenêtre de l'application pour éviter que les utilisateurs ne redimensionnent la fenêtre à une taille trop grande qui pourrait rendre l'interface utilisateur difficile à utiliser
        stage.setMaxHeight(700); // Définir la hauteur maximale de la fenêtre de l'application pour éviter que les utilisateurs ne redimensionnent la fenêtre à une taille trop grande qui pourrait rendre l'interface utilisateur difficile à utiliser

        stage.setScene(accueil); // Définir la scène principale de l'application sur la scène du menu principal, qui est la première page que les utilisateurs voient lorsqu'ils lancent l'application
        stage.show(); // Afficher la fenêtre de l'application à l'utilisateur
    }

    /**
     * Méthode statique pour charger une page spécifique dans la section centrale de l'application.
     * Cette méthode est utilisée par les différentes méthodes du contrôleur pour charger les différentes pages de l'application (accueil, règles du jeu, jeu, etc.)
     * dans la section centrale de l'application.
     * @param sceneDesiree le nom de la page à charger (sans l'extension .fxml), par exemple "accueil-view", "regles-view", "jeu-view", etc.
     * **/
    public static void chargerPage(String sceneDesiree) throws IOException {
        // Vérifier que le contrôleur et la section centrale de l'application sont disponibles avant de tenter de charger la page
        if (controller != null && controller.getSectionsApplication() != null) {
            Parent page = FXMLLoader.load(Objects.requireNonNull(JeuSerpentEtEchelleApplication.class.getResource(sceneDesiree + ".fxml"))); // Charger le fichier FXML de la page demandée en utilisant le nom de la page fourni en paramètre
            controller.getSectionsApplication().setCenter(page); // Charger la page demandée dans la section centrale de l'application
        }
    }
}
