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
 * Classe EditButton qui étend ActionButton pour fournir un bouton spécifique à l'édition de joueurs.
 */
public class EditButton extends ActionButton {
    
    private int targetId;  // ID cible du joueur à éditer
    private JoueurService joueurService; // Service pour la gestion des joueurs
    private EquipeService equipeService; // Service pour la gestion des équipes
    
    /**
     * Constructeur pour créer un bouton d'édition.
     * @param label Texte du bouton.
     * @param targetId Identifiant du joueur ciblé pour l'édition.
     * @param joueurService Service de gestion des joueurs.
     * @param equipeService Service de gestion des équipes.
     */
    public EditButton(String label, int targetId, JoueurService joueurService, EquipeService equipeService) {
        super(label);
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        this.targetId = targetId;
        setBackground_color(new Color(93, 173, 226));  // Couleur de fond bleu
        border_color = new Color(200, 200, 200);        // Couleur de bordure grise
        pressed_background_color = background_color.darker();  // Couleur plus foncée lors du clic
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performEditAction();  // Exécute l'action d'édition
            }
        });
        setPressedColor();  // Applique l'effet visuel lors du clic
    }
    
    /**
     * Met à jour l'identifiant du joueur ciblé pour l'édition.
     * @param id Nouvel identifiant du joueur.
     */
    public void setTargetId(int id) {
        this.targetId = id;
    }
    
    /**
     * Déclenche le processus d'édition pour le joueur ciblé.
     * Lance une boîte de dialogue d'édition et met à jour les informations du joueur via les services.
     */
    private void performEditAction() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Trouve la fenêtre parente
        PlayerEditDialog dialog = new PlayerEditDialog(frame, joueurService.getPlayerById(targetId), equipeService);  // Crée le dialogue d'édition
        dialog.setVisible(true);  // Affiche le dialogue
        Joueur editedPlayer = dialog.getJoueur();  // Récupère le joueur édité
        joueurService.updatePlayer(editedPlayer);  // Met à jour le joueur dans le service
    }
}