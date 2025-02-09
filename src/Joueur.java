
/**
 * Classe Joueur
 * @version 1.1
 * */

public class Joueur {

	/** 
	 * Id de Joueur
	 */
	private int id;		
	/**
	 * nom
	 */
	private String nom;	
	/**
	 * age
	 */
	private int age;
	/**
	 * Position du joueur
	 */
	private String position;
	
	/**
	 * Prix du joueur en M euro
	 */
	private int prix;		

	/**
	 * equipe
	 */
	private int equipe_id;


	/**
	 * Constructeur
	 * @param reference référence de l'article
	 * @param designation désignation
	 * @param puHt prix unitaire hors taxe
	 * @param qteStock quantité en stock
	 */
	public Joueur(int id,String nom,String position,int age, int prix, int equipe_id) {
		this.id = id;
		this.nom=nom;
		this.position = position;
		this.age = age;
		this.prix = prix;
		this.equipe_id = equipe_id;
	}
	/**
	 * Constructeur
	 * @param reference référence de l'article
	 * @param designation désignation
	 * @param puHt prix unitaire hors taxe
	 * @param qteStock quantité en stock
	 */
	public Joueur(String nom,String position,int age, int prix, int equipe_id) {
		this.nom=nom;
		this.position = position;
		this.age = age;
		this.prix = prix;
		this.equipe_id = equipe_id;
	}
	
	
	
	/**
	 * Constructeur - ni la référence ni la qte en stock ne sont fixées dans le programme
	 * @param designation désignation de l'article
	 * @param puHt prix unitaire hors taxe
	 */
	public Joueur(String nom,String position,int age, int prix) {
		this.nom=nom;
		this.position = position;
		this.age = age;
		this.prix = prix;
		this.equipe_id = 0;
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
	public int getPrix() {
		return prix;
	}
	/**
	 * setter  pour l'attribut puHt
	 * @param puHt :  nouvelle valeur de prix unitaire HT
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}
	

	public int getAge() {
		return age;
	}
	public String getPosition() {
		return position;
	}
	public int getEquipe_id() {
		return equipe_id;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setEquipe_id(int equipe_id) {
		this.equipe_id = equipe_id;
	}
	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	public String toString() {
		return "Joueur [ID : " + Integer.toString(id) + " - Nom : " + nom
				+ "Position :" + position + " Age : " + Integer.toString(age) + ", " + Integer.toString(prix) + "M € HT,]";
	}
}
