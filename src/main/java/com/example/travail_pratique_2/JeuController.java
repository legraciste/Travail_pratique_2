package com.example.travail_pratique_2;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.util.Duration;
import java.net.URL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller pour la page de jeu du serpent et échelle.
 * Ce controller gère la logique du jeu, y compris les interactions du joueur,
 * les déplacements des pions, les événements liés aux échelles et serpents, et
 * la gestion des tours entre le joueur humain et l'ordinateur.
 * Il utilise des éléments de l'interface utilisateur définis dans
 * le fichier FXML associé (jeu-view.fxml) pour afficher la grille de jeu,
 * les pions, les messages d'information, et les boutons d'action.
 * @author Joseph Legraciste Kamdem
 * @version 1.0
 * **/
public class JeuController implements Initializable {

    private static JeuController instance; // Instance statique pour permettre l'accès à ce controller depuis d'autres classes (ex: Mecanisme)

    @FXML
    GridPane gridPaneGrilleDeJeu; // Grille de jeu où les pions seront déplacés

    static final HashMap<Integer, Integer> SERPENTS_ET_ECHELLES = new HashMap<>(); // Map statique pour stocker les positions des serpents et échelles, accessible depuis d'autres classes (ex: Mecanisme)

    private Joueur joueur = new Joueur('J'); // Joueur humain, représenté par le pion vert
    private Joueur ordinateur = new Joueur('A'); // Joueur ordinateur, représenté par le pion rouge
    Button pionVert; // Pion du joueur humain
    Button pionRouge; // Pion de l'ordinateur

    // Style CSS commun pour les pions, avec une partie dynamique pour la couleur de fond
    String styleDesPions = "-fx-text-fill: black;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 16px;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-border-radius: 50%;" +
                    "-fx-border-color: black;" +
                    "-fx-border-width: 2px;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 4, 0.5, 0, 2);" +
                    "-fx-alignment: center;" +
                    "-fx-background-color: "; // La couleur de fond sera ajoutée dynamiquement pour chaque pion

    boolean tourDuJoueur = true; // true si c'est le tour du joueur, false pour l'ordinateur

    @FXML
    private ImageView imageFaceDuDe; // ImageView pour afficher la face du dé correspondant au résultat du lancer de dé

    @FXML
    private Label labelTourDuJoueur; // Label pour indiquer le tour actuel (joueur ou ordinateur)

    @FXML
    TextArea txtAreaMessage; // TextArea pour afficher les messages d'information sur les déplacements, les événements (échelles/serpents) et les collisions

    /**
     * Constructeur du controller de jeu. Initialise l'instance statique
     * pour permettre l'accès à ce controller depuis d'autres classes (ex: Mecanisme).
     * **/
    public JeuController() {
        instance = this;
    }

    /**
     * Méthode statique pour accéder à l'instance du controller de jeu.
     * **/
    public static JeuController getInstance() {
        return instance;
    }

