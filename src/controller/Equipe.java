package controller;

/**
 * Classe Equipe
 * Représente une équipe avec un identifiant, un nom et un solde.
 * @version 1.1
 */
public class Equipe {

    /** 
     * Identifiant unique de l'équipe.
     */
    private int id;

    /**
     * Nom de l'équipe.
     */
    private String nom;

    /**
     * Solde disponible pour l'équipe (en millions d'euros).
     */
    private int solde;

    /**
     * Constructeur complet pour initialiser une équipe.
     * @param id Identifiant de l'équipe.
     * @param nom Nom de l'équipe.
     * @param solde Solde de l'équipe (en millions d'euros).
     */
    public Equipe(int id, String nom, int solde) {
        this.id = id;
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Constructeur pour initialiser une équipe sans spécifier l'identifiant.
     * @param nom Nom de l'équipe.
     * @param solde Solde de l'équipe (en millions d'euros).
     */
    public Equipe(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Constructeur pour initialiser une équipe avec un nom uniquement.
     * Le solde est initialisé à 0 par défaut.
     * @param nom Nom de l'équipe.
     */
    public Equipe(String nom) {
        this.nom = nom;
        this.solde = 0;
    }

    /**
     * Getter pour l'identifiant de l'équipe.
     * @return Identifiant de l'équipe.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pour le nom de l'équipe.
     * @return Nom de l'équipe.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour le nom de l'équipe.
     * @param nom Nouveau nom de l'équipe.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour le solde de l'équipe.
     * @return Solde de l'équipe (en millions d'euros).
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Setter pour le solde de l'équipe.
     * @param solde Nouveau solde de l'équipe (en millions d'euros).
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations de l'équipe.
     * @return Une chaîne de caractères représentant l'équipe.
     */
    @Override
    public String toString() {
        return "Equipe [ID : " + id + " - " + nom + ", " + solde + "M €]";
    }
}