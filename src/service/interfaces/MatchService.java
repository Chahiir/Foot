/**
 * 
 */
package service.interfaces;

import java.sql.Date;
import java.util.List;

import controller.Match;
import dao.MatchDAO;

/**
 * 
 */
public class MatchService {

	public Match getMatch(int id) {
		MatchDAO matchDAO = new MatchDAO();
		return matchDAO.getMatch(id);
	}
	
	public Match getMatchByEquipes(int equipe, int adversaire) {
		MatchDAO matchDAO = new MatchDAO();
		return matchDAO.getMatch(equipe,adversaire);
	}
	
	public Match getMatchByDate(Date date) {
		MatchDAO matchDAO = new MatchDAO();
		return matchDAO.getMatchByDate(date);
	}
	
	public List<Match> getAllMatch(){
		MatchDAO matchDAO = new MatchDAO();
		return matchDAO.getAllMatch();
	}
	
	public void addMatch(Match match) {
		MatchDAO matchDAO = new MatchDAO();
		matchDAO.ajouter(match);
	}
	
	public void deleteMatch(int id) {
		MatchDAO matchDAO = new MatchDAO();
		matchDAO.deleteMatch(id);
	}
	
	
}
