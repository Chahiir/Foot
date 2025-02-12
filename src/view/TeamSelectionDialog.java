package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import service.interfaces.EquipeService;
import view.composent.ConfirmButton;

/**
 * TeamSelectionDialog est une boîte de dialogue qui permet aux utilisateurs de sélectionner ou d'ajouter une équipe.
 * Elle sert à configurer l'équipe actuellement gérée dans l'application via EquipeService.
 */
public class TeamSelectionDialog extends JDialog {
    private JComboBox<String> teamComboBox; // ComboBox pour la sélection d'équipe.
    private HashMap<String /*Names*/, Integer /*id*/> teams; // Map pour stocker les équipes et leurs IDs.
    private ConfirmButton confirmButton; // Bouton pour confirmer la sélection.
    private EquipeService equipeService; // Service pour interagir avec les données des équipes.

    /**
     * Constructeur pour créer une boîte de dialogue de sélection d'équipe.
     * @param parent Le JFrame parent de cette boîte de dialogue.
     * @param equipeService Le service d'équipe utilisé pour obtenir les données des équipes.
     */
    public TeamSelectionDialog(JFrame parent, EquipeService equipeService) {
        super(parent, "Sélection de l'Équipe", true);
        this.equipeService = equipeService;
        this.teams = new HashMap<>();
        equipeService.getAllEquipes().forEach(equipe -> teams.put(equipe.getNom(), equipe.getId()));
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new FlowLayout());

        add(new JLabel("Choisissez une équipe:"));
        String[] teamNames = teams.keySet().toArray(new String[0]);
        teamComboBox = new JComboBox<>(teamNames);
        add(teamComboBox);

        // Bouton pour ajouter une nouvelle équipe et fermer la boîte de dialogue
        confirmButton = new ConfirmButton("Confirmer");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirm();
            }
        });
        add(confirmButton);

        // Bouton pour ajouter une nouvelle équipe.
        JButton addTeamButton = new ConfirmButton("Ajouter une nouvelle équipe");
        addTeamButton.addActionListener(e -> {
            // Crée et affiche le dialogue pour la création d'une nouvelle équipe.
            TeamCreationDialog newTeamDialog = new TeamCreationDialog(parent, equipeService);
            newTeamDialog.setVisible(true);
            reloadData(); // Recharge les données des équipes après l'ajout.
        });
        add(addTeamButton);
    }

    /**
     * Gère l'action de confirmation de la sélection d'équipe.
     */
    private void onConfirm() {
        String selectedTeam = (String) teamComboBox.getSelectedItem();
        equipeService.setMonEquipe(teams.get(selectedTeam)); // Mise à jour de l'équipe actuelle dans le service.
        dispose(); // Ferme la boîte de dialogue.
    }

    /**
     * Recharge les données des équipes dans le JComboBox après un ajout d'équipe.
     */
    private void reloadData(){
        teamComboBox.removeAllItems(); // Efface les éléments existants.
        teams.clear(); // Vide la map actuelle.

        // Recharge les équipes du service et les ajoute dans la ComboBox et la map.
        equipeService.getAllEquipes().forEach(equipe -> {
            teams.put(equipe.getNom(), equipe.getId());
            teamComboBox.addItem(equipe.getNom());
        });
    }
}
