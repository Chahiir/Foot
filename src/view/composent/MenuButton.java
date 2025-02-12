package view.composent;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe MenuButton représentant un bouton de menu interactif avec un effet de sélection et de survol.
 */
public class MenuButton extends JButton {
    private boolean selected; // Indique si le bouton est sélectionné
    private Color background_color; // Couleur de fond normale
    private Color hover_background_color; // Couleur de fond lors du survol

    /**
     * Constructeur de MenuButton.
     *
     * @param text                Texte du bouton
     * @param isSelected          Indique si le bouton est sélectionné
     * @param background_color    Couleur de fond normale
     * @param hover_background_color Couleur de fond lors du survol
     */
    public MenuButton(String text, boolean isSelected, Color background_color, Color hover_background_color) {
        super(text);
        setBackground_color(background_color);
        setHover_background_color(hover_background_color);
        setForeground(Color.WHITE); // Texte en blanc pour une meilleure lisibilité
        setOpaque(true);
        setBorderPainted(false); // Suppression de la bordure par défaut
        setFocusPainted(false); // Suppression de l'effet de focus par défaut
        setSelected(isSelected);  
        setupMouseEffects(); // Ajout des effets de survol et de clic
    }

    /**
     * Configure les effets de survol et de clic pour le bouton.
     */
    private void setupMouseEffects() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if (!selected) {
                    setBackground(hover_background_color); // Change la couleur de fond au survol
                }
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                if (!selected) {
                    setBackground(background_color); // Retour à la couleur normale en quittant le bouton
                }
            }
            
            @Override
            public void mousePressed(MouseEvent evt) {
                if (!selected) {
                    setForeground(Color.BLUE); // Change la couleur du texte lors du clic
                    setBackground(background_color); // Rétablit la couleur de fond
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                if (!selected) {
                    setForeground(Color.WHITE); // Restaure la couleur du texte après le clic
                }
            }
        });
    }

    /**
     * Définit l'état de sélection du bouton.
     *
     * @param selected Vrai si le bouton est sélectionné, faux sinon.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            setForeground(new Color(100, 149, 237)); // Bleu clair pour indiquer la sélection
            setBackground(hover_background_color); // Fond plus clair pour l'effet sélectionné
            setEnabled(false); // Désactive le bouton pour éviter de le re-cliquer
        } else {
            setForeground(Color.WHITE);
            setBackground(background_color);
            setEnabled(true);
        }
    }

    /**
     * Vérifie si le bouton est sélectionné.
     *
     * @return Vrai si sélectionné, faux sinon.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Définit la couleur de fond normale.
     *
     * @param background_color Nouvelle couleur de fond normale.
     */
    public void setBackground_color(Color background_color) {
        this.background_color = background_color;
        setBackground(background_color);
    }

    /**
     * Définit la couleur de fond au survol.
     *
     * @param hover_background_color Nouvelle couleur de fond lors du survol.
     */
    public void setHover_background_color(Color hover_background_color) {
        this.hover_background_color = hover_background_color;
    }
}
