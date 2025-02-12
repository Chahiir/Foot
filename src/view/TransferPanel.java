package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.interfaces.EquipeService;
import service.interfaces.HistoryService;
import service.interfaces.JoueurService;
import service.interfaces.PlayerDataListener;

public class TransferPanel extends JPanel implements PlayerDataListener{

    private EquipeService equipeService;
    private HistoryService historyService;
    private JLabel balanceLabel;  // Label pour afficher le solde

    public TransferPanel(JoueurService joueurService, EquipeService equipeService) {
        super();
        this.equipeService = equipeService;  // Utiliser le service injecté
        this.historyService = new HistoryService();
        joueurService.addDataListener(this); // Inscription aux mises à jour du joueurService

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Création et ajout du JLabel pour afficher le solde
        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Style en gras et plus grand
        balanceLabel.setForeground(Color.BLACK);
        updateBalance(); // Initialise la valeur du solde

        add(balanceLabel, BorderLayout.NORTH); // Ajout du label en haut du panel

        add(new TransferHistoryPanel(joueurService, equipeService, historyService), BorderLayout.SOUTH);
        // Configuration des sous-panels
        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(new MarketPlayerPanel(joueurService, equipeService, true), BorderLayout.WEST);
        centralPanel.add(new MarketPlayerPanel(joueurService, equipeService, false), BorderLayout.EAST);    

        //créer un panneau vide au milieu
        
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.white);
        centralPanel.add(emptyPanel, BorderLayout.CENTER);

        centralPanel.setBackground(Color.WHITE);
        add(centralPanel, BorderLayout.CENTER);
    }

    /**
     * Méthode pour mettre à jour l'affichage du solde de l'équipe.
     */
    private void updateBalance() {
        int teamBalance = equipeService.getEquipeById(equipeService.getMonEquipe()).getSolde(); // Récupérer le solde depuis le service
        balanceLabel.setText("Solde de l'équipe : " + teamBalance + " M€");
    }

    /**
     * Méthode appelée lorsque les données de joueurService changent.
     */
    @Override
    public void onDataChanged() {
        updateBalance(); // Mettre à jour le solde de l'équipe
    }
}