package view.teamManagmentView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.composent.CustomTabbedPanel;

/**
 * TeamManagementPanel est un JPanel qui gère l'interface de gestion des équipes.
 * Ce panneau contient des onglets pour gérer différents aspects de l'équipe, tels que les joueurs, le staff, et la composition de l'équipe.
 */
public class TeamManagementPanel extends JPanel {

    private JoueurService joueurService; // Service pour gérer les données des joueurs
    private EquipeService equipeService; // Service pour gérer les informations de l'équipe

    /**
     * Constructeur pour initialiser le panneau de gestion d'équipe.
     * @param joueurService Service utilisé pour interagir avec les données des joueurs.
     * @param equipeService Service utilisé pour interagir avec les données des équipes.
     */
    public TeamManagementPanel(JoueurService joueurService, EquipeService equipeService) {
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 20));
        setBackground(Color.WHITE);
        initializeUI();
    }
    
    /**
     * Initialisation de l'interface utilisateur, ajout des composants au panneau.
     */
    private void initializeUI() {
        JLabel title = new JLabel("Gestion de l'Équipe");
        title.setFont(new Font("Serif", Font.BOLD, 24));

        // Création et configuration d'un panneau à onglets pour les différentes sections de la gestion d'équipe.
        CustomTabbedPanel tabbedPane = new CustomTabbedPanel();
        tabbedPane.addTab("Joueurs", new PlayersPanel(joueurService, equipeService));
        tabbedPane.addTab("Staff", new JPanel()); // Placeholder pour le futur contenu
        tabbedPane.addTab("Composition d'équipe", new JPanel()); // Placeholder pour le futur contenu

        // Ajout du titre et du panneau à onglets au panneau principal
        add(title, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
}
