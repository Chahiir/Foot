package view.teamManagmentView;

import javax.swing.*;

import controller.Joueur;
import service.interfaces.EquipeService;

import java.awt.*;
import java.awt.event.*;

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

    public PlayerEditDialog(Frame parent, Joueur joueur, EquipeService equipeService) {
        super(parent, "Édition de Joueur", true);
        this.joueur = joueur;
        this.equipeService = equipeService;

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
        joueur.setPrenom(prenomField.getText());
        joueur.setPosition(positionField.getText());
        try{
            joueur.setAge(Integer.parseInt(ageField.getText()));
        
        joueur.setPrix(Integer.parseInt(prixField.getText()));
        joueur.setEquipe_id(equipeService.getMonEquipe());
        dispose();
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "L'age et le prix doivent être des entiers", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Joueur getJoueur() {
        return joueur;
    }
}

