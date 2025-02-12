package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.teamManagmentView.PlayerEditDialog;

/**
 * Bouton personnalisé étendant ActionButton pour ajouter un nouveau joueur à une équipe.
 */
public class AddPlayerButton extends ActionButton {

    private JoueurService joueurService; // Service gérant les données des joueurs
    private EquipeService equipeService; // Service fournissant les informations sur les équipes

    /**
     * Constructeur qui initialise le bouton avec un label et les services nécessaires.
     * 
     * @param label Texte affiché sur le bouton.
     * @param joueurService Service pour la gestion des joueurs.
     * @param equipeService Service pour la gestion des équipes.
     */
    public AddPlayerButton(String label, JoueurService joueurService, EquipeService equipeService) {
        super(label); // Appelle le constructeur de la classe mère ActionButton
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        setBackground_color(new Color(80, 112, 242)); // Couleur de fond du bouton
        pressed_background_color = background_color.darker(); // Couleur lors de la pression du bouton
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAddAction(); // Définit l'action à exécuter lors du clic sur le bouton
            }
        });
        setPressedColor(); // Applique les effets visuels lors de la pression
    }

    /**
     * Méthode déclenchée lors de l'action du bouton, ouvrant un dialogue pour l'ajout d'un nouveau joueur.
     */
    private void performAddAction() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // Trouve la fenêtre parente pour le dialogue
        PlayerEditDialog dialog = new PlayerEditDialog(frame, new Joueur(), equipeService); // Crée un dialogue d'édition pour un nouveau joueur
        dialog.setVisible(true); // Affiche le dialogue
        Joueur editedPlayer = dialog.getJoueur(); // Récupère le joueur modifié après fermeture du dialogue
        joueurService.addPlayer(editedPlayer); // Ajoute le joueur modifié au service des joueurs
    }
}
