/**
 * 
 */
package service.interfaces;

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
