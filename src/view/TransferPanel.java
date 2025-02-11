package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Equipe;
import controller.History;
import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.HistoryService;
import service.interfaces.JoueurService;
import view.composent.ActionButton;
import view.composent.TransferBuyButton;

public class TransferPanel extends JPanel{

    private JTable table;
    private DefaultTableModel model;
    private JoueurService joueurService;
    private EquipeService equipeService;
    private HistoryService historyService;
    private int myTeamId;

    public TransferPanel(JoueurService joueurService, EquipeService equipeService) {
        super();
        this.joueurService = joueurService;  // Utiliser le service injecté
        this.equipeService = equipeService;  // Utiliser le service injecté
        this.historyService = new HistoryService();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        add(createTransferHistoryPanel(), BorderLayout.SOUTH);
        // Configuration des sous-panels
        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(createPlayersForSalePanel(), BorderLayout.WEST);
        centralPanel.add(new JPanel(), BorderLayout.CENTER);
        centralPanel.add(createAvailablePlayersPanel(), BorderLayout.EAST);

        add(centralPanel, BorderLayout.CENTER);
    }

    private JPanel createPlayersForSalePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Joueurs à Vendre"), BorderLayout.NORTH);
        JPanel centralPanel = new JPanel();
        java.util.List<Joueur> playersToSell = joueurService.getPlayerToSell();
        if (playersToSell.isEmpty()) {
            centralPanel.add(new JLabel("Aucun joueur n'est actuellement à vendre."), BorderLayout.CENTER);
        } else {
            for (Joueur joueur : playersToSell) {
                if (joueur.getEquipe_id() == myTeamId)
                    centralPanel.add(createPlayerPanel(joueur, new TransferBuyButton(joueur.getId(), joueurService)));
            }
        }
        panel.add(centralPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAvailablePlayersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Joueurs à Acheter"), BorderLayout.NORTH);
        java.util.List<Joueur> playersToBuy = joueurService.getPlayerToSell();
        JPanel centralPanel = new JPanel();
        if (playersToBuy.isEmpty()) {
            centralPanel.add(new JLabel("Aucun joueur n'est actuellement à vendre."), BorderLayout.CENTER);
        } else {
            for (Joueur joueur : playersToBuy) {
                if (joueur.getEquipe_id() != myTeamId)
                centralPanel.add(createPlayerPanel(joueur, new TransferBuyButton(joueur.getId(), joueurService)));
            }
        }
        panel.add(centralPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPlayerPanel(Joueur joueur,ActionButton actionButton) {
        PlayerPanel panel = new PlayerPanel(joueur, actionButton, joueurService, equipeService);
        return panel;
    }

    private JPanel createTransferHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Historique des Transferts"), BorderLayout.NORTH);

        String[] columnNames = {"Joueur", "Club Départ", "Club Arrivée", "Type de Transfert", "Montant", "Date"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        
        java.util.List<History> history = historyService.getHistory();
        for (History transfer : history) {
            if(transfer.getNewEquipe_id() == myTeamId || transfer.getOldEquipe_id()==myTeamId){
                Joueur joueur = joueurService.getPlayerById(transfer.getJoueur_id());
                Equipe departingClub = equipeService.getEquipeById(transfer.getOldEquipe_id());
                Equipe arrivingClub = equipeService.getEquipeById(transfer.getNewEquipe_id());
                String type = transfer.getNewEquipe_id() == myTeamId ? "Achat":"Vente";

                model.addRow(new Object[]{joueur.getPrenom()+" "+joueur.getNom(), departingClub.getNom(), arrivingClub.getNom(), 
                                        type, joueur.getPrix(), transfer.getDate()});
            }
        }
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}