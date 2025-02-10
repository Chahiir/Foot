package view;

import javax.swing.*;

import controller.Joueur;

import java.awt.*;
import java.awt.event.*;



public class PlayerEditDialog extends JDialog {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField positionField;
    private JTextField ageField;
    private JTextField prixField;
    private JTextField equipeIdField;
    private JButton saveButton;
    private JButton cancelButton;

    private Joueur joueur;

    public PlayerEditDialog(Frame parent, Joueur joueur) {
        super(parent, "Édition de Joueur", true);
        this.joueur = joueur;

        setLayout(new GridLayout(0, 2, 10, 10));
        add(new JLabel("Nom:"));
        nomField = new JTextField(joueur.getNom());
        add(nomField);

        setLayout(new GridLayout(0, 2, 10, 10));
        add(new JLabel("Prenom:"));
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

        add(new JLabel("ID Équipe:"));
        equipeIdField = new JTextField(String.valueOf(joueur.getEquipe_id()));
        add(equipeIdField);

        saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePlayer();
            }
        });
        add(saveButton);

        cancelButton = new JButton("Annuler");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton);

        setSize(300, 200);
        setLocationRelativeTo(parent);
    }

    private void savePlayer() {
        joueur.setNom(nomField.getText());
        joueur.setPosition(positionField.getText());
        joueur.setAge(Integer.parseInt(ageField.getText()));
        joueur.setPrix(Integer.parseInt(prixField.getText()));
        joueur.setEquipe_id(Integer.parseInt(equipeIdField.getText()));
        dispose();
    }

    public Joueur getJoueur() {
        return joueur;
    }
}

