package service.interfaces;

import dao.EquipeDAO;
import dao.HistoryDAO;
import dao.JoueurDAO;
import java.util.List;

import controller.Equipe;
import controller.History;
import controller.Joueur;

public class JoueurService {

    /**
     * Récupère tous les joueurs de la base de données.
     * @return Une liste de tous les joueurs.
     */
    public List<Joueur> getAllPlayers() {
        JoueurDAO joueur = new JoueurDAO();
        return joueur.getListeJoueurs();
    }

    
    public void addPlayerToTeam(int playerId, int equipeID) {
    	JoueurDAO joueurDAO = new JoueurDAO();
    	Joueur joueur = joueurDAO.getJoueur(playerId);
    	joueur.setEquipe_id(equipeID);
    	joueurDAO.updateJoueur(joueur);
    }

    public void removePlayerFromTeam(int playerId) {
    	JoueurDAO joueurDAO = new JoueurDAO();
    	Joueur joueur = joueurDAO.getJoueur(playerId);
    	joueur.setEquipe_id(0);
    	joueurDAO.updateJoueur(joueur);

    }
    
    
    /**
     * Récupère un joueur par son identifiant.
     * @param id L'identifiant du joueur.
     * @return Le joueur correspondant, ou null si aucun joueur ne correspond.
     */
    public Joueur getPlayerById(int id) {
        JoueurDAO joueur = new JoueurDAO();
        return joueur.getJoueur(id);
    }

    /**
     * Récupère les joueurs correspondant à un filtre textuel (nom, position, etc.).
     * @param queryString Le texte de recherche.
     * @return Une liste de joueurs correspondant au filtre.
     */
    public List<Joueur> getPlayersByTextFilter(String queryString) {
        JoueurDAO joueur = new JoueurDAO();
        return joueur.getJoueursByFilter(queryString);
    }

    /**
     * Met à jour les informations d'un joueur dans la base de données.
     * @param player Le joueur avec les nouvelles informations.
     */
    public void updatePlayer(Joueur player) {
        JoueurDAO joueur = new JoueurDAO();
        joueur.updateJoueur(player);
    }

    /**
     * Ajout d'un joueur dans la BDD
     * 
     * @param newPlayer le joueur a ajouter
     */
    public void addPlayer(Joueur newPlayer) {
    	JoueurDAO joueurDAO = new JoueurDAO();
    	joueurDAO.ajouter(newPlayer);
    }
    /**
     * marquer un joueur a vendre
     * @param id
     */
    	
    public void markPlayerToSell(int id) {
    	JoueurDAO joueurDAO = new JoueurDAO();
    	Joueur joueur = joueurDAO.getJoueur(id);
    	joueur.setaVendre(true);
    	joueurDAO.updateJoueur(joueur);

    }
    
    /**
     * vendre un joueur a une autre equipe	
     * @param playerId
     * @param newTeamId
     * @return
     */
    public boolean transferPlayer(int playerId, int newTeamId) {
    	
    	EquipeDAO equipeDAO = new EquipeDAO();
    	Equipe equipe = equipeDAO.getEquipe(newTeamId);
    	JoueurDAO joueurDAO = new JoueurDAO();
    	Joueur joueur = joueurDAO.getJoueur(playerId);
    	Equipe oldEquipe = equipeDAO.getEquipe(joueur.getEquipe_id());
    	HistoryDAO history = new HistoryDAO();
    	if(equipe.getSolde() >= joueur.getPrix()) {
    		oldEquipe.setSolde(oldEquipe.getSolde()+joueur.getPrix());
    		equipe.setSolde(equipe.getSolde()- joueur.getPrix());
    		joueur.setEquipe_id(newTeamId);
        	joueur.setaVendre(false);
        	history.ajouter(new History(oldEquipe.getId(),equipe.getId(),joueur.getId()));
        	joueurDAO.updateJoueur(joueur);
        	equipeDAO.updateEquipe(oldEquipe);
        	equipeDAO.updateEquipe(equipe);
        	return true;
    	}else {
    		return false;
    	}
        	
    }
    
    /**
     * liste de tous les joueurs a vendre
     * @return
     */
    public List<Joueur> getPlayerToSell(){
    	JoueurDAO joueurDAO = new JoueurDAO();
    	return joueurDAO.getListeJoueursAVendre();
    }
}