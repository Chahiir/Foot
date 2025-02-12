package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.Equipe;
import controller.History;
import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.HistoryService;
import service.interfaces.JoueurService;
import service.interfaces.PlayerDataListener;
import view.composent.BoldHeaderRenderer;
import view.composent.CustomCellRenderer;

import java.awt.*;

public class TransferHistoryPanel extends JPanel implements PlayerDataListener{
    private JoueurService joueurService;
    private EquipeService equipeService;
    private HistoryService historyService;
    private DefaultTableModel model;

    public TransferHistoryPanel(JoueurService joueurService, EquipeService equipeService, HistoryService historyService) {
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        this.historyService = historyService;
        joueurService.addDataListener(this);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        add(createHeaderLabel(), BorderLayout.NORTH);
        add(createScrollPane(), BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }

    private JLabel createHeaderLabel() {
        JLabel headerLabel = new JLabel("Historique des Transferts");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        return headerLabel;
    }

    private JScrollPane createScrollPane() {
        String[] columnNames = {"Joueur", "Club Départ", "Club Arrivée", "Type de Transfert", "Montant (en M€)", "Date"};
        model = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(model);
        BoldHeaderRenderer headerRenderer = new BoldHeaderRenderer();
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer());
        }

        loadHistoryData();

        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    @Override
    public void onDataChanged() {
        loadHistoryData();
    }

    private void loadHistoryData() {
        model.setRowCount(0);  // Clear existing data
        java.util.List<History> history = historyService.getHistory();
        for (History transfer : history) {
            if (transfer.getNewEquipe_id() == equipeService.getMonEquipe() || transfer.getOldEquipe_id() == equipeService.getMonEquipe()) {
                Joueur joueur = joueurService.getPlayerById(transfer.getJoueur_id());
                Equipe departingClub = equipeService.getEquipeById(transfer.getOldEquipe_id());
                Equipe arrivingClub = equipeService.getEquipeById(transfer.getNewEquipe_id());
                String type = transfer.getNewEquipe_id() == equipeService.getMonEquipe() ? "Achat" : "Vente";

                model.addRow(new Object[]{
                    joueur.getPrenom() + " " + joueur.getNom(), 
                    departingClub.getNom(), 
                    arrivingClub.getNom(), 
                    type, 
                    joueur.getPrix(), 
                    transfer.getDate()
                });
            }
        }
    }
}
