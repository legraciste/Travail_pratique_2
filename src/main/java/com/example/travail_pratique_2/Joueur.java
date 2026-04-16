package com.example.travail_pratique_2;

import java.util.Random;

/**
 * Classe représentant un joueur du jeu Serpents et Échelles.
 * Chaque joueur a :
 * - Une position sur la grille (0 à 100)
 * - Un identifiant unique ('J' pour le joueur humain, 'A' pour l'ordinateur)
 * - La capacité à lancer un dé (valeur entre 1 et 6)
 * @author Joseph Legraciste Kamdem
 * @version 1.0
 **/
public class Joueur {
    private int position;
    private final char id;
    private final Random random;

    /**
     * Initialise un joueur avec son identifiant.
     * La position de départ est 0 (avant la grille de jeu).
     *
     * @param id l'identifiant du joueur ('J' pour le joueur humain, 'A' pour l'ordinateur)
     **/
    public Joueur(char id) {
        // Stocker l'identifiant unique du joueur ('J' ou 'A')
        this.id = id;

        // Initialiser la position de départ à 0 (hors de la grille)
        // Le premier lancer de dé le fera entrer sur la grille
        this.position = 0;

        // Créer un générateur de nombres aléatoires pour simuler le lancer de dé
        this.random = new Random();
    }

    /**
     * Lance le dé et retourne un nombre aléatoire entre 1 et 6.
     *
     * @return le résultat du lancer du dé (1, 2, 3, 4, 5 ou 6)
     **/
    public int lancerLeDe() {
        // Générer un nombre aléatoire entre 0 et 5 avec nextInt(6)
        // Puis ajouter 1 pour obtenir un résultat entre 1 et 6
        return random.nextInt(6) + 1;
    }

    /**
     * Définit la nouvelle position du joueur sur la grille.
     *
     * @param nouvellePosition la position à définir (0-100)
     **/
    public void setPosition(int nouvellePosition) {
        // Mettre à jour la position du joueur avec la nouvelle valeur
        // Utilisé après un lancer de dé, une montée d'échelle, une descente de serpent, etc.
        this.position = nouvellePosition;
    }

    /**
     * Obtient la position actuelle du joueur sur la grille.
     *
     * @return la position du joueur (0-100)
     **/
    public int getPosition() {
        // Retourner la position actuelle du joueur
        // 0 = hors grille (départ), 1-99 = en jeu, 100 = victoire
        return position;
    }

    /**
     * Obtient l'identifiant unique du joueur.
     *
     * @return le caractère identifiant le joueur ('J' ou 'A')
     **/
    public char getId() {
        // Retourner l'identifiant du joueur
        // 'J' = Joueur humain, 'A' = adversaire (ordinateur)
        return id;
    }
}
