package view.composent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe personnalisée pour le rendu des en-têtes de colonnes d'un JTable.
 * Cette classe permet de mettre les en-têtes en gras avec une couleur de fond spécifique.
 */
public class BoldHeaderRenderer extends DefaultTableCellRenderer {
    
    /**
     * Méthode qui personnalise l'apparence des en-têtes de colonnes.
     *
     * @param table     Le JTable auquel l'en-tête appartient.
     * @param value     La valeur de la cellule (texte de l'en-tête).
     * @param isSelected Indique si la cellule est sélectionnée (non pertinent pour les en-têtes).
     * @param hasFocus  Indique si la cellule a le focus (non pertinent pour les en-têtes).
     * @param row       L'index de la ligne (toujours -1 pour un en-tête).
     * @param column    L'index de la colonne actuelle.
     * @return          Le composant configuré pour être affiché en tant qu'en-tête.
     */
    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        // Appelle la méthode de la classe parent pour obtenir un composant de base
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Définition du style de l'en-tête
        setFont(getFont().deriveFont(Font.BOLD, 14)); // Met la police en gras avec une taille de 14
        setBackground(new Color(230, 230, 230)); // Définit la couleur de fond en gris clair
        setHorizontalAlignment(CENTER); // Centre le texte dans la cellule
        
        return this;
    }
}
