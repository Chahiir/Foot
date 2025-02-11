package service.interfaces;

import dao.EquipeDAO;
import dao.JoueurDAO;

import java.util.ArrayList;
import java.util.List;

import controller.Equipe;
import controller.Joueur;

public class JoueurService {

    private List<PlayerDataListener> listeners = new ArrayList<>(); //pour emettre un event lors d'un changement de données

    public void addDataListener(PlayerDataListener listener) {
        listeners.add(listener);
    }

    public void notifyDataChanged() {
        for (PlayerDataListener listener : listeners) {
            listener.onDataChanged();
        }
    }

    /**
     * Récupère tous les joueurs de la base de données.
     * @return Une liste de tous les joueurs.
     */
    public List<Joueur> getAllPlayers() {
        JoueurDAO joueur = new JoueurDAO();
        return joueur.getListeJoueurs();
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
        notifyDataChanged();
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
    	joueurDAO.getJoueur(id).setaVendre(true);;
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
    	
    	if(equipe.getSolde() >= joueur.getPrix()) {
    		oldEquipe.setSolde(oldEquipe.getSolde()+joueur.getPrix());
    		equipe.setSolde(equipe.getSolde()- joueur.getPrix());
    		joueur.setEquipe_id(newTeamId);
        	joueur.setaVendre(false);
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