    /**
     * Initialise les éléments du jeu, y compris les positions des serpents et échelles, les positions initiales des joueurs, et les styles des pions.
     * Cette méthode est appelée automatiquement par JavaFX après que le fichier FXML a été chargé et que les éléments de l'interface utilisateur ont été injectés.
     * @param url l'URL de la ressource utilisée pour localiser le fichier FXML (non utilisé dans cette méthode)
     * @param resourceBundle le ResourceBundle utilisé pour localiser les chaînes de caractères (non utilisé dans cette méthode)
     * **/
    public void initialize(URL url, ResourceBundle resourceBundle) {

        instance = this;
        Mecanisme.miseAJourDuController();

        // Initialisation des serpents et échelles
        SERPENTS_ET_ECHELLES.put(2, 23);
        SERPENTS_ET_ECHELLES.put(20, 59);
        SERPENTS_ET_ECHELLES.put(52, 72);
        SERPENTS_ET_ECHELLES.put(57, 96);
        SERPENTS_ET_ECHELLES.put(71, 92);
        SERPENTS_ET_ECHELLES.put(98, 40);
        SERPENTS_ET_ECHELLES.put(84, 58);
        SERPENTS_ET_ECHELLES.put(87, 49);
        SERPENTS_ET_ECHELLES.put(73, 15);
        SERPENTS_ET_ECHELLES.put(56, 8);
        SERPENTS_ET_ECHELLES.put(50, 5);
        SERPENTS_ET_ECHELLES.put(43, 17);

        //Initialisation de la position des joueurs
        joueur.setPosition(0);
        ordinateur.setPosition(0);

        //Creation des pions pour les joueurs
        pionVert = new Button(String.valueOf(joueur.getId())); // Pion du joueur humain
        pionRouge = new Button(String.valueOf(ordinateur.getId())); // Pion de l'ordinateur

        // Style du pion du joueur
        pionVert.setPrefSize(35, 35);      // taille du pion du joueur
        pionVert.setStyle(styleDesPions + "#2ecc71;"); // Couleur de fond verte pour le joueur

        // Style du pion de l'ordinateur
        pionRouge.setPrefSize(35, 35);      // taille du pion de l'ordinateur
        pionRouge.setStyle(styleDesPions + "#e74c3c;"); // Couleur de fond rouge pour l'ordinateur
    }

    /**
     * Affiche l'image correspondant au résultat du lancer de dé.
     * @param numeroDuLancer le numéro obtenu lors du lancer de dé (entre 1 et 6)
     * **/
    public void lancementDuDe(int numeroDuLancer) {
        try {
            String path = "/images/Face_de_de_numero_" + numeroDuLancer + ".png"; // Construire le chemin de l'image en fonction du numéro du lancer
            System.out.println("Tentative de chargement de l'image : " + path); // Log pour vérifier le chemin de l'image
            Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm()); // Charger l'image à partir du chemin construit
            imageFaceDuDe.setPreserveRatio(true); // Préserver les proportions pour éviter la distorsion
            imageFaceDuDe.setImage(image); //Affichage de l'image du dé correspondant au lancer
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image pour le numéro " + numeroDuLancer + " : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gère l'action du bouton "Lancer le dé" pour le joueur humain.
     * Flux :
     * 1. Vérifie si c'est le tour du joueur humain (sinon, ignore l'action)
     * 2. Lance le dé pour le joueur humain et affiche l'image correspondante
     * 3. Traite le tour du joueur humain (déplacement, échelles/serpents, collisions)
     * 4. Vérifie si le joueur humain a gagné (position 100) et termine le jeu si c'est le cas
     * 5. Passe au tour de l'ordinateur et affiche le message correspondant
     * 6. Délai de 2 secondes avant de lancer le tour de l'ordinateur pour permettre au joueur de voir le résultat de son lancer de dé et les événements associés avant que l'ordinateur ne joue
     * @param event l'événement déclenché par le clic sur le bouton "Lancer le dé"
     * **/
    @FXML
    void onLancerDeAction(MouseEvent event) throws IOException {

        if (!tourDuJoueur) return; // Si ce n'est pas le tour du joueur humain, ignorer l'action pour éviter les conflits avec le tour de l'ordinateur

        // --- Tour du joueur ---
        int numeroDuLancer = joueur.lancerLeDe(); // Lancer le dé pour le joueur humain (résultat entre 1 et 6)
        lancementDuDe(numeroDuLancer); // Affichage de l'image du dé correspondant au lancer du joueur humain
        traiterTourJoueur(joueur, numeroDuLancer); // Traiter le tour du joueur humain (déplacement, échelles/serpents, collisions)

        // Vérification de la condition de victoire pour le joueur humain (position 100)
        if (Mecanisme.joueurVainqueur(joueur)) {
            labelTourDuJoueur.setStyle("-fx-text-fill: green; -fx-font-size: 24px; -fx-font-weight: bold;"); // Style pour le message de victoire du joueur humain
            labelTourDuJoueur.setText("**Le joueur a gagné**"); // Afficher le message de victoire pour le joueur humain
            terminerJeu(); // Terminer le jeu et afficher le message de fin
            return; // Sortir de la méthode pour éviter de passer au tour de l'ordinateur après que le joueur humain a gagné
        }

        // Passe au tour de l'ordinateur
        tourDuJoueur = false;
        labelTourDuJoueur.setText("**Tour de l'ordinateur**");

        // --- Délai avant le tour de l'ordinateur ---
        PauseTransition pause = getPauseTransition(); // Créer une transition de pause pour introduire un délai avant le tour de l'ordinateur

        pause.play(); // Démarrer la transition de pause
    }

