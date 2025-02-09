package view;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.composent.MenuButton;


public class MenuPanel extends JPanel {
	
	private static final Color BACKGROUND_COLOR = new Color(45, 45, 45);
	private static final Color HOVER_BACKGROUND_COLOR = new Color(25, 25, 25);
	private int selected_index;
	
    public MenuPanel(int selectedIndex) {
    	this.selected_index = selectedIndex;
    	
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND_COLOR);  // Couleur de fond du panneau

        // Boutons du menu
        add(createMenuButton("Gestion de l'Équipe",0));
        add(createMenuButton("Matchs et Résultats",1));
        add(createMenuButton("Blessures",2));
        add(createMenuButton("Marché",3));
    }

    private JButton createMenuButton(String text, int index) {
    	MenuButton button = new MenuButton(text, index == selected_index, BACKGROUND_COLOR,HOVER_BACKGROUND_COLOR);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Configurer la dimension maximale pour étendre la largeur
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        
        button.setMinimumSize(new Dimension(100, 200));  // Assure que tous les boutons ont une taille minimum


        return button;
    }
}
