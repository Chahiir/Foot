package view.composent;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import service.interfaces.EquipeService;
import service.interfaces.JoueurService;

/**
 * Classe de rendu personnalisée pour afficher des boutons dans une cellule de JTable.
 * Cette classe permet d'afficher les boutons "Modifier" et "Vendre" dans la colonne des actions d'un JTable.
 */
public class ButtonRenderer extends JPanel implements TableCellRenderer {
    
    private EditButton editButton;  // Bouton d'édition
    private SellButton sellButton;  // Bouton de mise en vente

    /**
     * Constructeur du rendu des boutons.
     * 
     * @param joueurService Service permettant la gestion des joueurs.
     * @param equipeService Service permettant la gestion des équipes.
     */
    public ButtonRenderer(JoueurService joueurService, EquipeService equipeService) {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Alignement à gauche
        setOpaque(true); // Rendre le fond visible
        
        // Initialisation des boutons avec un ID par défaut (-1)
        editButton = new EditButton("Modifier", -1, joueurService, equipeService);
        sellButton = new SellButton("Vendre", -1, joueurService);
        
        // Ajout des boutons au panneau
        add(editButton);
        add(sellButton);
    }

    /**
     * Méthode permettant d'obtenir le composant affiché dans une cellule spécifique de la JTable.
     * 
     * @param table     La JTable concernée.
     * @param value     La valeur de la cellule (non utilisée ici).
     * @param isSelected Indique si la cellule est sélectionnée.
     * @param hasFocus  Indique si la cellule a le focus.
     * @param row       L'index de la ligne actuelle.
     * @param column    L'index de la colonne actuelle.
     * @return          Le composant à afficher dans la cellule.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Mise à jour de l'ID cible des boutons pour correspondre à la ligne actuelle
        editButton.setTargetId(row);
        sellButton.setTargetId(row);

        // Alternance des couleurs de fond pour améliorer la lisibilité du tableau
        setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 242, 242));

        return this;
    }
}
