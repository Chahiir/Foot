package view.composent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

/**
 * Classe abstraite ActionButton qui étend JButton pour créer des boutons avec des bords arrondis
 * et une gestion personnalisée des couleurs d'état.
 */
public abstract class ActionButton extends JButton {
    private static final float ARC_WIDTH = 16f;  // Largeur de l'arc pour les coins arrondis
    private static final float ARC_HEIGHT = 16f; // Hauteur de l'arc pour les coins arrondis
    private Color current_background_color;      // Couleur de fond actuelle du bouton
    protected Color background_color = new Color(0, 155, 221); // Couleur de fond par défaut
    protected Color border_color = new Color(200, 200, 200);    // Couleur de la bordure
    protected Color pressed_background_color = background_color.darker(); // Couleur de fond lorsque le bouton est pressé
    
    /**
     * Constructeur pour créer un ActionButton avec un label.
     * @param label Le texte du bouton.
     */
    public ActionButton(String label) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        current_background_color = background_color; // Initialise la couleur de fond actuelle avec la couleur de fond par défaut
    }

    /**
     * Définit la couleur de fond du bouton.
     * @param background_color Nouvelle couleur de fond.
     */
    public void setBackground_color(Color background_color) {
        this.background_color = background_color;
        this.current_background_color = background_color;
    }
    
    /**
     * Surcharge de la méthode paintComponent pour dessiner un bouton avec des bords arrondis.
     * @param g L'objet Graphics utilisé pour dessiner le bouton.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2d.setColor(current_background_color);
        g2d.fill(round);
        g2d.setColor(border_color);
        g2d.draw(round);
        g2d.dispose();
        super.paintComponent(g);
    }
    
    /**
     * Configure les actions de couleur lors des événements de pression de la souris.
     */
    protected void setPressedColor() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                current_background_color = pressed_background_color; // Change la couleur lors du pressage
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                current_background_color = background_color; // Réinitialise la couleur lorsque la souris est relâchée
                repaint();
            }
        });
    }
}
