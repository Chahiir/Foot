package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;


public class MainFrame extends JFrame {

    private ArrayList<JPanel> panels;
    private JoueurService joueurService;
    private EquipeService equipeService;
    private JPanel currentVisiblePanel;

    public MainFrame() {
        this.joueurService = new JoueurService();
        this.equipeService = new EquipeService();
        // Création de la fenêtre principale
        setTitle("Gestion de l'Équipe");
        setSize(1500, 1000); // Ajusté pour mieux s'adapter à tous les éléments
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Ajoute un espace entre les composants

        // Panneau West pour la navigation
        add(new MenuPanel(this, 0), BorderLayout.WEST);

        //initialisation des panneaux
        this.panels = new ArrayList<>();
        panels.add(new TeamManagementPanel(joueurService, equipeService));
        panels.add(new JPanel());
        panels.add(new JPanel());
        panels.add(new TransferPanel(joueurService, equipeService));
     
        currentVisiblePanel = panels.get(0);
        add(currentVisiblePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setSelectedPanel(int index){
        remove(currentVisiblePanel); // Supprime l'ancien panneau
        currentVisiblePanel = panels.get(index);
        add(currentVisiblePanel, BorderLayout.CENTER);
        joueurService.notifyDataChanged(); // Mise à jour des UI sur les informations sur les joueurs
        revalidate(); // Met à jour l'affichage
        repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
