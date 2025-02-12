package service.interfaces;

import controller.Equipe;
import dao.EquipeDAO;
import java.util.List;

/**
 * Service pour la gestion des équipes.
 */
public class EquipeService {
    
    /**
     * Identifiant de l'équipe de l'utilisateur.
     * Initialisé à -1 par défaut.
     */
    private int monEquipe = -1;
    
    /**
     * Récupère l'identifiant de l'équipe de l'utilisateur.
     * @return L'identifiant de l'équipe.
     */
    public int getMonEquipe() {
        return monEquipe;
    }
    
    /**
     * Définit l'identifiant de l'équipe de l'utilisateur.
     * @param id Le nouvel identifiant de l'équipe.
     */
    public void setMonEquipe(int id) {
        this.monEquipe = id;
    }
    
    /**
     * Récupère une équipe par son identifiant.
     * @param id L'identifiant de l'équipe.
     * @return L'équipe correspondante.
     */
    public Equipe getEquipeById(int id) {
        EquipeDAO equipeDAO = new EquipeDAO();
        return equipeDAO.getEquipe(id);
    }
    
    /**
     * Récupère la liste de toutes les équipes.
     * @return La liste des équipes.
     */
    public List<Equipe> getAllEquipes() {
        EquipeDAO equipeDAO = new EquipeDAO();
        return equipeDAO.getListeEquipes();
    }

    /**
     * Supprime une équipe par son identifiant.
     * @param teamId L'identifiant de l'équipe à supprimer.
     */
    public void deleteTeamById(int teamId) {
        EquipeDAO equipeDAO = new EquipeDAO();
        equipeDAO.deleteEquipe(teamId);    
    }
    
    /**
     * Ajoute une nouvelle équipe à la base de données.
     * @param team L'équipe à ajouter.
     */
    public void addTeam(Equipe team) {
        EquipeDAO equipeDAO = new EquipeDAO();
        equipeDAO.ajouter(team);    
    }
}
