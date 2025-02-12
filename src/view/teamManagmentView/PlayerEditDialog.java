package view.teamManagmentView;

import javax.swing.*;

import controller.Joueur;
import service.interfaces.EquipeService;

import java.awt.*;
import java.awt.event.*;

/**
 * PlayerEditDialog est une boîte de dialogue modale permettant d'éditer les informations d'un joueur 
 * telles que son nom, sa position, son âge et son prix. Les modifications sont appliquées à l'instance 
 * du joueur après confirmation.
 */
public class PlayerEditDialog extends JDialog {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField positionField;
    private JTextField ageField;
    private JTextField prixField;
    private JButton saveButton;
    private JButton cancelButton;

    private Joueur joueur;
    private EquipeService equipeService;

    /**
     * Constructeur de la boîte de dialogue d'édition de joueur.
     * @param parent Fenêtre parente de cette boîte de dialogue.
     * @param joueur Le joueur dont les informations doivent être modifiées.
     * @param equipeService Le service permettant la gestion des équipes.
     */
    public PlayerEditDialog(Frame parent, Joueur joueur, EquipeService equipeService) {
        super(parent, "Édition de Joueur", true);
        this.joueur = joueur;
        this.equipeService = equipeService;

        setLayout(new GridLayout(0, 2, 10, 10)); // Utilisation d'un GridLayout pour un affichage clair

        // Champs de saisie
        add(new JLabel("Nom:"));
        nomField = new JTextField(joueur.getNom());
        add(nomField);

        add(new JLabel("Prénom:"));
        prenomField = new JTextField(joueur.getPrenom());
        add(prenomField);

        add(new JLabel("Position:"));
        positionField = new JTextField(joueur.getPosition());
        add(positionField);

        add(new JLabel("Âge:"));
        ageField = new JTextField(String.valueOf(joueur.getAge()));
        add(ageField);

        add(new JLabel("Prix (M €):"));
        prixField = new JTextField(String.valueOf(joueur.getPrix()));
        add(prixField);

        // Bouton Enregistrer
        saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePlayer();
            }
        });
        add(saveButton);

        // Bouton Annuler
        cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la boîte de dialogue sans enregistrer
            }
        });
        add(cancelButton);

        // Propriétés de la boîte de dialogue
        setSize(400, 300); // Ajustement pour une meilleure lisibilité
        setLocationRelativeTo(parent); // Centre la boîte de dialogue par rapport à la fenêtre parente
    }

    /**
     * Enregistre les modifications apportées aux attributs du joueur 
     * et l'associe à l'équipe courante.
     */
    private void savePlayer() {
        try {
            joueur.setNom(nomField.getText());
            joueur.setPrenom(prenomField.getText());
            joueur.setPosition(positionField.getText());
            joueur.setAge(Integer.parseInt(ageField.getText()));
            joueur.setPrix(Integer.parseInt(prixField.getText()));
            joueur.setEquipe_id(equipeService.getMonEquipe()); // Affecte le joueur à l'équipe courante
            dispose(); // Ferme la boîte de dialogue après enregistrement
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "L'âge et le prix doivent être des nombres entiers", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retourne l'instance du joueur modifié après fermeture de la boîte de dialogue.
     * @return L'instance mise à jour du joueur.
     */
    public Joueur getJoueur() {
        return joueur;
    }
}
