/**
 * Service pour la gestion des résultats des matchs.
 */
package service.interfaces;

import java.util.List;

import controller.Resultat;
import dao.ResultatDAO;

/**
 * Classe permettant d'effectuer des opérations sur les résultats des matchs.
 */
public class ResultatService {

    /**
     * Ajoute un nouveau résultat dans la base de données.
     * @param resultat Le résultat à ajouter.
     */
    public void addResultat(Resultat resultat) {
        ResultatDAO resultatDAO = new ResultatDAO();
        resultatDAO.ajouter(resultat);
    }

    /**
     * Récupère la liste de tous les résultats enregistrés.
     * @return Liste des résultats disponibles.
     */
    public List<Resultat> getAllResultat() {
        ResultatDAO resultatDAO = new ResultatDAO();
        return resultatDAO.getAllResultat();
    }

    /**
     * Récupère un résultat à partir de son identifiant.
     * @param id Identifiant du résultat.
     * @return Le résultat correspondant ou null si non trouvé.
     */
    public Resultat getResultat(int id) {
        ResultatDAO resultatDAO = new ResultatDAO();
        return resultatDAO.getResultat(id);
    }

    /**
     * Supprime un résultat à partir de son identifiant.
     * @param id Identifiant du résultat à supprimer.
     */
    public void deleteResultat(int id) {
        ResultatDAO resultatDAO = new ResultatDAO();
        resultatDAO.deleteResultat(id);
    }
}
