package controller;

public class Composition {

	
	private int id;
	private int joueur_id;
	private int match_id;
	/**
	 * @param id
	 * @param joueur_id
	 * @param match_id
	 */
	public Composition(int id, int joueur_id, int match_id) {
		super();
		this.id = id;
		this.joueur_id = joueur_id;
		this.match_id = match_id;
	}
	/**
	 * @param joueur_id
	 * @param match_id
	 */
	public Composition(int joueur_id, int match_id) {
		super();
		this.joueur_id = joueur_id;
		this.match_id = match_id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the joueur_id
	 */
	public int getJoueur_id() {
		return joueur_id;
	}
	/**
	 * @return the match_id
	 */
	public int getMatch_id() {
		return match_id;
	}
	/**
	 * @param joueur_id the joueur_id to set
	 */
	public void setJoueur_id(int joueur_id) {
		this.joueur_id = joueur_id;
	}
	/**
	 * @param match_id the match_id to set
	 */
	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}
	
}
