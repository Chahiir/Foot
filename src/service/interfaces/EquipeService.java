package service.interfaces;


import controller.Equipe;
import dao.EquipeDAO;

import dao.JoueurDAO;
import controller.Joueur;
import java.util.List;

public class EquipeService {
	public Equipe getEquipeById(int id) {
		EquipeDAO equipeDAO = new EquipeDAO();
		return equipeDAO.getEquipe(id);
	}
	
	public List<Equipe> getAllEquipes(){
		EquipeDAO equipeDAO = new EquipeDAO();
		return equipeDAO.getListeEquipes();
	}

    public void addPlayerToTeam(int playerId, int equipeID) {
    	JoueurDAO joueurDAO = new JoueurDAO();
    	Joueur joueur = joueurDAO.getJoueur(playerId);
    	joueur.setEquipe_id(equipeID);
    }

    public void removePlayerFromTeam(int playerId) {
    	JoueurDAO joueurDAO = new JoueurDAO();
    	Joueur joueur = joueurDAO.getJoueur(playerId);
    	joueur.setEquipe_id(0);
    }

    public void deleteTeamById( int teamId) {
    	EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.deleteEquipe(teamId);    
		}
}
