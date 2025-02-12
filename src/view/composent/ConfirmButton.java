package view.composent;

import java.awt.Color;

/**
 * Classe ConfirmButton qui étend ActionButton pour fournir un bouton stylisé utilisé principalement pour les actions de confirmation.
 */
public class ConfirmButton extends ActionButton {
    
    /**
     * Constructeur qui initialise le ConfirmButton avec un label et une couleur spécifique.
     * 
     * @param label Texte à afficher sur le bouton.
     */
    public ConfirmButton(String label) {
        super(label); // Appelle le constructeur de la classe parente ActionButton avec le label fourni.
        
        // Configuration des couleurs du bouton pour les différents états.
        setBackground_color(new Color(93, 173, 226)); // Couleur de fond normale en bleu clair.
        border_color = new Color(200, 200, 200); // Couleur de la bordure en gris clair.
        pressed_background_color = background_color.darker(); // Couleur de fond lorsque le bouton est pressé, plus foncée que la normale.
        
        setPressedColor(); // Applique les modifications de couleur lors des interactions avec le bouton (clic).
    }
}
