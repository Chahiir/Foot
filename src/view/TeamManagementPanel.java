package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.composent.CustomTabbedPanel;

public class TeamManagementPanel extends JPanel {

    private JoueurService joueurService;
    private EquipeService equipeService;

	public TeamManagementPanel(JoueurService joueurService, EquipeService equipeService) {
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 20));
        setBackground(Color.WHITE);
        initializeUI();
    }
	
	private void initializeUI() {
		
		JLabel title = new JLabel("Gestion de l'Équipe");
		title.setFont(new Font("Serif", Font.BOLD, 24));
        // Configuration du JTabbedPane
		CustomTabbedPanel tabbedPane = new CustomTabbedPanel();
        tabbedPane.addTab("Joueurs", new PlayersPanel(joueurService, equipeService));
        tabbedPane.addTab("Staff", new JPanel());
        tabbedPane.addTab("Composition d'équipe", new JPanel());

        // Ajout de la barre de recherche en haut du panneau
        add(title, BorderLayout.NORTH);
        // Ajout du JTabbedPane au centre
        add(tabbedPane, BorderLayout.CENTER);
    }
}
