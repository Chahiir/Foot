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

import view.composent.AddPlayerButton;
import view.composent.BoldHeaderRenderer;
import view.composent.ButtonEditor;
import view.composent.ButtonRenderer;
import view.composent.CustomCellRenderer;

public class PlayersPanel extends JPanel {
	private JTable table;
    private DefaultTableModel model;
    
	public PlayersPanel() {
		super();
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(0,0,0,Color.TRANSLUCENT));
		
		// North panel with add button and search field using BorderLayout
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(new Color(0,0,0,Color.TRANSLUCENT));
        northPanel.setBorder(new EmptyBorder(10,10,10,10));
        AddPlayerButton addPlayerButton = new AddPlayerButton("Ajouter un joueur");
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
        table.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        table.getColumn("Actions").setCellEditor(new ButtonEditor());
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

	private void loadData() {
		addRowData("Dupont", "Antoine", "Milieu");
        addRowData("Martin", "Lucas", "Attaquant");
        addRowData("Leroy", "Pierre", "Défenseur");
        addRowData("Bernard", "Mathieu", "Gardien");
        addRowData("Dubois", "David", "Milieu");
	}
    
	private void addRowData(String nom, String prenom, String poste) {
        model.addRow(new Object[]{nom, prenom, poste, ""});
    }
    
}
