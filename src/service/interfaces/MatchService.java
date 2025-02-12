/**
 * Service pour la gestion des matchs.
 */
package service.interfaces;

import java.sql.Date;
import java.util.List;

import controller.Match;
import dao.MatchDAO;

/**
 * Classe permettant d'effectuer des opérations sur les matchs.
 */
public class MatchService {

    /**
     * Récupère un match à partir de son identifiant.
     * @param id Identifiant du match.
     * @return Le match correspondant ou null si non trouvé.
     */
    public Match getMatch(int id) {
        MatchDAO matchDAO = new MatchDAO();
        return matchDAO.getMatch(id);
    }

    /**
     * Récupère un match à partir des identifiants des équipes participantes.
     * @param equipe Identifiant de l'équipe principale.
     * @param adversaire Identifiant de l'équipe adverse.
     * @return Le match correspondant ou null si non trouvé.
     */
    public Match getMatchByEquipes(int equipe, int adversaire) {
        MatchDAO matchDAO = new MatchDAO();
        return matchDAO.getMatch(equipe, adversaire);
    }

    /**
     * Récupère un match à partir de sa date.
     * @param date Date du match.
     * @return Le match correspondant ou null si non trouvé.
     */
    public Match getMatchByDate(Date date) {
        MatchDAO matchDAO = new MatchDAO();
        return matchDAO.getMatchByDate(date);
    }

    /**
     * Récupère la liste de tous les matchs.
     * @return Liste des matchs disponibles.
     */
    public List<Match> getAllMatch() {
        MatchDAO matchDAO = new MatchDAO();
        return matchDAO.getAllMatch();
    }

    /**
     * Ajoute un nouveau match à la base de données.
     * @param match Le match à ajouter.
     */
    public void addMatch(Match match) {
        MatchDAO matchDAO = new MatchDAO();
        matchDAO.ajouter(match);
    }

    /**
     * Supprime un match à partir de son identifiant.
     * @param id Identifiant du match à supprimer.
     */
    public void deleteMatch(int id) {
        MatchDAO matchDAO = new MatchDAO();
        matchDAO.deleteMatch(id);
    }
}
