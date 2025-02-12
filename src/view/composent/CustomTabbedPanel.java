package view.composent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;

import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * Classe personnalisée pour la gestion des onglets d'un JTabbedPane.
 * Cette classe modifie l'apparence des onglets en ajoutant des coins arrondis,
 * en ajustant les couleurs et en supprimant les bordures inutiles.
 */
public class CustomTabbedPanel extends JTabbedPane {

    /**
     * Constructeur par défaut qui applique le style personnalisé aux onglets.
     */
    public CustomTabbedPanel() {
        super();
        setBackground(Color.WHITE);
        setForeground(Color.WHITE);
        setUI(new CustomTabbedPaneUI());
    }

    /**
     * Classe interne définissant l'apparence personnalisée du JTabbedPane.
     */
    private static class CustomTabbedPaneUI extends BasicTabbedPaneUI {

        /**
         * Configure les valeurs par défaut des onglets, notamment l'espace autour du contenu.
         */
        @Override
        protected void installDefaults() {
            super.installDefaults();
            contentBorderInsets = new Insets(20, 0, 0, 0); // Ajoute un espacement entre les onglets et le contenu
        }

        /**
         * Modifie la hauteur des onglets et ajuste leur position.
         */
        @Override
        protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
            Rectangle tabRect = rects[tabIndex];
            tabRect.y = 2;  // Décale légèrement les onglets vers le bas
            tabRect.height = 30; // Ajuste la hauteur des onglets
            super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
        }

        /**
         * Ajuste la position du texte dans les onglets.
         */
        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex,
                                 String title, Rectangle textRect, boolean isSelected) {
            textRect.y = 8;  // Ajuste la position verticale du texte
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
        }

        /**
         * Dessine l'arrière-plan des onglets avec des coins arrondis et une couleur différente selon l'onglet sélectionné.
         */
        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Couleur différente pour l'onglet sélectionné
            Color fillColor = isSelected ? new Color(62, 88, 191) : new Color(100, 100, 100);
            g2.setColor(fillColor);

            int arc = 10; // Rayon des coins arrondis

            // Vérification si l'onglet est le premier ou le dernier
            boolean isFirstTab = (tabIndex == 0);
            boolean isLastTab = (tabIndex == tabPane.getTabCount() - 1);

            Path2D.Double path = new Path2D.Double();

            if (isFirstTab) {
                // Arrondi uniquement sur les coins gauches
                path.moveTo(x + arc, y);
                path.lineTo(x + w, y);
                path.lineTo(x + w, y + h);
                path.lineTo(x + arc, y + h);
                path.quadTo(x, y + h, x, y + h - arc);
                path.lineTo(x, y + arc);
                path.quadTo(x, y, x + arc, y);
                path.closePath();
            } else if (isLastTab) {
                // Arrondi uniquement sur les coins droits
                path.moveTo(x, y);
                path.lineTo(x + w - arc, y);
                path.quadTo(x + w, y, x + w, y + arc);
                path.lineTo(x + w, y + h - arc);
                path.quadTo(x + w, y + h, x + w - arc, y + h);
                path.lineTo(x, y + h);
                path.closePath();
            } else {
                // Rectangle normal pour les onglets intermédiaires
                path.moveTo(x, y);
                path.lineTo(x + w, y);
                path.lineTo(x + w, y + h);
                path.lineTo(x, y + h);
                path.closePath();
            }

            g2.fill(path);
        }

        /**
         * Supprime la bordure des onglets.
         */
        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            // Pas de bordure pour un design épuré
        }

        /**
         * Supprime la bordure du contenu du panneau.
         */
        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            // Ne rien dessiner pour masquer la bordure du contenu
        }
    }
}
