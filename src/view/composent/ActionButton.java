package view.composent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public abstract class ActionButton extends JButton {
	 private static final float ARC_WIDTH = 16f;
     private static final float ARC_HEIGHT = 16f;
     private Color current_background_color;
     protected Color background_color = new Color(0, 155, 221);
	 protected Color border_color = new Color(200, 200, 200);
     protected Color pressed_background_color = background_color.darker();
    
    public ActionButton(String label) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        current_background_color = background_color;
    }

    public void setBackground_color(Color background_color) {
		this.background_color = background_color;
		this.current_background_color = background_color;
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2d.setColor(current_background_color);
        g2d.fill(round);
        g2d.setColor(border_color);
        g2d.draw(round);
        g2d.dispose();
        super.paintComponent(g);
    }
    
    protected void setPressedColor() {
    	addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
            	current_background_color = pressed_background_color;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	current_background_color = background_color; // Set to hover color if mouse is still over the button
                repaint();
            }
        });
    }
}
