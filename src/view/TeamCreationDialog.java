package view;

import javax.swing.*;
import controller.Equipe;
import service.interfaces.EquipeService;
import view.composent.ConfirmButton;

import java.awt.*;
import java.awt.event.*;

/**
 * Dialog pour la création d'une nouvelle équipe dans l'application.
 * Permet aux utilisateurs de saisir le nom et le solde initial d'une équipe avant de l'enregistrer.
 */
public class TeamCreationDialog extends JDialog {
    private JTextField nameField; // Champ de texte pour le nom de l'équipe
    private JSpinner balanceSpinner; // Spinner pour sélectionner le solde initial
    private JButton saveButton; // Bouton pour enregistrer la nouvelle équipe
    private JButton cancelButton; // Bouton pour annuler la création
    private EquipeService equipeService; // Service pour interagir avec les données des équipes

    /**
     * Constructeur pour initialiser le dialogue de création d'équipe.
     * @param parent Le JFrame parent de ce dialogue.
     * @param equipeService Le service d'équipe utilisé pour ajouter de nouvelles équipes.
     */
    public TeamCreationDialog(JFrame parent, EquipeService equipeService) {
        super(parent, "Créer Nouvelle Équipe", true);
        this.equipeService = equipeService;
        
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(0, 1)); // Utilise un GridLayout pour organiser les composants verticalement.

        // Panel pour le nom de l'équipe
        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.add(new JLabel("Nom de l'équipe:"));
        nameField = new JTextField(15);
        namePanel.add(nameField);
        add(namePanel);

        // Panel pour le solde initial
        JPanel balancePanel = new JPanel(new FlowLayout());
        balancePanel.add(new JLabel("Solde initial (M €):"));
        balanceSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 1000, 1));
        balancePanel.add(balanceSpinner);
        add(balancePanel);

        // Panel pour les boutons d'action
        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveButton = new ConfirmButton("Enregistrer");
        saveButton.addActionListener(this::saveTeam); // Ajoute un écouteur pour le bouton d'enregistrement
        buttonPanel.add(saveButton);
        
        cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(e -> dispose()); // Ajoute un écouteur pour fermer le dialogue
        buttonPanel.add(cancelButton);
        
        add(buttonPanel);
    }

    /**
     * Gère l'enregistrement de la nouvelle équipe en utilisant les données saisies par l'utilisateur.
     * @param e L'événement déclenché par le bouton d'enregistrement.
     */
    private void saveTeam(ActionEvent e) {
        String teamName = nameField.getText();
        int balance = (Integer) balanceSpinner.getValue();
        if (!teamName.isEmpty()) {
            Equipe newTeam = new Equipe(teamName, balance);
            equipeService.addTeam(newTeam); // Ajoute l'équipe via le service
            JOptionPane.showMessageDialog(this, "Équipe ajoutée avec succès!");
            dispose(); // Ferme le dialogue après l'ajout
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom d'équipe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
