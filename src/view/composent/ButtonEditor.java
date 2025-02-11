package view.composent;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import service.interfaces.JoueurService;

// Custom editor to handle actions in JTable cells
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private EditButton editButton;
    private SellButton sellButton;
    private JPanel panel;

    public ButtonEditor(JoueurService joueurService) {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        editButton = new EditButton("Modifier", -1, joueurService);
        sellButton = new SellButton("Vendre", -1, joueurService);
        panel.add(editButton);
        panel.add(sellButton);

        editButton.addActionListener(e -> {
            fireEditingStopped();  // Action handling logic
        });
        sellButton.addActionListener(e -> {
            fireEditingStopped();  // Action handling logic
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        editButton.setTargetId((int)value);  // Update button with the current row ID
        sellButton.setTargetId((int)value);
        return panel;
    }

    public Object getCellEditorValue() {
        return null;  // Or appropriate return
    }
}