package service.interfaces;

import dao.JoueurDAO;
import java.util.List;
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

    	
}