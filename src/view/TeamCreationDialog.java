package view;

import javax.swing.*;

import controller.Equipe;
import service.interfaces.EquipeService;
import view.composent.ConfirmButton;

import java.awt.*;
import java.awt.event.*;

public class TeamCreationDialog extends JDialog {
    private JTextField nameField;
    private JSpinner balanceSpinner;
    private JButton saveButton;
    private JButton cancelButton;
    private EquipeService equipeService;

    public TeamCreationDialog(JFrame parent, EquipeService equipeService) {
        super(parent, "Créer Nouvelle Équipe", true);
        this.equipeService = equipeService;
        
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(0, 1));

        // Nom de l'équipe
        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.add(new JLabel("Nom de l'équipe:"));
        nameField = new JTextField(15);
        namePanel.add(nameField);
        add(namePanel);

        // Solde de l'équipe
        JPanel balancePanel = new JPanel(new FlowLayout());
        balancePanel.add(new JLabel("Solde initial (M €):"));
        balanceSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 1000, 1));
        balancePanel.add(balanceSpinner);
        add(balancePanel);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveButton = new ConfirmButton("Enregistrer");
        saveButton.addActionListener(this::saveTeam);
        buttonPanel.add(saveButton);
        
        cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
        
        add(buttonPanel);
    }

    private void saveTeam(ActionEvent e) {
        String teamName = nameField.getText();
        int balance = (Integer) balanceSpinner.getValue();
        if (!teamName.isEmpty()) {
            Equipe newTeam = new Equipe(teamName, balance);
            equipeService.addTeam(newTeam);
            JOptionPane.showMessageDialog(this, "Équipe ajoutée avec succès!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom d'équipe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
