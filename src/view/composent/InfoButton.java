package view.composent;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Classe InfoButton qui étend ActionButton pour créer un bouton d'information.
 * Ce bouton est utilisé pour afficher des informations telles que la position d'un joueur.
 */
public class InfoButton extends ActionButton {
    
    /**
     * Constructeur du bouton d'information.
     * @param label Texte affiché sur le bouton.
     */
    public InfoButton(String label) {
        super(label);
        setBackground_color(new Color(34, 139, 34)); // Définit une couleur verte foncée pour le fond
        border_color = new Color(200, 200, 200); // Définition de la couleur de la bordure en gris clair
        setForeground(Color.WHITE); // Définit la couleur du texte en blanc
        setMaximumSize(new Dimension(10, 10)); // Définit la taille maximale du bouton
    }
}
