package view.playerTransferView;

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

/**
 * TransferHistoryPanel est un panneau qui affiche l'historique des transferts des joueurs au sein d'une équipe.
 * Il se met à jour dynamiquement en réponse aux changements de données des joueurs.
 */
public class TransferHistoryPanel extends JPanel implements PlayerDataListener{
    private JoueurService joueurService;
    private EquipeService equipeService;
    private HistoryService historyService;
    private DefaultTableModel model;

    /**
     * Construit un panneau pour afficher l'historique des transferts avec les services nécessaires pour obtenir les données requises.
     * @param joueurService Service pour accéder aux données des joueurs.
     * @param equipeService Service pour accéder aux données des équipes.
     * @param historyService Service pour obtenir les données historiques des transferts.
     */
    public TransferHistoryPanel(JoueurService joueurService, EquipeService equipeService, HistoryService historyService) {
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        this.historyService = historyService;
        joueurService.addDataListener(this);
        initUI();
    }

    /**
     * Initialise les composants de l'interface utilisateur du panneau.
     */
    private void initUI() {
        setLayout(new BorderLayout());
        add(createHeaderLabel(), BorderLayout.NORTH);
        add(createScrollPane(), BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }

    /**
     * Crée une étiquette d'en-tête pour le panneau.
     * @return JLabel configurée pour l'en-tête.
     */
    private JLabel createHeaderLabel() {
        JLabel headerLabel = new JLabel("Historique des Transferts");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        return headerLabel;
    }

    /**
     * Crée un JScrollPane contenant un JTable pour afficher les données de transfert.
     * @return JScrollPane contenant le JTable configuré.
     */
    private JScrollPane createScrollPane() {
        String[] columnNames = {"Joueur", "Club Départ", "Club Arrivée", "Type de Transfert", "Montant (en M€)", "Date"};
        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Le tableau n'est pas editable
            }
        };
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

    /**
     * Méthode appelée lorsque les données de JoueurService changent.
     * Ceci déclenche un rechargement des données de l'historique.
     */
    @Override
    public void onDataChanged() {
        loadHistoryData();
    }

    /**
     * Charge les données historiques dans le modèle de tableau à partir du HistoryService.
     */
    private void loadHistoryData() {
        model.setRowCount(0);  // Efface les données existantes
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
