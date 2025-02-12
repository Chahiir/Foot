package view.teamManagmentView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import service.interfaces.PlayerDataListener;
import view.composent.*;

/**
 * La classe PlayersPanel fournit une interface utilisateur permettant d'afficher et d'interagir
 * avec les données des joueurs dans une application de gestion d'équipe sportive.
 * Elle permet d'ajouter de nouveaux joueurs et de modifier les existants.
 */
public class PlayersPanel extends JPanel implements PlayerDataListener {
    private JTable table;
    private DefaultTableModel model;
    private JoueurService joueurService;
    private EquipeService equipeService;

    /**
     * Constructeur de PlayersPanel avec dépendances sur les services de gestion des joueurs et des équipes.
     * @param joueurService le service permettant de gérer les opérations sur les joueurs
     * @param equipeService le service permettant de gérer les opérations sur les équipes
     */
    public PlayersPanel(JoueurService joueurService, EquipeService equipeService) {
        super();
        // Ajout des services de gestion des joueurs et des équipes
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        joueurService.addDataListener(this);  // Enregistrement du panneau comme écouteur de données

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0,0,0, Color.TRANSLUCENT));
        
        // Configuration du panneau supérieur avec le bouton d'ajout de joueur et un champ de recherche
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(new Color(0,0,0, Color.TRANSLUCENT));
        northPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        AddPlayerButton addPlayerButton = new AddPlayerButton("Ajouter un joueur", joueurService, equipeService);
        addPlayerButton.setMinimumSize(new Dimension(20, 30));
        addPlayerButton.setForeground(Color.WHITE);
        JTextField searchField = new JTextField(15);
        northPanel.add(addPlayerButton, BorderLayout.WEST);
        northPanel.add(searchField, BorderLayout.EAST);

        // Configuration du tableau pour afficher les données des joueurs
        String[] columnNames = {"Nom", "Prénom", "Poste", "Actions"};
        Object[][] data = {};
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Seule la colonne des actions est éditable
            }
        };
        
        table = new JTable(model);
        BoldHeaderRenderer headerRenderer = new BoldHeaderRenderer();
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(headerRenderer);
        
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer());
        }
        table.getColumn("Actions").setCellRenderer(new ButtonRenderer(joueurService, equipeService));
        table.getColumn("Actions").setCellEditor(new ButtonEditor(joueurService, equipeService));
        table.setRowHeight(40);
        table.setBackground(Color.white);

        // Chargement initial des données et mise en place de l'affichage
        loadData();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        table.setShowGrid(false); // Désactive les bordures des cellules
        table.setIntercellSpacing(new Dimension(0, 0)); // Supprime l'espacement entre les cellules
        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Méthode appelée lorsque les données du JoueurService sont mises à jour.
     * Elle déclenche la mise à jour de l'interface utilisateur.
     */
    @Override
    public void onDataChanged() {
        reloadData();  // Recharge les données lorsqu'une mise à jour est signalée
    }

    /**
     * Recharge les données des joueurs depuis le service et met à jour le modèle du tableau.
     */
    private void reloadData() {
        model.setRowCount(0); // Efface les données existantes
        joueurService.getAllPlayers().forEach((joueur) -> {
            if (joueur.getEquipe_id() == equipeService.getMonEquipe())
                addRowData(joueur);
        });
    }
    
    /**
     * Charge les données initiales des joueurs à partir du service dans le modèle du tableau.
     */
    private void loadData() {
        joueurService.getAllPlayers().forEach((joueur) -> {
            if (joueur.getEquipe_id() == equipeService.getMonEquipe())
                addRowData(joueur);
        });
    }

    /**
     * Ajoute une ligne contenant les données d'un joueur au modèle du tableau.
     * @param joueur le joueur dont les données doivent être ajoutées
     */
    private void addRowData(Joueur joueur) {
        model.addRow(new Object[]{joueur.getNom(), joueur.getPrenom(), joueur.getPosition(), joueur.getId()});
    }
}
