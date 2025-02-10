package dao;

/**
 * Classe Equipe
 * @version 1.1
 * */

public class Equipe {

	/** 
	 * Id de l'equipe
	 */
	private int id;		
	/**
	 * nom
	 */
	private String nom;	
	/**
	 * solde de l'equipe pour acheter joueur avec
	 */
	private int solde;		



	/**
	 * Constructeur
	 * @param reference référence de l'article
	 * @param designation désignation
	 * @param puHt prix unitaire hors taxe
	 * @param qteStock quantité en stock
	 */
	public Equipe(int id,String nom, int solde) {
		this.id = id;
		this.nom=nom;
		this.solde = solde;

	}
	/**
	 * Constructeur
	 * @param reference référence de l'article
	 * @param designation désignation
	 * @param puHt prix unitaire hors taxe
	 * @param qteStock quantité en stock
	 */
	public Equipe(String nom, int solde) {
		this.nom=nom;
		this.solde = solde;

	}
	
	/**
	 * Constructeur - ni la référence ni la qte en stock ne sont fixées dans le programme
	 * @param designation désignation de l'article
	 * @param puHt prix unitaire hors taxe
	 */
	public Equipe(String nom) {
		this.nom = nom;
		this.solde = 0;
	}
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference article
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut désignation
	 * @return valeur de la désignation article
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * setter  pour l'attribut designation
	 * @param designation : nouvelle valeur de la désignation article
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * getter  pour l'attribut puHt
	 * @return valeur de prix unitaire HT
	 */
	public int getSolde() {
		return solde;
	}
	/**
	 * setter  pour l'attribut puHt
	 * @param puHt :  nouvelle valeur de prix unitaire HT
	 */
	public void setSolde(int solde) {
		this.solde = solde;
	}
	

	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	public String toString() {
		return "Equipe [ID : " + Integer.toString(id) + " - " + nom
				+ ", " + Integer.toString(solde) + "M € HT,]";
	}
}
