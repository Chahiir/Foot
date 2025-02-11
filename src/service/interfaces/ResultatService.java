package service.interfaces;

import java.util.List;

import controller.Resultat;
import dao.ResultatDAO;

public class ResultatService {

	
	
	public void addResultat(Resultat resultat) {
		ResultatDAO resultatDAO = new ResultatDAO();
		resultatDAO.ajouter(resultat);
	}
	
	public List<Resultat> getAllResultat(){
		ResultatDAO resultatDAO = new ResultatDAO();
		return resultatDAO.getAllResultat();
	}
	
	public Resultat getResultat(int id) {
		ResultatDAO resultatDAO = new ResultatDAO();
		return resultatDAO.getResultat(id);
	}
	
	public void deleteResultat(int id) {
		ResultatDAO resultatDAO = new ResultatDAO();
		resultatDAO.deleteResultat(id);
	}
	
}
