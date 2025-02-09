package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainFrame extends JFrame {

    public MainFrame() {
        // Création de la fenêtre principale
        setTitle("Gestion de l'Équipe");
        setSize(1000, 500); // Ajusté pour mieux s'adapter à tous les éléments
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Ajoute un espace entre les composants

        // Panneau West pour la navigation
        add(new MenuPanel(0), BorderLayout.WEST);
     
        add(new TeamManagementPanel(), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    

    public static void main(String[] args) {
        new MainFrame();
    }
}
