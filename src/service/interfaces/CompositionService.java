package service.interfaces;

import java.util.List;

import controller.Composition;
import dao.CompositionDAO;

/**
 * Service pour la gestion des compositions des matchs.
 */
public class CompositionService {

    /**
     * Ajoute une nouvelle composition de match.
     * @param newComp La nouvelle composition à ajouter.
     */
    public void addComposition(Composition newComp) {
        CompositionDAO comp = new CompositionDAO();
        comp.ajouter(newComp);
    }

    /**
     * Récupère la composition d'un match spécifique.
     * @param id L'identifiant du match.
     * @return La liste des compositions associées à ce match.
     */
    public List<Composition> getCompositionOfMatch(int id) {
        CompositionDAO comp = new CompositionDAO();
        return comp.getCompositionMatch(id);
    }
}
