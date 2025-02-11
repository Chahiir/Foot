package controller;


/**
 * Classe Joueur
 * Représente un joueur avec un identifiant, un nom, une position, un âge, un prix et une équipe associée.
 * @version 1.1
 */
public class Joueur {

    /** 
     * Identifiant unique du joueur.
     */
    private int id;

    /**
     * Nom du joueur.
     */
    private String nom;
    
    /**
     * Prenom du Joueur
     */
    
    private String prenom;

    /**
     * Âge du joueur.
     */
    private int age;

    /**
     * Position du joueur sur le terrain.
     */
    private String position;

    /**
     * Prix du joueur en millions d'euros.
     */
    private int prix;

    /**
     * est-ce que le joueur est a vendre
     * 
     */
    private boolean aVendre;
    /**
     * Identifiant de l'équipe à laquelle le joueur appartient.
     */
    private int equipe_id;

    /**
     * Constructeur complet pour initialiser un joueur.
     * @param id Identifiant du joueur.
     * @param nom Nom du joueur.
     * @param position Position du joueur.
     * @param age Âge du joueur.
     * @param prix Prix du joueur en millions d'euros.
     * @param equipe_id Identifiant de l'équipe à laquelle le joueur appartient.
     */
    public Joueur(int id, String nom,String prenom, String position, int age, int prix, boolean aVendre, int equipe_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.position = position;
        this.age = age;
        this.prix = prix;
        this.aVendre = aVendre;
        this.equipe_id = equipe_id;
    }
    
    /**
     * Constructeur par defaut sans parametre
     */
    public Joueur() {
    	this.nom = "";
    	this.prenom = "";
    	this.position = "";
    	this.age = 0;
    	this.prix = 0;
    }
    
    /**
     * Constructeur pour initialiser un joueur sans spécifier l'identifiant.
     * @param nom Nom du joueur.
     * @param position Position du joueur.
     * @param age Âge du joueur.
     * @param prix Prix du joueur en millions d'euros.
     * @param equipe_id Identifiant de l'équipe à laquelle le joueur appartient.
     */
    public Joueur(String nom,String prenom, String position, int age, int prix, int equipe_id) {
        this.nom = nom;
        this.prenom = prenom;
        this.position = position;
        this.age = age;
        this.prix = prix;
        this.equipe_id = equipe_id;
    }

    /**
     * Constructeur pour initialiser un joueur sans spécifier l'identifiant ni l'équipe.
     * L'identifiant de l'équipe est initialisé à 0 par défaut.
     * @param nom Nom du joueur.
     * @param position Position du joueur.
     * @param age Âge du joueur.
     * @param prix Prix du joueur en millions d'euros.
     */
    public Joueur(String nom,String prenom, String position, int age, int prix) {
        this.nom = nom;
        this.prenom = prenom;
        this.position = position;
        this.age = age;
        this.prix = prix;
        this.equipe_id = 0;
    }

    /**
     * Getter pour l'identifiant du joueur.
     * @return Identifiant du joueur.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pour le nom du joueur.
     * @return Nom du joueur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour le nom du joueur.
     * @param nom Nouveau nom du joueur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour le prenom du joueur.
	 * @return prenom du joueur 
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Setter pour le nom du joueur.
	 * @param prenom du nouveau joueur
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
     * Getter pour le prix du joueur.
     * @return Prix du joueur en millions d'euros.
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Setter pour le prix du joueur.
     * @param prix Nouveau prix du joueur en millions d'euros.
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
	 * @return the aVendre
	 */
	public boolean isaVendre() {
		return aVendre;
	}

	/**
	 * @param aVendre the aVendre to set
	 */
	public void setaVendre(boolean aVendre) {
		this.aVendre = aVendre;
	}

	/**
     * Getter pour l'âge du joueur.
     * @return Âge du joueur.
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter pour l'âge du joueur.
     * @param age Nouvel âge du joueur.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter pour la position du joueur.
     * @return Position du joueur.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Setter pour la position du joueur.
     * @param position Nouvelle position du joueur.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Getter pour l'identifiant de l'équipe du joueur.
     * @return Identifiant de l'équipe du joueur.
     */
    public int getEquipe_id() {
        return equipe_id;
    }

    /**
     * Setter pour l'identifiant de l'équipe du joueur.
     * @param equipe_id Nouvel identifiant de l'équipe du joueur.
     */
    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    /**
     * Redéfinition de la méthode toString pour afficher les informations du joueur.
     * @return Une chaîne de caractères représentant le joueur.
     */
    @Override
    public String toString() {
        return "Joueur [ID : " + id + " - Nom : " + nom + ", Position : " + position + 
               ", Âge : " + age + ", Prix : " + prix + "M €, Équipe ID : " + equipe_id + "]";
    }
}