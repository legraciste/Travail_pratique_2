package com.example.travail_pratique_2;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.net.URL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class JeuController implements Initializable {

    private static JeuController instance;

    @FXML
    GridPane gridPaneGrilleDeJeu;

    static final HashMap<Integer, Integer> SERPENTS_ET_ECHELLES = new HashMap<>();

    private Joueur joueur = new Joueur('J');
    private Joueur ordinateur = new Joueur('A');
    Button pionVert;
    Button pionRouge;

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
    private TextArea txtAreaMessage;

    public JeuController() {
        instance = this;
    }

    public static JeuController getInstance() {
        return instance;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public void lancementDuDe(int numeroDuLancer) {
        try {
            String path = "/images/Face_de_de_numero_" + numeroDuLancer + ".png";
            System.out.println("Tentative de chargement de l'image : " + path);
            Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
            imageFaceDuDe.setPreserveRatio(true); // Préserver les proportions pour éviter la distorsion
            imageFaceDuDe.setImage(image); //Affichage de l'image du dé correspondant au lancer
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image pour le numéro " + numeroDuLancer + " : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void onBtnLancerDeAction(ActionEvent event) throws IOException {

        if (!tourDuJoueur) return;

        // --- Tour du joueur ---
        int numeroDuLancer = joueur.lancerLeDe();
        lancementDuDe(numeroDuLancer);
        traiterTourJoueur(joueur, numeroDuLancer);

        if (Mecanisme.joueurVainqueur(joueur)) {
            labelTourDuJoueur.setText("Félicitations ! Vous avez gagné !");
            terminerJeu();
            return;
        }

        // Passe au tour de l'ordinateur
        tourDuJoueur = false;
        labelTourDuJoueur.setText("**Tour de l'ordinateur**");

        // --- Délai avant le tour de l'ordinateur ---
        PauseTransition pause = getPauseTransition();

        pause.play();
    }

    private PauseTransition getPauseTransition() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Délai de 2 secondes avant le tour de l'ordinateur
        pause.setOnFinished(e -> {

            int lancerOrdi = ordinateur.lancerLeDe();
            lancementDuDe(lancerOrdi);
            try {
                traiterTourOrdinateur(ordinateur);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (Mecanisme.joueurVainqueur(ordinateur)) {
                labelTourDuJoueur.setText("L'ordinateur a gagné. Essayez à nouveau !");
                terminerJeu();
                return;
            }

            // Retour au joueur
            tourDuJoueur = true;
            labelTourDuJoueur.setText("**Tour du joueur**");
        });
        return pause;
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

            Mecanisme.deplacerPion(pionVert, joueur);

            // Vérification des collisions avec l'ordinateur
            // Si les deux joueurs sont sur la même case, le joueur avance de +1
            Mecanisme.gererCollision(joueur, pionVert, ordinateur);

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

        Mecanisme.deplacerPion(pionRouge, ordinateur);

        // Vérification des collisions avec le joueur humain
        // Si les deux joueurs sont sur la même case, l'ordinateur avance de +1
        Mecanisme.gererCollision(ordinateur, pionRouge, joueur);
    }


    /**
     * Termine le jeu et nettoie les ressources.
     * Ferme le scanner et affiche le message de fin.
     **/
    private void terminerJeu() {
        tourDuJoueur = false; // Désactive les actions du joueur
        txtAreaMessage.setText("Le jeu est terminé. Merci d'avoir joué !");
    }
}