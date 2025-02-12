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

/**
 * PlayerPanel est un composant graphique qui affiche des informations sur un joueur ainsi que des actions spécifiques.
 */
public class PlayerPanel extends JPanel {

    /**
     * Constructeur qui initialise le panneau avec des informations détaillées sur le joueur et des boutons d'action.
     * 
     * @param joueur L'objet Joueur contenant les informations à afficher.
     * @param actionButton Le bouton d'action qui peut être un bouton d'achat ou de vente, selon le contexte.
     * @param joueurService Service pour gérer les interactions liées aux joueurs.
     * @param equipeService Service pour obtenir les informations sur les équipes.
     */
    public PlayerPanel(Joueur joueur, ActionButton actionButton, JoueurService joueurService, EquipeService equipeService) {
        setLayout(new BorderLayout(10, 0)); // Définit le layout avec un espacement horizontal
        setBackground(Color.WHITE); // Définit la couleur de fond du panneau
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Définit les marges intérieures

        // Panel de détails du joueur
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5)); // GridLayout pour aligner verticalement les informations
        infoPanel.setOpaque(false); // Rend le panel transparent pour utiliser le fond du panel principal

        // Nom et prénom du joueur
        JLabel nameLabel = new JLabel(joueur.getPrenom() + " " + joueur.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Définit la police et la taille du texte
        infoPanel.add(nameLabel);

        // Club et âge
        JLabel clubAgeLabel = new JLabel("Club: " + equipeService.getEquipeById(joueur.getEquipe_id()).getNom() + " | Âge: " + joueur.getAge());
        clubAgeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(clubAgeLabel);

        // Prix estimé
        JLabel priceLabel = new JLabel("Prix estimé: " + joueur.getPrix() + " millions €");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(priceLabel);

        // Bouton pour afficher le statut du joueur
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton positionButton = new InfoButton(joueur.getPosition());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(positionButton, BorderLayout.NORTH);

        // Ajout des composants au panel principal
        add(infoPanel, BorderLayout.CENTER); // Ajoute le panel d'informations au centre
        add(buttonPanel, BorderLayout.EAST); // Ajoute le panel de bouton à l'est
        add(actionButton, BorderLayout.SOUTH); // Ajoute le bouton d'action en bas
    }
}
