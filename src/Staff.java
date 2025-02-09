
/**
 * Classe Staff
 * @version 1.1
 * */

public class Staff {

	/** 
	 * Id de Joueur
	 */
	private int id;		
	/**
	 * nom
	 */
	private String nom;	
	/**
	 * Role
	 */
	private String role;
	/**
	 * specialite du membre de staff
	 */
	private String specialite;
	

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
	public Staff(int id,String nom,String specialite,String role, int equipe_id) {
		this.id = id;
		this.nom=nom;
		this.specialite = specialite;
		this.role = role;
		this.equipe_id = equipe_id;
	}
	/**
	 * Constructeur
	 * @param reference référence de l'article
	 * @param designation désignation
	 * @param puHt prix unitaire hors taxe
	 * @param qteStock quantité en stock
	 */
	public Staff(String nom,String specialite,String role, int equipe_id) {
		this.nom=nom;
		this.specialite = specialite;
		this.role = role;
		this.equipe_id = equipe_id;
	}
	
	
	
	/**
	 * Constructeur - ni la référence ni la qte en stock ne sont fixées dans le programme
	 * @param designation désignation de l'article
	 * @param puHt prix unitaire hors taxe
	 */
	public Staff(String nom,String specialite,String role) {
		this.nom=nom;
		this.specialite = specialite;
		this.role = role;
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
	
	public void setEquipe_id(int equipe_id) {
		this.equipe_id = equipe_id;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @return the specialite
	 */
	public String getSpecialite() {
		return specialite;
	}
	/**
	 * @return the equipe_id
	 */
	public int getEquipe_id() {
		return equipe_id;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @param specialite the specialite to set
	 */
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	public String toString() {
		return "Membre du Staff [ID : " + Integer.toString(id) + " - Nom : " + nom
				+ "Role :" + role + " Specialite : " + specialite + "]";
	}
}
