package view.composent;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;

/**
 * Classe d'éditeur personnalisé pour gérer les boutons dans les cellules de la table de gestion des joueurs.
 * Cette classe permet d'afficher et de gérer les actions des boutons "Modifier" et "Vendre" 
 * pour chaque ligne du tableau.
 */
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    
    private EditButton editButton;  // Bouton pour modifier un joueur
    private SellButton sellButton;  // Bouton pour mettre en vente un joueur
    private JPanel panel;           // Panneau contenant les boutons

    /**
     * Constructeur de l'éditeur de cellule.
     * 
     * @param joueurService Service permettant la gestion des joueurs.
     * @param equipeService Service permettant la gestion des équipes.
     */
    public ButtonEditor(JoueurService joueurService, EquipeService equipeService) {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alignement à gauche des boutons
        editButton = new EditButton("Modifier", -1, joueurService, equipeService);
        sellButton = new SellButton("Vendre", -1, joueurService);
        
        // Ajout des boutons au panneau
        panel.add(editButton);
        panel.add(sellButton);

        // Ajout des écouteurs d'événements pour arrêter l'édition après un clic
        editButton.addActionListener(e -> fireEditingStopped());
        sellButton.addActionListener(e -> fireEditingStopped());
    }

    /**
     * Retourne le composant à afficher dans la cellule lors de l'édition.
     * 
     * @param table     La JTable concernée.
     * @param value     La valeur de la cellule (utilisée pour récupérer l'ID du joueur).
     * @param isSelected Indique si la cellule est sélectionnée.
     * @param row       L'index de la ligne actuelle.
     * @param column    L'index de la colonne actuelle.
     * @return          Le composant affiché dans la cellule.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // Mise à jour des IDs des boutons en fonction de la valeur de la cellule
        if (value instanceof Integer) {
            editButton.setTargetId((int) value);
            sellButton.setTargetId((int) value);
        }
        return panel;
    }

    /**
     * Retourne la valeur de la cellule après l'édition (non utilisée ici).
     * 
     * @return null car aucune valeur spécifique n'est requise.
     */
    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
