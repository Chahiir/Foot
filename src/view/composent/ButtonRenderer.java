package view.composent;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

// Custom renderer to place buttons in JTable cells
public class ButtonRenderer extends JPanel implements TableCellRenderer {
    EditButton editButton;
    DeleteButton deleteButton;

    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        editButton = new EditButton("Modifier", -1);  // Initial ID set to -1 or other invalid value
        deleteButton = new DeleteButton("Vendre", -1);
        add(editButton);
        add(deleteButton);
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        editButton.setTargetId(row);  // Update button with the current row ID
        deleteButton.setTargetId(row);
        setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 242, 242)); // Alternating row color
        return this;
    }
}