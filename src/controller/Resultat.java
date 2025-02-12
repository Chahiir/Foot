package controller;

/**
 * Classe Resultat
 * Représente le résultat d'un match avec un identifiant, les scores des équipes et l'identifiant du match.
 */
public class Resultat {

    /** Identifiant unique du résultat. */
    private int id;

    /** Score de l'équipe. */
    private int score_equipe = 0;

    /** Score de l'équipe adverse. */
    private int score_adversaire = 0;

    /** Identifiant du match associé au résultat. */
    private int match_id;

    /**
     * Constructeur avec tous les paramètres.
     * @param id Identifiant du résultat.
     * @param score_equipe Score de l'équipe.
     * @param score_adversaire Score de l'équipe adverse.
     * @param match_id Identifiant du match.
     */
    public Resultat(int id, int score_equipe, int score_adversaire, int match_id) {
        this.id = id;
        this.score_equipe = score_equipe;
        this.score_adversaire = score_adversaire;
        this.match_id = match_id;
    }

    /**
     * Constructeur sans identifiant.
     * @param score_equipe Score de l'équipe.
     * @param score_adversaire Score de l'équipe adverse.
     * @param match_id Identifiant du match.
     */
    public Resultat(int score_equipe, int score_adversaire, int match_id) {
        this.score_equipe = score_equipe;
        this.score_adversaire = score_adversaire;
        this.match_id = match_id;
    }

    /** @return Identifiant du résultat. */
    public int getId() {
        return id;
    }

    /** @return Score de l'équipe. */
    public int getScore_equipe() {
        return score_equipe;
    }

    /** @return Score de l'équipe adverse. */
    public int getScore_adversaire() {
        return score_adversaire;
    }

    /** @return Identifiant du match. */
    public int getMatch_id() {
        return match_id;
    }

    /** @param score_equipe Nouveau score de l'équipe. */
    public void setScore_equipe(int score_equipe) {
        this.score_equipe = score_equipe;
    }

    /** @param score_adversaire Nouveau score de l'équipe adverse. */
    public void setScore_adversaire(int score_adversaire) {
        this.score_adversaire = score_adversaire;
    }

    /** @param match_id Nouvel identifiant du match. */
    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
