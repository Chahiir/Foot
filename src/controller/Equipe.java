package controller;

/**
 * Classe représentant une équipe avec un identifiant, un nom et un solde financier.
 * @version 1.1
 */
public class Equipe {

    /** 
     * Identifiant unique de l'équipe (généré par la base de données).
     */
    private int id;

    /**
     * Nom de l'équipe.
     */
    private String nom;

    /**
     * Solde disponible pour l'équipe en millions d'euros.
     */
    private int solde;

    /**
     * Constructeur complet pour initialiser une équipe.
     * @param id Identifiant unique de l'équipe (géré par la base de données).
     * @param nom Nom de l'équipe.
     * @param solde Solde disponible en millions d'euros.
     */
    public Equipe(int id, String nom, int solde) {
        this.id = id;
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Constructeur pour initialiser une équipe sans identifiant (cas où l'ID est auto-généré en base).
     * @param nom Nom de l'équipe.
     * @param solde Solde disponible en millions d'euros.
     */
    public Equipe(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Constructeur minimaliste pour une équipe avec un nom uniquement.
     * Le solde est initialisé à 0 par défaut.
     * @param nom Nom de l'équipe.
     */
    public Equipe(String nom) {
        this.nom = nom;
        this.solde = 0;
    }

    /**
     * Obtient l'identifiant de l'équipe.
     * @return Identifiant unique de l'équipe.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'équipe (utile uniquement si l'ID n'est pas auto-généré).
     * @param id Nouvel identifiant unique de l'équipe.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom de l'équipe.
     * @return Nom de l'équipe.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de l'équipe.
     * @param nom Nouveau nom de l'équipe.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le solde financier de l'équipe.
     * @return Solde disponible en millions d'euros.
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Modifie le solde de l'équipe.
     * @param solde Nouveau solde en millions d'euros.
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations de l'équipe sous un format lisible.
     * @return Une chaîne de caractères représentant l'équipe.
     */
    @Override
    public String toString() {
        return "Equipe [ID : " + id + " - " + nom + ", " + solde + "M €]";
    }
}
