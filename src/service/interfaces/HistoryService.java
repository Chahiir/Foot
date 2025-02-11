package service.interfaces;

import java.util.List;

import controller.History;
import dao.HistoryDAO;

public class HistoryService {

	public List<History> getHistory(){
		HistoryDAO historyDAO = new HistoryDAO();
		return historyDAO.getHistory();
		
	}
	
	public void addTransfer(History newTransfer){
		HistoryDAO historyDAO = new HistoryDAO();
		historyDAO.ajouter(newTransfer);
	}
	
	
	
	
	
	
}
