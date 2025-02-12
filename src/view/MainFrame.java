package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.playerTransferView.TransferPanel;
import view.teamManagmentView.TeamManagementPanel;

/**
 * MainFrame est la classe principale qui initialise et affiche la fenêtre principale de l'application.
 * Elle gère les différents panneaux (panels) de l'application et permet de changer le contenu affiché
 * en fonction de l'interaction de l'utilisateur avec le menu de navigation.
 */
public class MainFrame extends JFrame {

    private ArrayList<JPanel> panels;
    private JoueurService joueurService;
    private EquipeService equipeService;
    private JPanel currentVisiblePanel;

    /**
     * Constructeur de MainFrame qui initialise les services et les composants UI.
     */
    public MainFrame() {
        // Initialisation des services pour la gestion des joueurs et des équipes.
        this.joueurService = new JoueurService();
        this.equipeService = new EquipeService();

        // Affichage de la boîte de dialogue de sélection d'équipe.
        TeamSelectionDialog teamDialog = new TeamSelectionDialog(this, equipeService);
        teamDialog.setVisible(true); // Affiche le dialogue et bloque jusqu'à la sélection

        // Vérification si une équipe a été sélectionnée avant de continuer.
        if (equipeService.getMonEquipe() != -1){
            // Configuration de la fenêtre principale.
            setTitle("Gestion de l'Équipe");
            setSize(1500, 1000);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Ajout du panneau de menu à l'ouest pour la navigation.
            add(new MenuPanel(this, 0), BorderLayout.WEST);

            // Initialisation et ajout des différents panneaux de gestion.
            this.panels = new ArrayList<>();
            panels.add(new TeamManagementPanel(joueurService, equipeService));
            panels.add(new JPanel()); // Placeholders pour d'autres fonctionnalités.
            panels.add(new JPanel());
            panels.add(new TransferPanel(joueurService, equipeService));
        
            currentVisiblePanel = panels.get(0);
            add(currentVisiblePanel, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    /**
     * Méthode pour changer le panneau affiché en fonction de l'index spécifié.
     * @param index L'index du panneau à afficher dans le cadre principal.
     */
    public void setSelectedPanel(int index){
        // Remplacement du panneau actuellement visible.
        remove(currentVisiblePanel);
        currentVisiblePanel = panels.get(index);
        add(currentVisiblePanel, BorderLayout.CENTER);
        joueurService.notifyDataChanged(); // Notification de mise à jour des données.
        revalidate();
        repaint();
    }

    /**
     * Point d'entrée principal pour l'application.
     * @param args Arguments de ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        new MainFrame();
    }
}
