package controller;

import java.time.LocalDate;

/**
 * Classe Match
 * Représente un match avec un identifiant, une date, une équipe et un adversaire.
 */
public class Match {

    /** Identifiant unique du match. */
    private int id;

    /** Date du match. */
    private LocalDate date;

    /** Identifiant de l'équipe adverse. */
    private int adversaire;

    /** Identifiant de l'équipe jouant le match. */
    private int equipe;

    /**
     * Constructeur avec tous les paramètres.
     * @param id Identifiant unique du match.
     * @param date Date du match.
     * @param adversaire Identifiant de l'équipe adverse.
     * @param equipe Identifiant de l'équipe jouant le match.
     */
    public Match(int id, LocalDate date, int adversaire, int equipe) {
        this.id = id;
        this.date = date;
        this.adversaire = adversaire;
        this.equipe = equipe;
    }

    /**
     * Constructeur sans date ni identifiant.
     * @param adversaire Identifiant de l'équipe adverse.
     * @param equipe Identifiant de l'équipe jouant le match.
     */
    public Match(int adversaire, int equipe) {
        this.adversaire = adversaire;
        this.equipe = equipe;
    }

    /**
     * Constructeur sans identifiant.
     * @param date Date du match.
     * @param adversaire Identifiant de l'équipe adverse.
     * @param equipe Identifiant de l'équipe jouant le match.
     */
    public Match(LocalDate date, int adversaire, int equipe) {
        this.date = date;
        this.adversaire = adversaire;
        this.equipe = equipe;
    }

    /** @return Identifiant du match. */
    public int getId() {
        return id;
    }

    /** @return Date du match. */
    public LocalDate getDate() {
        return date;
    }

    /** @return Identifiant de l'équipe adverse. */
    public int getAdversaire() {
        return adversaire;
    }

    /** @return Identifiant de l'équipe jouant le match. */
    public int getEquipe() {
        return equipe;
    }

    /** @param date Nouvelle date du match. */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /** @param adversaire Nouvel identifiant de l'équipe adverse. */
    public void setAdversaire(int adversaire) {
        this.adversaire = adversaire;
    }

    /** @param equipe Nouvel identifiant de l'équipe jouant le match. */
    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }
}
