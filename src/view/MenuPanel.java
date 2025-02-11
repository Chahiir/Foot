package view;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.composent.MenuButton;


public class MenuPanel extends JPanel {
	
	private static final Color BACKGROUND_COLOR = new Color(45, 45, 45);
	private static final Color HOVER_BACKGROUND_COLOR = new Color(25, 25, 25);
	private int selected_index;
    private MainFrame mainFrame;
    private ArrayList<MenuButton> menuButtons;
	
    public MenuPanel(MainFrame mainFrame, int selectedIndex) {
    	this.selected_index = selectedIndex;
    	this.mainFrame = mainFrame;
        this.menuButtons = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND_COLOR);  // Couleur de fond du panneau

        // Boutons du menu
        menuButtons.add(createMenuButton("Gestion de l'Équipe",0));
        menuButtons.add(createMenuButton("Matchs et Résultats",1));
        menuButtons.add(createMenuButton("Blessures",2));
        menuButtons.add(createMenuButton("Marché",3));

        for(JButton button : menuButtons){
            add(button);
        }
    }

    private MenuButton createMenuButton(String text, int index) {
    	MenuButton button = new MenuButton(text, index == selected_index, BACKGROUND_COLOR,HOVER_BACKGROUND_COLOR);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Configurer la dimension maximale pour étendre la largeur
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        
        button.setMinimumSize(new Dimension(100, 200));  // Assure que tous les boutons ont une taille minimum

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setSelectedPanel(index); // Change le panneau
                setSelectedIndex(index);
            }        
        });
        return button;
    }

    private void setSelectedIndex(int index) {
        for (int i = 0; i<menuButtons.size(); i++){
            menuButtons.get(i).setSelected(i==index);
        }
    }
}
