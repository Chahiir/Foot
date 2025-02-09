package view.composent;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {
    private boolean selected;
    private Color background_color;
	private Color hover_background_color;

    public MenuButton(String text, boolean isSelected, Color background_color, Color hover_background_color) {
        super(text);
        setBackground_color(background_color);
        setHover_background_color(hover_background_color);
        setForeground(Color.WHITE);
        setOpaque(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setSelected(isSelected);  
        setupMouseEffects();
    }

    private void setupMouseEffects() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!selected) {
                    setBackground(hover_background_color); // Plus foncé lors du survol
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (!selected) {
                    setBackground(background_color); // Retour à la couleur normale
                }
            }
            
            @Override
            public void mousePressed(MouseEvent evt) {
                if (!selected) {
                    setForeground(Color.BLUE); 
                    setBackground(background_color);
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                if (!selected) {
                    setForeground(Color.WHITE);
                }
            }
        });
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            setForeground(new Color(100, 149, 237)); // Couleur bleue pour le texte
            setBackground(hover_background_color); // Couleur de fond plus claire
            setEnabled(false); // Désactiver le bouton pour éviter les clics
        } else {
            setForeground(Color.WHITE);
            setBackground(background_color);
            setEnabled(true);
        }
    }

    public boolean isSelected() {
        return selected;
    }

	public void setBackground_color(Color background_color) {
		this.background_color = background_color;
		setBackground(background_color);
	}

	public void setHover_background_color(Color hover_background_color) {
		this.hover_background_color = hover_background_color;
	}
}
