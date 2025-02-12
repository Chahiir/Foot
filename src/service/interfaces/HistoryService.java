package service.interfaces;

import java.util.List;

import controller.History;
import dao.HistoryDAO;

/**
 * Service pour la gestion des historiques de transferts.
 */
public class HistoryService {

    /**
     * Récupère la liste complète de l'historique des transferts.
     * @return Une liste contenant tous les transferts enregistrés.
     */
    public List<History> getHistory() {
        HistoryDAO historyDAO = new HistoryDAO();
        return historyDAO.getHistory();
    }

    /**
     * Ajoute un nouveau transfert à l'historique.
     * @param newTransfer Le transfert à ajouter.
     */
    public void addTransfer(History newTransfer) {
        HistoryDAO historyDAO = new HistoryDAO();
        historyDAO.ajouter(newTransfer);
    }
}