    /**
     * Crée une transition de pause de 2 secondes avant le tour de l'ordinateur.
     * Cette méthode est utilisée pour introduire un délai entre le tour du joueur humain et le tour de l'ordinateur,
     * afin que le joueur puisse voir le résultat de son lancer de dé et les événements associés avant que l'ordinateur ne joue.
     * **/
    private PauseTransition getPauseTransition() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Délai de 2 secondes avant le tour de l'ordinateur

        // Action à effectuer lorsque la pause est terminée (lancement du tour de l'ordinateur)
        pause.setOnFinished(e -> {

            int lancerOrdi = ordinateur.lancerLeDe(); // Lancer le dé pour l'ordinateur (résultat entre 1 et 6)
            lancementDuDe(lancerOrdi); // Affichage de l'image du dé correspondant au lancer de l'ordinateur

            // Traiter le tour de l'ordinateur (déplacement, échelles/serpents, collisions)
            try {
                traiterTourOrdinateur(ordinateur);
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            // Vérification de la condition de victoire pour l'ordinateur (position 100)
            if (Mecanisme.joueurVainqueur(ordinateur)) {
                labelTourDuJoueur.setStyle("-fx-text-fill: red; -fx-font-size: 24px; -fx-font-weight: bold;"); // Style pour le message de victoire du joueur humain
                labelTourDuJoueur.setText("**L'ordinateur a gagné**"); // Afficher le message de victoire pour l'ordinateur
                terminerJeu();
                return;
            }

            // Retour au joueur
            tourDuJoueur = true;
            labelTourDuJoueur.setText("**Tour du joueur**");
        });

        return pause; // Retourner la transition de pause pour pouvoir la démarrer dans la méthode onBtnLancerDeAction
    }

    /**
     * Gère l'action du bouton "Recommencer la partie", qui réinitialise le jeu en rechargeant la page de jeu.
     * Flux :
     * 1. Met à jour le titre de la fenêtre pour refléter la page de jeu
     * 2. Recharge la page de jeu pour réinitialiser tous les éléments du jeu (positions des joueurs, pions, messages, etc.)
     * et permettre au joueur de recommencer une nouvelle partie à partir de zéro
     * **/
    @FXML
    void onBtnRecommencerLaPartieAction(ActionEvent event) throws IOException {
        Alert confirmationRecommencer = getConfirmationRecommencerLaPartie();
        if(confirmationRecommencer.showAndWait().orElse(null) == ButtonType.OK) { // Si l'utilisateur confirme qu'il veut recommencer la partie
            JeuSerpentEtEchelleApplication.stage.setTitle("Jeu"); // Mettre à jour le titre de la fenêtre pour refléter la page de jeu
            JeuSerpentEtEchelleApplication.chargerPage("jeu-view"); // Charger la page de jeu dans la section centrale de l'application pour réinitialiser tous les éléments du jeu et permettre au joueur de recommencer une nouvelle partie à partir de zéro
        }
    }

