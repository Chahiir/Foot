package view.composent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

////custom renderer for bold header
public class BoldHeaderRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setFont(getFont().deriveFont(Font.BOLD,14)); // Mettre la police en gras
        setBackground(new Color(230, 230, 230));
        return this;
    }
}