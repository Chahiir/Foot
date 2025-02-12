package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.interfaces.JoueurService;

/**
 * Classe SellButton qui hérite de ActionButton.
 * Ce bouton permet de marquer un joueur comme étant à vendre.
 */
public class SellButton extends ActionButton {
    
    private int targetId; // Identifiant du joueur associé au bouton
    private JoueurService joueurService; // Service de gestion des joueurs
    
    /**
     * Constructeur du bouton de mise en vente.
     * 
     * @param label Texte affiché sur le bouton.
     * @param targetId ID du joueur concerné.
     * @param joueurService Instance du service permettant de gérer les joueurs.
     */
    public SellButton(String label, int targetId, JoueurService joueurService) {
        super(label);
        this.targetId = targetId;  // Initialise avec l'identifiant du joueur
        this.joueurService = joueurService; // Initialise avec l'instance du service
        setBackground_color(new Color(231, 76, 60)); // Couleur rouge pour indiquer une action importante
        pressed_background_color = background_color.darker(); // Assombrit la couleur lors du clic
        
        // Ajoute un écouteur d'événement pour exécuter l'action de vente
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSellAction();
            }
        });

        setPressedColor(); // Applique l'effet de pression visuel
    }

    /**
     * Définit un nouvel ID pour le bouton s'il est réutilisé pour un autre joueur.
     * 
     * @param id Nouvel ID du joueur
     */
    public void setTargetId(int id) {
        this.targetId = id;
    }

    /**
     * Méthode exécutée lorsqu'on clique sur le bouton.
     * Marque le joueur comme étant à vendre en utilisant JoueurService.
     */
    private void performSellAction() {
        joueurService.markPlayerToSell(targetId);
    }
}
