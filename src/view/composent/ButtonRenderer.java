package view.composent;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import service.interfaces.JoueurService;

// Custom renderer to place buttons in JTable cells
public class ButtonRenderer extends JPanel implements TableCellRenderer {
    EditButton editButton;
    SellButton sellButton;

    public ButtonRenderer(JoueurService joueurService) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        editButton = new EditButton("Modifier", -1, joueurService);  // Initial ID set to -1 or other invalid value
        sellButton = new SellButton("Vendre", -1, joueurService);
        add(editButton);
        add(sellButton);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        editButton.setTargetId(row);  // Update button with the current row ID
        sellButton.setTargetId(row);
        setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 242, 242)); // Alternating row color
        return this;
    }
}