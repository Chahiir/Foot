package view.composent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe personnalisée pour modifier l'affichage des cellules dans un JTable.
 * Cette classe permet d'appliquer un fond alterné sur les lignes et d'ajouter une marge intérieure (padding).
 */
public class CustomCellRenderer extends DefaultTableCellRenderer {

    /**
     * Méthode qui définit l'affichage des cellules dans le tableau.
     * Elle applique une couleur d'arrière-plan alternée et ajoute un espace intérieur.
     *
     * @param table      La JTable concernée.
     * @param value      La valeur affichée dans la cellule.
     * @param isSelected Indique si la cellule est sélectionnée.
     * @param hasFocus   Indique si la cellule a le focus.
     * @param row        L'index de la ligne de la cellule.
     * @param column     L'index de la colonne de la cellule.
     * @return           Le composant mis à jour avec le style défini.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Appelle la méthode parente pour conserver le comportement de base
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Applique un fond alterné : blanc pour les lignes paires, gris clair pour les lignes impaires
        if (!isSelected) {
            setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 242, 242));
        }

        // Ajoute un espace intérieur (padding) pour améliorer la lisibilité
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return this;
    }
}
