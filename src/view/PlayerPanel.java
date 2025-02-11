package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.composent.ActionButton;

public class PlayerPanel extends JPanel{
    private ActionButton actionButton;
    private JoueurService joueurService;
    private EquipeService equipeService;
    private Joueur joueur;

    public PlayerPanel(Joueur joueur, ActionButton actionButton, JoueurService joueurService, EquipeService equipeService) {
        this.actionButton = actionButton;
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        this.joueur = joueur;

        setLayout(new BorderLayout(10, 0)); // Espacement horizontal
        setBackground(Color.WHITE); // Fond gris clair
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Marges intérieures

        // Panel de détails du joueur
        // Création du panel d'information
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5)); // GridLayout pour aligner les labels verticalement
        infoPanel.setOpaque(false); // Pour hériter du fond du panel principal

        // Nom et prénom du joueur
        JLabel nameLabel = new JLabel(joueur.getPrenom() + " " + joueur.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(nameLabel);

        // Club et âge
        JLabel clubAgeLabel = new JLabel("Club: " + equipeService.getEquipeById(1).getNom() + " | Âge: " + joueur.getAge());
        clubAgeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(clubAgeLabel);

        // Prix estimé
        JLabel priceLabel = new JLabel("Prix estimé: " + joueur.getPrix() + " millions €");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(priceLabel);


        // Bouton pour le statut du joueur
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton positionButton = new JButton("Attaquant"); // À dynamiser selon le joueur
        positionButton.setBackground(new Color(34, 139, 34)); // Vert foncé
        positionButton.setForeground(Color.WHITE); // Texte blanc
        positionButton.setBorder(new EmptyBorder(5, 10, 5, 10)); // Bordure pour le bouton
        positionButton.setMaximumSize(new Dimension(10,10));
        positionButton.setEnabled(false);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(positionButton, BorderLayout.NORTH);

        // Ajout des composants au panel principal
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST); // Positionnement du bouton à droite
        add(actionButton, BorderLayout.SOUTH); // Bouton d'action en bas
    }


    /*public static void main(String[] args) {
        JoueurService joueurService = new JoueurService();
        EquipeService equipeService = new EquipeService();
        PlayerPanel playerPanel = new PlayerPanel(new Joueur("Leclerc", "Charles", "Attaquant", 18, 5), new TransferBuyButton(0, joueurService), joueurService, equipeService);
        JFrame frame = new JFrame();
        frame.add(playerPanel);
        frame.setSize(500, 200); // Ajusté pour mieux s'adapter à tous les éléments
        frame.setVisible(true);
    }*/
}
