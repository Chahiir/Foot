package controller;

public class Resultat {

	
	private int id;
	private int score_equipe = 0;
	private int score_adversaire = 0 ;
	private int match_id;
	/**
	 * @param id
	 * @param score_equipe
	 * @param score_adversaire
	 * @param match_id
	 */
	public Resultat(int id, int score_equipe, int score_adversaire, int match_id) {
		super();
		this.id = id;
		this.score_equipe = score_equipe;
		this.score_adversaire = score_adversaire;
		this.match_id = match_id;
	}
	/**
	 * @param score_equipe
	 * @param score_adversaire
	 * @param match_id
	 */
	public Resultat(int score_equipe, int score_adversaire, int match_id) {
		super();
		this.score_equipe = score_equipe;
		this.score_adversaire = score_adversaire;
		this.match_id = match_id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the score_equipe
	 */
	public int getScore_equipe() {
		return score_equipe;
	}
	/**
	 * @return the score_adversaire
	 */
	public int getScore_adversaire() {
		return score_adversaire;
	}
	/**
	 * @return the match_id
	 */
	public int getMatch_id() {
		return match_id;
	}
	/**
	 * @param score_equipe the score_equipe to set
	 */
	public void setScore_equipe(int score_equipe) {
		this.score_equipe = score_equipe;
	}
	/**
	 * @param score_adversaire the score_adversaire to set
	 */
	public void setScore_adversaire(int score_adversaire) {
		this.score_adversaire = score_adversaire;
	}
	/**
	 * @param match_id the match_id to set
	 */
	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}
	
}
