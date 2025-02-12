package controller;

import java.time.LocalDate;

/**
 * Classe History
 * Représente l'historique des transferts d'un joueur entre deux équipes.
 */
public class History {
    
    /** Identifiant unique de l'historique */
    private int id;
    
    /** Identifiant de la nouvelle équipe après transfert */
    private int newEquipe_id;
    
    /** Identifiant de l'ancienne équipe avant transfert */
    private int oldEquipe_id;
    
    /** Identifiant du joueur transféré */
    private int joueur_id;
    
    /** Prix du transfert */
    private int prix;
    
    /** Date du transfert */
    private LocalDate date;

    /**
     * Constructeur avec tous les paramètres.
     * @param id Identifiant unique
     * @param newEquipe_id Nouvelle équipe
     * @param oldEquipe_id Ancienne équipe
     * @param joueur_id Identifiant du joueur
     * @param prix Prix du transfert
     * @param date Date du transfert
     */
    public History(int id, int newEquipe_id, int oldEquipe_id, int joueur_id, int prix, LocalDate date) {
        this.id = id;
        this.prix = prix;
        this.newEquipe_id = newEquipe_id;
        this.oldEquipe_id = oldEquipe_id;
        this.joueur_id = joueur_id;
        this.date = date;
    }

    /**
     * Constructeur sans identifiant ni date (date par défaut à aujourd'hui).
     * @param newEquipe_id Nouvelle équipe
     * @param oldEquipe_id Ancienne équipe
     * @param joueur_id Identifiant du joueur
     * @param prix Prix du transfert
     */
    public History(int newEquipe_id, int oldEquipe_id, int joueur_id, int prix) {
        this.newEquipe_id = newEquipe_id;
        this.oldEquipe_id = oldEquipe_id;
        this.joueur_id = joueur_id;
        this.prix = prix;
        this.date = LocalDate.now();
    }

    /**
     * Constructeur sans date (date par défaut à aujourd'hui).
     * @param id Identifiant unique
     * @param newEquipe_id Nouvelle équipe
     * @param oldEquipe_id Ancienne équipe
     * @param joueur_id Identifiant du joueur
     * @param prix Prix du transfert
     */
    public History(int id, int newEquipe_id, int oldEquipe_id, int joueur_id, int prix) {
        this(id, newEquipe_id, oldEquipe_id, joueur_id, prix, LocalDate.now());
    }

    /**
     * Obtient l'identifiant de l'historique.
     * @return Identifiant unique
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'historique.
     * @param id Nouvel identifiant
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient l'identifiant de la nouvelle équipe.
     * @return Identifiant de la nouvelle équipe
     */
    public int getNewEquipe_id() {
        return newEquipe_id;
    }

    /**
     * Définit l'identifiant de la nouvelle équipe.
     * @param newEquipe_id Nouvel identifiant
     */
    public void setNewEquipe_id(int newEquipe_id) {
        this.newEquipe_id = newEquipe_id;
    }

    /**
     * Obtient l'identifiant de l'ancienne équipe.
     * @return Identifiant de l'ancienne équipe
     */
    public int getOldEquipe_id() {
        return oldEquipe_id;
    }

    /**
     * Définit l'identifiant de l'ancienne équipe.
     * @param oldEquipe_id Nouvel identifiant
     */
    public void setOldEquipe_id(int oldEquipe_id) {
        this.oldEquipe_id = oldEquipe_id;
    }

    /**
     * Obtient l'identifiant du joueur.
     * @return Identifiant du joueur
     */
    public int getJoueur_id() {
        return joueur_id;
    }

    /**
     * Définit l'identifiant du joueur.
     * @param joueur_id Nouvel identifiant
     */
    public void setJoueur_id(int joueur_id) {
        this.joueur_id = joueur_id;
    }

    /**
     * Obtient le prix du transfert.
     * @return Prix du transfert
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Définit le prix du transfert.
     * @param prix Nouveau prix
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Obtient la date du transfert.
     * @return Date du transfert
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Définit la date du transfert.
     * @param date Nouvelle date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations de l'historique.
     * @return Une chaîne représentant l'historique
     */
    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", newEquipe_id=" + newEquipe_id +
                ", oldEquipe_id=" + oldEquipe_id +
                ", joueur_id=" + joueur_id +
                ", prix=" + prix +
                ", date=" + date +
                '}';
    }
}
