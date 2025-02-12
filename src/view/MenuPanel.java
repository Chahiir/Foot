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

/**
 * MenuPanel est un panneau latéral qui contient des boutons de navigation pour différentes sections de l'application.
 * Chaque bouton permet de basculer l'affichage principal de la fenêtre à la section correspondante.
 */
public class MenuPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(45, 45, 45);
    private static final Color HOVER_BACKGROUND_COLOR = new Color(25, 25, 25);
    private int selected_index;
    private MainFrame mainFrame;
    private ArrayList<MenuButton> menuButtons;

    /**
     * Constructeur du MenuPanel.
     * @param mainFrame La fenêtre principale contenant ce panneau, utilisée pour les interactions.
     * @param selectedIndex L'indice du bouton actuellement sélectionné.
     */
    public MenuPanel(MainFrame mainFrame, int selectedIndex) {
        this.selected_index = selectedIndex;
        this.mainFrame = mainFrame;
        this.menuButtons = new ArrayList<>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND_COLOR);  // Définit la couleur de fond pour le panneau du menu.

        // Initialisation et ajout des boutons de menu
        menuButtons.add(createMenuButton("Gestion de l'Équipe", 0));
        menuButtons.add(createMenuButton("Matchs et Résultats", 1));
        menuButtons.add(createMenuButton("Blessures", 2));
        menuButtons.add(createMenuButton("Marché", 3));

        // Ajout de chaque bouton au panneau
        for (JButton button : menuButtons) {
            add(button);
        }
    }

    /**
     * Crée et configure un bouton de menu.
     * @param text Le texte à afficher sur le bouton.
     * @param index L'index du bouton correspondant à son panneau cible.
     * @return Un MenuButton configuré.
     */
    private MenuButton createMenuButton(String text, int index) {
        MenuButton button = new MenuButton(text, index == selected_index, BACKGROUND_COLOR, HOVER_BACKGROUND_COLOR);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centre le bouton dans le panneau.
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));  // Assure une largeur maximale.
        button.setMinimumSize(new Dimension(100, 200));  // Assure une taille minimale.

        // Ajoute un écouteur d'événements pour changer le panneau affiché lorsque le bouton est pressé.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setSelectedPanel(index);  // Change le panneau affiché dans MainFrame.
                setSelectedIndex(index);  // Met à jour l'indice du bouton sélectionné.
            }
        });
        return button;
    }

    /**
     * Met à jour l'état de sélection de tous les boutons en fonction de l'index sélectionné.
     * @param index L'indice du bouton actuellement sélectionné.
     */
    private void setSelectedIndex(int index) {
        for (int i = 0; i < menuButtons.size(); i++) {
            menuButtons.get(i).setSelected(i == index);
        }
    }
}
