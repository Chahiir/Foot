package service.interfaces;

import dao.EquipeDAO;
import dao.HistoryDAO;
import dao.JoueurDAO;

import java.util.ArrayList;
import java.util.List;

import controller.Equipe;
import controller.History;
import controller.Joueur;

/**
 * Service pour la gestion des joueurs.
 */
public class JoueurService {

    /** Liste des écouteurs de données pour notifier les changements. */
    private List<PlayerDataListener> listeners = new ArrayList<>();

    /**
     * Ajoute un écouteur de données pour être notifié des changements.
     * @param listener L'écouteur à ajouter.
     */
    public void addDataListener(PlayerDataListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifie tous les écouteurs qu'un changement de données a eu lieu.
     */
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
     * Récupère la liste des joueurs d'une équipe spécifique.
     * @param id Identifiant de l'équipe.
     * @return Liste des joueurs appartenant à cette équipe.
     */
    public List<Joueur> getTeamPlayer(int id){
        JoueurDAO joueurDAO = new JoueurDAO();
        return joueurDAO.getTeamJoueurs(id);
    }

    /**
     * Ajoute un joueur à une équipe spécifique.
     * @param playerId Identifiant du joueur à ajouter.
     * @param equipeID Identifiant de l'équipe cible.
     */
    public void addPlayerToTeam(int playerId, int equipeID) {
        JoueurDAO joueurDAO = new JoueurDAO();
        Joueur joueur = joueurDAO.getJoueur(playerId);
        joueur.setEquipe_id(equipeID);
        joueurDAO.updateJoueur(joueur);
    }

    /**
     * Retire un joueur de son équipe actuelle et le met sans équipe.
     * @param playerId Identifiant du joueur à retirer.
     */
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
        notifyDataChanged();
    }

    /**
     * Ajoute un joueur dans la base de données.
     * @param newPlayer Le joueur à ajouter.
     */
    public void addPlayer(Joueur newPlayer) {
        JoueurDAO joueurDAO = new JoueurDAO();
        joueurDAO.ajouter(newPlayer);
        notifyDataChanged();
    }

    /**
     * Marque un joueur comme étant à vendre.
     * @param id Identifiant du joueur à mettre en vente.
     */
    public void markPlayerToSell(int id) {
        JoueurDAO joueurDAO = new JoueurDAO();
        Joueur joueur = joueurDAO.getJoueur(id);
        joueur.setaVendre(true);
        joueurDAO.updateJoueur(joueur);
        notifyDataChanged();
    }

    /**
     * Transfère un joueur vers une nouvelle équipe en vérifiant si l'équipe acheteuse a le solde nécessaire.
     * @param playerId Identifiant du joueur à transférer.
     * @param newTeamId Identifiant de l'équipe de destination.
     * @return true si le transfert est réussi, false sinon.
     */
    public boolean transferPlayer(int playerId, int newTeamId) {
        EquipeDAO equipeDAO = new EquipeDAO();
        Equipe equipe = equipeDAO.getEquipe(newTeamId);
        JoueurDAO joueurDAO = new JoueurDAO();
        Joueur joueur = joueurDAO.getJoueur(playerId);
        Equipe oldEquipe = equipeDAO.getEquipe(joueur.getEquipe_id());
        HistoryDAO history = new HistoryDAO();

        if (equipe.getSolde() >= joueur.getPrix()) {
            oldEquipe.setSolde(oldEquipe.getSolde() + joueur.getPrix());
            equipe.setSolde(equipe.getSolde() - joueur.getPrix());
            joueur.setEquipe_id(newTeamId);
            joueur.setaVendre(false);
            history.ajouter(new History(oldEquipe.getId(), equipe.getId(), joueur.getId(), joueur.getPrix()));
            joueurDAO.updateJoueur(joueur);
            equipeDAO.updateEquipe(oldEquipe);
            equipeDAO.updateEquipe(equipe);
            notifyDataChanged();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Récupère la liste des joueurs à vendre.
     * @return Liste des joueurs disponibles sur le marché des transferts.
     */
    public List<Joueur> getPlayerToSell() {
        JoueurDAO joueurDAO = new JoueurDAO();
        return joueurDAO.getListeJoueursAVendre();
    }
}