    /**
     * Méthode privée pour créer et configurer une boîte de dialogue de confirmation pour recommencer la partie.
     * Cette méthode est utilisée dans la méthode onBtnRecommencerLaPartieAction() pour afficher une boîte de dialogue
     * demandant à l'utilisateur s'il est sûr de vouloir recommencer la partie, et pour fournir des options de confirmation (OK) ou d'annulation (Annuler).
     * @return confirmationRecommencer - une instance de la classe Alert configurée pour demander à l'utilisateur s'il est sûr de vouloir recommencer
     * la partie, avec des options de confirmation et d'annulation
     * **/
    private static Alert getConfirmationRecommencerLaPartie() {
        Alert confirmationRecommencer = getConfirmationQuitterLaParite(); // Créer une boîte de dialogue de confirmation pour recommencer la partie
        confirmationRecommencer.setTitle("Confirmation de recommencer la partie"); // Définir le titre de la boîte de dialogue pour refléter l'action de recommencer la partie
        confirmationRecommencer.setHeaderText("Êtes-vous sûr de vouloir recommencer la partie ?"); // Définir le texte d'en-tête de la boîte de dialogue pour refléter l'action de recommencer la partie
        confirmationRecommencer.setContentText("Cliquez sur OK pour recommencer, ou sur Annuler pour continuer la partie en cours."); // Définir le texte de contenu de la boîte de dialogue pour refléter l'action de recommencer la partie
        return confirmationRecommencer;
    }

    /**
     * Gère l'action du bouton "Retourner à l'accueil", qui ramène le joueur à la page d'accueil de l'application.
     * Flux :
     * 1. Met à jour le titre de la fenêtre pour refléter la page d'accueil
     * 2. Recharge la page d'accueil pour permettre au joueur de revenir à la page d'accueil de l'application,
     * où il peut choisir de lire les règles du jeu, recommencer une partie, ou quitter le jeu, etc.
     * **/
    @FXML
    void onBtnQuitterLaPartieAction(ActionEvent event) throws IOException {
        Alert confirmationQuitter = getConfirmationQuitterLaParite(); // Créer une boîte de dialogue de confirmation pour quitter la partie
        if(confirmationQuitter.showAndWait().orElse(null) == ButtonType.OK) { // Si l'utilisateur confirme qu'il veut quitter la partie
            JeuSerpentEtEchelleApplication.stage.setTitle("Accueil"); // Mettre à jour le titre de la fenêtre pour refléter la page d'accueil
            JeuSerpentEtEchelleApplication.chargerPage("accueil-view"); // Charger la page d'accueil dans la section centrale de l'application
        }
    }

    /**
     * Méthode privée pour créer et configurer une boîte de dialogue de confirmation pour quitter la partie.
     * Cette méthode est utilisée dans la méthode onBtnQuitterLaPartieAction() pour afficher une boîte de
     * dialogue demandant à l'utilisateur s'il est sûr de vouloir quitter la partie, et pour fournir
     * des options de confirmation (OK) ou d'annulation (Annuler).
     * @return confirmationQuitter - une instance de la classe Alert configurée pour demander à l'utilisateur
     * s'il est sûr de vouloir quitter la partie, avec des options de confirmation et d'annulation
     * **/
    private static Alert getConfirmationQuitterLaParite() {
        Alert confirmationQuitter = new Alert(Alert.AlertType.CONFIRMATION); // Créer une boîte de dialogue de confirmation pour quitter la partie
        confirmationQuitter.setTitle("Confirmation de quitter la partie"); // Définir le titre de la boîte de dialogue
        confirmationQuitter.setHeaderText("Êtes-vous sûr de vouloir quitter la partie ?"); // Définir le texte d'en-tête de la boîte de dialogue
        confirmationQuitter.setContentText("Cliquez sur OK pour quitter, ou sur Annuler pour rester dans la partie."); // Définir le texte de contenu de la boîte de dialogue
        return confirmationQuitter;
    }

