package service.interfaces;

import java.util.List;

import controller.Composition;
import dao.CompositionDAO;

public class CompositionService {

	
	
	public void addComposition(Composition newComp) {
		CompositionDAO comp = new CompositionDAO();
		comp.ajouter(newComp);
	}
	
	public List<Composition> getCompositionOfMatch(int id) {
		CompositionDAO comp = new CompositionDAO();
		return comp.getCompositionMatch(id);
	}
	
	
}
