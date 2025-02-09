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

public class CustomTabbedPanel extends JTabbedPane {
	
	public CustomTabbedPanel() {
		super();
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setUI(new CustomTabbedPaneUI());
	}

	private static class CustomTabbedPaneUI extends BasicTabbedPaneUI {
		
		@Override
	    protected void installDefaults() {
	        super.installDefaults();
	        contentBorderInsets = new Insets(20, 0, 0, 0);
	    }
		
		@Override
	    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
	        Rectangle tabRect = rects[tabIndex];
	        tabRect.y = 2;
	        tabRect.height = 30;
	        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
	    }

		@Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex,
                                 String title, Rectangle textRect, boolean isSelected) {
            textRect.y = 8;
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
        }

		
	    @Override
	    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	    	Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        Color fillColor = isSelected ? new Color(62,88,191) : new Color(100, 100, 100);
	        g2.setColor(fillColor);
	        
	        int arc = 10; // Rayon pour les coins arrondis
	        // Condition pour déterminer si c'est le premier ou le dernier onglet
	        boolean isFirstTab = (tabIndex == 0);
	        boolean isLastTab = (tabIndex == tabPane.getTabCount() - 1);

	        Path2D.Double path = new Path2D.Double();
	        if (isFirstTab) {
	        	// Round only the top-left corners
	        	 path.moveTo(x + arc, y);
	             path.lineTo(x + w, y);
	             path.lineTo(x + w, y + h);
	             path.lineTo(x + arc, y + h);
	             path.quadTo(x, y + h, x, y + h - arc);
	             path.lineTo(x, y + arc);
	             path.quadTo(x, y, x + arc, y);
	             path.closePath();
	        } else if (isLastTab) {
	            // Round only the right corners
	        	path.moveTo(x, y);
	            path.lineTo(x + w - arc, y);
	            path.quadTo(x + w, y, x + w, y + arc);
	            path.lineTo(x + w, y + h - arc);
	            path.quadTo(x + w, y + h, x + w - arc, y + h);
	            path.lineTo(x, y + h);
	            path.closePath();
	        } else {
	            // Regular rectangle for middle tabs
	            path.moveTo(x, y);
	            path.lineTo(x + w, y);
	            path.lineTo(x + w, y + h);
	            path.lineTo(x, y + h);
	            path.closePath();
	        }

	        g2.fill(path);
	    }

	    @Override
	    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
	        // Vous pouvez supprimer cette méthode si vous ne voulez pas de bordure du tout
	    }

	    @Override
	    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
	        // Ne rien peindre pour cacher la bordure du panneau de contenu
	    }
    }
}
