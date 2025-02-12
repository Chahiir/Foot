package view.composent;

import java.awt.Color;

/**
 * Classe TransferSellButton qui hérite de ActionButton.
 * Ce bouton est utilisé pour indiquer qu'un joueur est en attente d'achat.
 */
public class TransferSellButton extends ActionButton {
    
    /**
     * Constructeur du bouton de mise en vente d'un joueur.
     * Affiche "En attente d'achat" pour signaler qu'un joueur a été mis sur le marché des transferts.
     */
    public TransferSellButton() {
        super("En attente d'achat"); // Définit le texte du bouton

        // Style du bouton
        setBackground_color(new Color(226, 79, 53)); // Rouge pour indiquer un statut d'attente de vente
        border_color = new Color(200, 200, 200); // Bordure grise
    }
}
