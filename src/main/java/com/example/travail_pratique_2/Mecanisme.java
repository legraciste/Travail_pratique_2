package com.example.travail_pratique_2;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Classe responsable de la gestion de la logique des événements du jeu.
 * Gère :
 * - L'application des échelles (montées).
 * - L'application des serpents (descentes).
 * - La vérification des conditions de victoire
 * - La gestion des collisions entre joueurs
 * @author Joseph Legraciste Kamdem
 * @version 1.0
 **/

public class Mecanisme {
    /**
     * Vérifie si le joueur est sur une échelle ou un serpent et applique l'effet correspondant.
     * Une échelle fait monter le joueur.
     * Un serpent fait descendre le joueur.
     *
     * @param joueur le joueur à vérifier
     * @return un boolean indiquant si une échelle ou un serpent a été appliqué (true) ou non (false)
     **/
    public static void surCaseEchelleOuSerpent(Button pion, Joueur joueur) {
        if (JeuController.SERPENTS_ET_ECHELLES.get(joueur.getPosition()) != null) {
            joueur.setPosition(JeuController.SERPENTS_ET_ECHELLES.get(joueur.getPosition()));
            deplacerPion(pion, joueur);
        }
    }

    public static void deplacerPion(Button pion, Joueur joueur) {

        JeuController controller = JeuController.getInstance();

        // 1. Retirer le pion de son ancienne case
        if (pion.getParent() != null) {
            controller.gridPaneGrilleDeJeu.getChildren().remove(pion);
        }

        // 2. Calculer la position zigzag
        int position = joueur.getPosition() - 1; // position 1 correspond à l'index 0

        int ligne = 9 - (position / 10); // ligne du bas = 9
        int colonne;

        if ((9 - ligne) % 2 == 0) {
            // ligne normale (gauche → droite)
            colonne = position % 10;
        } else {
            // ligne zigzag (droite → gauche)
            colonne = 9 - (position % 10);
        }

        // 4. Centrer le pion dans la cellule
        GridPane.setHalignment(pion, HPos.CENTER);
        GridPane.setValignment(pion, VPos.CENTER);


        // 3. Ajouter le pion dans la nouvelle case
        controller.gridPaneGrilleDeJeu.add(pion, colonne, ligne);
    }

    /**
     * Vérifie si le joueur a gagné la partie en atteignant la case 100.
     * Si le joueur dépasse la case 100, sa position est ajustée à exactement 100.
     *
     * @param joueur le joueur à vérifier
     * @return true si le joueur a gagné, false sinon
     **/
    public static boolean joueurVainqueur(Joueur joueur) {
        // Vérifier si le joueur a atteint ou dépassé la case 100
        if (joueur.getPosition() >= 100) {
            // Forcer la position à exactement 100 (au cas où elle serait supérieure)
            joueur.setPosition(100);
            // Le joueur a gagné
            return true;
        } else {
            // Le joueur n'a pas encore gagné
            return false;
        }
    }

    /**
     * Gère la collision entre deux joueurs.
     * Si les deux joueurs sont sur la même case, le premier joueur avance d'une case.
     * Si la nouvelle case contient une échelle ou un serpent, l'effet est appliqué automatiquement.
     *
     * @param joueur1 le premier joueur (celui qui se déplace en priorité)
     * @param joueur2 le deuxième joueur
     * @return le message décrivant l'événement après collision, ou "non" si aucun événement
     **/
    public static void gererCollision(Joueur joueur1, Button pion, Joueur joueur2) {
        // Vérifier si les deux joueurs sont sur la même case
        if (joueur1.getPosition() == joueur2.getPosition()) {
            // Déplacer le joueur1 d'une case supplémentaire (+1) pour éviter la collision
            joueur1.setPosition(joueur1.getPosition() + 1);

            // Vérifier si la nouvelle position contient une échelle ou un serpent
            // et retourner le message correspondant (ou "non" si pas d'événement)
            surCaseEchelleOuSerpent(pion, joueur1);
        }
    }
}



