package service.interfaces;


import controller.Equipe;
import dao.EquipeDAO;


import java.util.List;

public class EquipeService {
	
	private int monEquipe;
	
	public int getMonEquipe() {
		return monEquipe;
	}
	
	public void setMonEquipe(int id) {
		this.monEquipe = id;
	}
	
	public Equipe getEquipeById(int id) {
		EquipeDAO equipeDAO = new EquipeDAO();
		return equipeDAO.getEquipe(id);
	}
	
	public List<Equipe> getAllEquipes(){
		EquipeDAO equipeDAO = new EquipeDAO();
		return equipeDAO.getListeEquipes();
	}

   

    public void deleteTeamById( int teamId) {
    	EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.deleteEquipe(teamId);    
		}
    
    public void addTeam(Equipe team) {
    	EquipeDAO equipeDAO = new EquipeDAO();
    	equipeDAO.ajouter(team);	
    }
}
