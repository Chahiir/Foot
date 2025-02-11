package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.composent.ActionButton;
import view.composent.TransferBuyButton;

public class TransferPanel extends JPanel{

    private JTable table;
    private DefaultTableModel model;
    private JoueurService joueurService;
    private EquipeService equipeService;
    private int myTeamId;

    public TransferPanel(JoueurService joueurService, EquipeService equipeService) {
        super();
        this.joueurService = joueurService;  // Utiliser le service injecté
        this.equipeService = equipeService;  // Utiliser le service injecté
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        
        // Configuration des sous-panels
        add(createPlayersForSalePanel(), BorderLayout.WEST);
        add(createAvailablePlayersPanel(), BorderLayout.EAST);
        add(createTransferHistoryPanel(), BorderLayout.SOUTH);
    }

    private JPanel createPlayersForSalePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Joueurs à Vendre"), BorderLayout.NORTH);
        java.util.List<Joueur> playersToSell = joueurService.getPlayerToSell();
        if (playersToSell.isEmpty()) {
            panel.add(new JLabel("Aucun joueur n'est actuellement à vendre."), BorderLayout.CENTER);
        } else {
            for (Joueur joueur : playersToSell) {
                if (joueur.getEquipe_id() == myTeamId)
                    panel.add(createPlayerPanel(joueur, new TransferBuyButton(joueur.getId(), joueurService)));
            }
        }
        return panel;
    }

    private JPanel createAvailablePlayersPanel() {
        JPanel panel = new JPanel();
        java.util.List<Joueur> playersToBuy = joueurService.getPlayerToSell();
        for (Joueur joueur : playersToBuy) {
            if (joueur.getEquipe_id() != myTeamId)
                panel.add(createPlayerPanel(joueur, new TransferBuyButton(joueur.getId(), joueurService)));
        }
        return panel;
    }

    private JPanel createPlayerPanel(Joueur joueur,ActionButton actionButton) {
        PlayerPanel panel = new PlayerPanel(joueur, actionButton, joueurService, equipeService);
        return panel;
    }

    private JPanel createTransferHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Historique des Transferts"), BorderLayout.NORTH);

        String[] columnNames = {"Joueur", "Club Départ", "Club Arrivée", "Type de Transfert", "Montant", "Statut", "Date", "Actions"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        /* 
        java.util.List<Transfer> history = joueurService.getTransferHistory();
        for (Transfer transfer : history) {
            model.addRow(new Object[]{transfer.getPlayerName(), transfer.getDepartingClub(), transfer.getArrivingClub(), 
                                      transfer.getType(), transfer.getAmount(), transfer.getStatus(), transfer.getDate(), "Détails"});
        }
        */
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}