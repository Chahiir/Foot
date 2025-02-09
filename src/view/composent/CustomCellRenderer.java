package view.composent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//custom renderer for alternate background
public class CustomCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(row % 2 == 0 ? Color.WHITE : new Color(242, 242, 242)); // Alternating row color
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        return this;
    }
}