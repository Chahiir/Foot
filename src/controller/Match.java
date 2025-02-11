/**
 * 
 */
package controller;

import java.time.LocalDate;

/**
 * 
 */
public class Match {

	private int id;
	private LocalDate date; 
	private int adversaire;
	private int equipe;
	/**
	 * @param id
	 * @param date
	 * @param adversaire
	 * @param equipe
	 */
	public Match(int id, LocalDate date, int adversaire, int equipe) {
		super();
		this.id = id;
		this.date = date;
		this.adversaire = adversaire;
		this.equipe = equipe;
	}
	
	/**
	 * @param adversaire
	 * @param equipe
	 */
	public Match(int adversaire, int equipe) {
		super();
		this.adversaire = adversaire;
		this.equipe = equipe;
	}

	/**
	 * @param date
	 * @param adversaire
	 * @param equipe
	 */
	public Match(LocalDate date, int adversaire, int equipe) {
		super();
		this.date = date;
		this.adversaire = adversaire;
		this.equipe = equipe;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @return the adversaire
	 */
	public int getAdversaire() {
		return adversaire;
	}
	/**
	 * @return the equipe
	 */
	public int getEquipe() {
		return equipe;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @param adversaire the adversaire to set
	 */
	public void setAdversaire(int adversaire) {
		this.adversaire = adversaire;
	}
	/**
	 * @param equipe the equipe to set
	 */
	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}
	
}
