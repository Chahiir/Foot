package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import service.interfaces.EquipeService;
import view.composent.ConfirmButton;

public class TeamSelectionDialog extends JDialog {
    private JComboBox<String> teamComboBox;
    private HashMap<String /*Names*/, Integer /*id*/> teams;
    private ConfirmButton confirmButton;
    private EquipeService equipeService;

    public TeamSelectionDialog(JFrame parent, EquipeService equipeService) {
        super(parent, "Sélection de l'Équipe", true);
        this.equipeService = equipeService;
        this.teams = new HashMap<>();
        equipeService.getAllEquipes().forEach((equipe -> teams.put(equipe.getNom(), equipe.getId())));
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new FlowLayout());

        add(new JLabel("Choisissez une équipe:"));
        String [] teamNames = teams.keySet().toArray(new String[0]);
        teamComboBox = new JComboBox<>(teamNames);
        add(teamComboBox);

        confirmButton = new ConfirmButton("Confirmer");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfirm();
            }
        });
        add(confirmButton);
    }

    private void onConfirm() {
        String selectedTeam = (String) teamComboBox.getSelectedItem();
        equipeService.setMonEquipe(teams.get(selectedTeam)); // Configurez votre service d'équipe ici
        dispose(); // Ferme la boîte de dialogue
    }
}

