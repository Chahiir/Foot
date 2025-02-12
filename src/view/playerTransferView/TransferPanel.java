package view.playerTransferView;

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

/**
 * Classe représentant le panneau de gestion des transferts.
 * Ce panneau affiche les informations relatives aux transferts des joueurs,
 * y compris l'historique des transferts et les joueurs disponibles à l'achat ou à la vente.
 * Il met également à jour l'affichage du solde de l'équipe lorsque les données des joueurs changent.
 */
public class TransferPanel extends JPanel implements PlayerDataListener {

    private EquipeService equipeService;
    private HistoryService historyService;
    private JLabel balanceLabel;  // Label affichant le solde de l'équipe.

    /**
     * Constructeur de TransferPanel.
     * Initialise les services et configure l'interface utilisateur.
     * 
     * @param joueurService Service de gestion des joueurs.
     * @param equipeService Service de gestion des équipes.
     */
    public TransferPanel(JoueurService joueurService, EquipeService equipeService) {
        super();
        this.equipeService = equipeService;  // Injection du service de gestion des équipes.
        this.historyService = new HistoryService(); // Initialisation du service d'historique des transferts.
        joueurService.addDataListener(this); // Inscription en tant qu'écouteur des mises à jour des joueurs.

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        // Création et ajout du label affichant le solde de l'équipe.
        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Police en gras et taille augmentée.
        balanceLabel.setForeground(Color.BLACK);
        updateBalance(); // Initialisation de la valeur du solde.

        add(balanceLabel, BorderLayout.NORTH); // Ajout du label en haut du panneau.

        // Ajout du panneau d'historique des transferts en bas.
        add(new TransferHistoryPanel(joueurService, equipeService, historyService), BorderLayout.SOUTH);

        // Configuration du panneau central contenant les panneaux d'achat et de vente de joueurs.
        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(new MarketPlayerPanel(joueurService, equipeService, true), BorderLayout.WEST); // Panneau des joueurs à vendre.
        centralPanel.add(new MarketPlayerPanel(joueurService, equipeService, false), BorderLayout.EAST); // Panneau des joueurs à acheter.

        // Création d'un panneau vide au centre pour l'espacement.
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.white);
        centralPanel.add(emptyPanel, BorderLayout.CENTER);

        centralPanel.setBackground(Color.WHITE);
        add(centralPanel, BorderLayout.CENTER);
    }

    /**
     * Met à jour l'affichage du solde de l'équipe en récupérant la donnée depuis le service d'équipe.
     */
    private void updateBalance() {
        int teamBalance = equipeService.getEquipeById(equipeService.getMonEquipe()).getSolde(); // Récupération du solde de l'équipe.
        balanceLabel.setText("Solde de l'équipe : " + teamBalance + " M€"); // Mise à jour du texte du label.
    }

    /**
     * Méthode appelée lorsque les données des joueurs changent.
     * Met à jour l'affichage du solde de l'équipe pour refléter les nouvelles transactions.
     */
    @Override
    public void onDataChanged() {
        updateBalance(); // Mise à jour du solde lorsque les données changent.
    }
}