    /**
     * Traite le tour du joueur humain.
     * Flux :
     * 1. Affiche le menu des actions (L : lancer, Q : quitter)
     * 2. Lit le choix de l'utilisateur
     * 3. Si 'Q' : demande confirmation et quitte si 'O'
     * 4. Si 'L' : lance le dé, déplace le joueur, vérifie les événements et collisions
     **/
    private void traiterTourJoueur(Joueur joueur, int resultatDuLance) throws IOException {
        // Indicateur pour savoir si le tour du joueur est terminé
        boolean tourTermine = false;

        // Boucle jusqu'à ce que le joueur ait lancé le dé
        while (!tourTermine) {
            // Calcul de la nouvelle position (position actuelle + résultat du dé)
            // Math.min() assure qu'on ne dépasse pas la case 100
            int nouvellePosition = Math.min(resultatDuLance + joueur.getPosition(), 100);

            txtAreaMessage.setText(txtAreaMessage.getText() + "-Le Joueur\n se déplace\n de " + joueur.getPosition() + " à " + nouvellePosition + "\n");

            // Mise à jour de la position du joueur
            joueur.setPosition(nouvellePosition);

            Mecanisme.surCaseEchelleOuSerpent(pionVert, joueur); // Vérification des événements (échelles/serpents) et déplacement du pion en conséquence

            Mecanisme.deplacerPion(pionVert, joueur); // Déplacement du pion du joueur à sa nouvelle position après avoir vérifié les échelles/serpents

            // Vérification des collisions avec l'ordinateur
            // Si les deux joueurs sont sur la même case, le joueur avance de +1
            if(Mecanisme.gererCollision(joueur, pionVert, ordinateur)) {;
                Mecanisme.deplacerPion(pionVert, joueur); // Si une collision a été gérée, déplacer le pion du joueur à sa nouvelle position
            }

            // Le dé a été lancé, le tour est terminé
            tourTermine = true;
        }
    }

    /**
     * Traite le tour de l'ordinateur automatiquement.
     * Flux :
     * 1. Affiche le message du tour de l'ordinateur
     * 2. Lance le dé
     * 3. Déplace l'ordinateur
     * 4. Vérifie les événements (échelles/serpents)
     * 5. Vérifie les collisions avec le joueur
     **/
    private void traiterTourOrdinateur(Joueur ordinateur) throws IOException {

        // L'ordinateur lance automatiquement le dé (résultat entre 1 et 6)
        int resultatDuLance = ordinateur.lancerLeDe();

        lancementDuDe(resultatDuLance); // Affichage de l'image du dé correspondant au lancer de l'ordinateur

        // Calcul de la nouvelle position de l'ordinateur
        // Math.min() assure qu'on ne dépasse pas la case 100
        int nouvellePosition = Math.min(resultatDuLance + ordinateur.getPosition(), 100);
        txtAreaMessage.setText(txtAreaMessage.getText() + "-L'Ordinateur\n se déplace\n de " + ordinateur.getPosition() + " à " + nouvellePosition + "\n");

        // Mise à jour de la position de l'ordinateur
        ordinateur.setPosition(nouvellePosition);

        Mecanisme.surCaseEchelleOuSerpent(pionRouge, ordinateur); // Vérification des événements (échelles/serpents) et déplacement du pion en conséquence

        Mecanisme.deplacerPion(pionRouge, ordinateur); // Déplacement du pion de l'ordinateur à sa nouvelle position après avoir vérifié les échelles/serpents

        // Vérification des collisions avec le joueur humain
        // Si les deux joueurs sont sur la même case, l'ordinateur avance de +1
        if(Mecanisme.gererCollision(ordinateur, pionRouge, joueur)) {;
            Mecanisme.deplacerPion(pionRouge, ordinateur); // Si une collision a été gérée, déplacer le pion de l'ordinateur à sa nouvelle position
        }
    }


    /**
     * Termine le jeu et nettoie les ressources.
     * Ferme le scanner et affiche le message de fin.
     **/
    private void terminerJeu() {
        tourDuJoueur = false; // Désactive les actions du joueur
        txtAreaMessage.setText("Le jeu est terminé.\n Merci d'avoir\n joué !"); // Affiche le message de fin dans la TextArea
    }
}