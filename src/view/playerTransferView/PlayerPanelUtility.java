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
 * Utility class to create player listing panels for different scenarios like selling or buying players.
 * This class provides a generic way to create and manage player panels based on a list of players.
 */
public class PlayerPanelUtility {

    /**
     * Creates a panel listing players either for sale or purchase based on the given parameters.
     * 
     * @param title The title of the panel, e.g., "Joueurs à Vendre" or "Joueurs à Acheter".
     * @param players List of players to be displayed in the panel.
     * @param isSelling Boolean flag indicating whether the panel is for selling players (true) or buying players (false).
     * @param joueurService The service used to interact with player data.
     * @param equipeService The service used to get team information, such as team names by ID.
     * @return A JPanel configured with a list of players and appropriate actions (sell or buy).
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

        // Check if the list of players is empty and display a message if so
        if (players.isEmpty()) {
            centralPanel.add(new JLabel("Aucun joueur n'est actuellement disponible."), gbc);
        } else {
            // Create a PlayerPanel for each player in the list based on the team and action required
            for (Joueur joueur : players) {
                if ((isSelling && joueur.getEquipe_id() == equipeService.getMonEquipe()) || (!isSelling && joueur.getEquipe_id() != equipeService.getMonEquipe())) {
                    ActionButton button = isSelling ? new TransferSellButton() : new TransferBuyButton(joueur.getId(), joueurService, equipeService);
                    centralPanel.add(createPlayerPanel(joueur, button, joueurService, equipeService), gbc);
                }
            }
        }

        // Add a spacer component at the bottom to push all content to the top
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
     * Helper method to create an individual PlayerPanel.
     * 
     * @param joueur The player for whom the panel is created.
     * @param actionButton The action button associated with the player.
     * @param joueurService Service for managing player data.
     * @param equipeService Service for managing team data.
     * @return A configured PlayerPanel for the given player.
     */
    private static JPanel createPlayerPanel(Joueur joueur, ActionButton actionButton, JoueurService joueurService, EquipeService equipeService) {
        return new PlayerPanel(joueur, actionButton, joueurService, equipeService);
    }
}
