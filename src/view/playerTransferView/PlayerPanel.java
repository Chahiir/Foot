package view.playerTransferView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.composent.ActionButton;
import view.composent.InfoButton;

public class PlayerPanel extends JPanel{

    public PlayerPanel(Joueur joueur, ActionButton actionButton, JoueurService joueurService, EquipeService equipeService) {

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
        JLabel clubAgeLabel = new JLabel("Club: " + equipeService.getEquipeById(joueur.getEquipe_id()).getNom() + " | Âge: " + joueur.getAge());
        clubAgeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(clubAgeLabel);

        // Prix estimé
        JLabel priceLabel = new JLabel("Prix estimé: " + joueur.getPrix() + " millions €");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(priceLabel);


        // Bouton pour le statut du joueur
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton positionButton = new InfoButton(joueur.getPosition()); 
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(positionButton, BorderLayout.NORTH);

        // Ajout des composants au panel principal
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST); // Positionnement du bouton à droite
        add(actionButton, BorderLayout.SOUTH); // Bouton d'action en bas
    }

}
