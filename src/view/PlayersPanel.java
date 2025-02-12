package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import service.interfaces.PlayerDataListener;
import view.composent.AddPlayerButton;
import view.composent.BoldHeaderRenderer;
import view.composent.ButtonEditor;
import view.composent.ButtonRenderer;
import view.composent.CustomCellRenderer;

public class PlayersPanel extends JPanel implements PlayerDataListener{
	private JTable table;
    private DefaultTableModel model;
    private JoueurService joueurService;
    private EquipeService equipeService;
    
	public PlayersPanel(JoueurService joueurService, EquipeService equipeService) {
		super();

        //ajout de la couche service de gestion des joueurs et équipe
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        joueurService.addDataListener(this);  // Enregistrez le panel comme listener

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(0,0,0,Color.TRANSLUCENT));
		
		// North panel with add button and search field using BorderLayout
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(new Color(0,0,0,Color.TRANSLUCENT));
        northPanel.setBorder(new EmptyBorder(10,10,10,10));
        AddPlayerButton addPlayerButton = new AddPlayerButton("Ajouter un joueur", joueurService);
        addPlayerButton.setMinimumSize(new Dimension(20,30));
        addPlayerButton.setForeground(Color.WHITE);
        JTextField searchField = new JTextField(15);
        northPanel.add(addPlayerButton, BorderLayout.WEST);
        northPanel.add(searchField, BorderLayout.EAST);

        // Setup table for players
        String[] columnNames = {"Nom", "Prénom", "Poste", "Actions"};
        Object[][] data = {}; // initial empty data
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only actions column is editable
            }
        };
        
        table = new JTable(model);
        BoldHeaderRenderer headerRenderer = new BoldHeaderRenderer();

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(headerRenderer);
        
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer());
        }
        table.getColumn("Actions").setCellRenderer(new ButtonRenderer(joueurService));
        table.getColumn("Actions").setCellEditor(new ButtonEditor(joueurService));
        table.setRowHeight(40);
        table.setBackground(Color.white);

        // Add rows with data 
        loadData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        table.setShowGrid(false); // Disable cell borders
        table.setIntercellSpacing(new Dimension(0, 0)); // Remove spacing between cells
        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
	}

    @Override
    public void onDataChanged() {
        reloadData();  // Recharge les données lorsque le service notifie un changement
    }

    private void reloadData() {
        model.setRowCount(0); // Efface les données existantes
        joueurService.getAllPlayers().forEach((joueur) -> {
            if (joueur.getEquipe_id() == equipeService.getMonEquipe())
                addRowData(joueur);
        });
	}
    

	private void loadData() {
        joueurService.getAllPlayers().forEach((joueur) -> {
            if (joueur.getEquipe_id() == equipeService.getMonEquipe())
                addRowData(joueur);
        });
	}
    
	private void addRowData(Joueur joueur) {
        model.addRow(new Object[]{joueur.getNom(), joueur.getPrenom(), joueur.getPosition(), joueur.getId()});
    }
}
