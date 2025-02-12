package controller;

/**
 * Classe Staff
 * Représente un membre du staff avec un identifiant, un nom, un rôle, une spécialité et une équipe associée.
 * @version 1.1
 */
public class Staff {

    /** Identifiant unique du membre du staff. */
    private int id;    

    /** Nom du membre du staff. */
    private String nom;    

    /** Rôle du membre du staff. */
    private String role;

    /** Spécialité du membre du staff. */
    private String specialite;

    /** Identifiant de l'équipe à laquelle le membre du staff appartient. */
    private int equipe_id;

    /**
     * Constructeur avec tous les paramètres.
     * @param id Identifiant unique.
     * @param nom Nom du membre du staff.
     * @param specialite Spécialité du membre du staff.
     * @param role Rôle du membre du staff.
     * @param equipe_id Identifiant de l'équipe.
     */
    public Staff(int id, String nom, String specialite, String role, int equipe_id) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.role = role;
        this.equipe_id = equipe_id;
    }

    /**
     * Constructeur sans identifiant.
     * @param nom Nom du membre du staff.
     * @param specialite Spécialité du membre du staff.
     * @param role Rôle du membre du staff.
     * @param equipe_id Identifiant de l'équipe.
     */
    public Staff(String nom, String specialite, String role, int equipe_id) {
        this.nom = nom;
        this.specialite = specialite;
        this.role = role;
        this.equipe_id = equipe_id;
    }

    /**
     * Constructeur sans identifiant ni équipe.
     * @param nom Nom du membre du staff.
     * @param specialite Spécialité du membre du staff.
     * @param role Rôle du membre du staff.
     */
    public Staff(String nom, String specialite, String role) {
        this.nom = nom;
        this.specialite = specialite;
        this.role = role;
        this.equipe_id = 0;
    }

    /** @return Identifiant du membre du staff. */
    public int getId() {
        return id;
    }

    /** @return Nom du membre du staff. */
    public String getNom() {
        return nom;
    }

    /** @param nom Nouveau nom du membre du staff. */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** @return Rôle du membre du staff. */
    public String getRole() {
        return role;
    }

    /** @return Spécialité du membre du staff. */
    public String getSpecialite() {
        return specialite;
    }

    /** @return Identifiant de l'équipe du membre du staff. */
    public int getEquipe_id() {
        return equipe_id;
    }

    /** @param equipe_id Nouvel identifiant de l'équipe du membre du staff. */
    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    /** @param role Nouveau rôle du membre du staff. */
    public void setRole(String role) {
        this.role = role;
    }

    /** @param specialite Nouvelle spécialité du membre du staff. */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du membre du staff.
     * @return Une chaîne de caractères représentant le membre du staff.
     */
    @Override
    public String toString() {
        return "Membre du Staff [ID : " + id + " - Nom : " + nom + 
               ", Rôle : " + role + ", Spécialité : " + specialite + "]";
    }
}
