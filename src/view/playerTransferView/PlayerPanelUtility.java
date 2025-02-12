package view.playerTransferView;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import view.composent.ActionButton;
import view.composent.TransferBuyButton;
import view.composent.TransferSellButton;

import java.awt.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Classe utilitaire pour créer des panneaux affichant une liste de joueurs, soit pour les vendre soit pour les acheter.
 * Cette classe offre une méthode générique pour organiser ces panneaux basée sur une liste de joueurs.
 */
public class PlayerPanelUtility {

    /**
     * Crée un panneau listant les joueurs à vendre ou à acheter en fonction des paramètres donnés.
     * 
     * @param title Le titre du panneau, comme "Joueurs à Vendre" ou "Joueurs à Acheter".
     * @param players La liste des joueurs à afficher dans le panneau.
     * @param isSelling Indique si le panneau est destiné à la vente (true) ou à l'achat (false) de joueurs.
     * @param joueurService Service utilisé pour interagir avec les données des joueurs.
     * @param equipeService Service pour obtenir des informations sur les équipes, tel que les noms des équipes par leur ID.
     * @return Un JPanel configuré avec une liste de joueurs et les actions appropriées (vendre ou acheter).
     */
    public static JPanel createPlayerListingPanel(String title, List<Joueur> players, boolean isSelling, JoueurService joueurService, EquipeService equipeService) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel centralPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(2, 2, 2, 2);

        players.removeIf(joueur -> (!isSelling && joueur.getEquipe_id() == equipeService.getMonEquipe()) || (isSelling && joueur.getEquipe_id() != equipeService.getMonEquipe()));

        // Vérifie si la liste des joueurs est vide et affiche un message si c'est le cas
        if (players.isEmpty()) {
            centralPanel.add(new JLabel("Aucun joueur n'est actuellement disponible."), gbc);
        } else {
            // Crée un PlayerPanel pour chaque joueur dans la liste basé sur l'action requise
            for (Joueur joueur : players) {
                ActionButton button = isSelling ? new TransferSellButton() : new TransferBuyButton(joueur.getId(), joueurService, equipeService);
                centralPanel.add(createPlayerPanel(joueur, button, joueurService, equipeService), gbc);
            }
        }

        // Ajoute un composant espaceur en bas pour pousser tout le contenu vers le haut
        gbc.weighty = 1;
        centralPanel.add(new JPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(centralPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(Color.WHITE);
        return panel;
    }

    /**
     * Méthode auxiliaire pour créer un PlayerPanel individuel.
     * 
     * @param joueur Le joueur pour qui le panneau est créé.
     * @param actionButton Le bouton d'action associé au joueur.
     * @param joueurService Service pour la gestion des données des joueurs.
     * @param equipeService Service pour la gestion des données des équipes.
     * @return Un PlayerPanel configuré pour le joueur donné.
     */
    private static JPanel createPlayerPanel(Joueur joueur, ActionButton actionButton, JoueurService joueurService, EquipeService equipeService) {
        return new PlayerPanel(joueur, actionButton, joueurService, equipeService);
    }
}
