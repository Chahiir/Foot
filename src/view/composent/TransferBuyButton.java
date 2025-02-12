package view.composent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;

/**
 * Classe TransferBuyButton qui hérite de ActionButton.
 * Ce bouton permet d'acheter un joueur et de l'ajouter à l'équipe courante.
 */
public class TransferBuyButton extends ActionButton {
    
    private int targetId; // Identifiant du joueur à acheter
    private JoueurService joueurService; // Service de gestion des joueurs
    private EquipeService equipeService; // Service de gestion des équipes
    
    /**
     * Constructeur du bouton d'achat d'un joueur.
     * 
     * @param targetId ID du joueur concerné par l'achat.
     * @param joueurService Instance du service permettant de gérer les joueurs.
     * @param equipeService Instance du service permettant de gérer les équipes.
     */
    public TransferBuyButton(int targetId, JoueurService joueurService, EquipeService equipeService) {
        super("Acheter"); // Définit le texte du bouton
        this.targetId = targetId;  // Initialise avec l'identifiant du joueur
        this.joueurService = joueurService; // Injecte l'instance du service de gestion des joueurs
        this.equipeService = equipeService; // Injecte l'instance du service de gestion des équipes
        
        // Style du bouton
        setBackground_color(new Color(93, 173, 226)); // Bleu clair pour l'achat
        border_color = new Color(200, 200, 200);
        pressed_background_color = background_color.darker();
        
        // Ajoute un écouteur d'événement pour effectuer l'achat
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performBuyAction();
            }
        });

        setPressedColor();
    }
    
    /**
     * Met à jour l'ID du joueur à acheter (utile si le bouton est réutilisé).
     * 
     * @param id Nouvel ID du joueur
     */
    public void setTargetId(int id) {
        this.targetId = id;
    }
    
    /**
     * Méthode exécutée lorsqu'on clique sur le bouton.
     * Effectue le transfert du joueur vers l'équipe courante.
     */
    private void performBuyAction() {
        int myTeamId = equipeService.getMonEquipe(); // Récupère l'ID de l'équipe courante
        joueurService.transferPlayer(targetId, myTeamId); // Exécute le transfert
    }
}
