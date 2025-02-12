package view.playerTransferView;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import service.interfaces.PlayerDataListener;

/**
 * Classe représentant le panneau du marché des joueurs, permettant d'afficher soit les joueurs à vendre soit à acheter.
 */
public class MarketPlayerPanel extends JPanel implements PlayerDataListener {
    
    private JoueurService joueurService; // Service gérant les informations des joueurs
    private EquipeService equipeService; // Service fournissant les informations sur les équipes
    private boolean isSelling; // Indicateur si le panneau est pour vendre (true) ou acheter (false) des joueurs

    /**
     * Constructeur qui initialise le panneau avec les services nécessaires et détermine si le panneau est pour vendre ou acheter.
     * @param joueurService Le service pour gérer les données des joueurs
     * @param equipeService Le service pour gérer les données des équipes
     * @param isSelling Booléen indiquant si le panneau est pour la vente (true) ou l'achat (false) de joueurs
     */
    public MarketPlayerPanel(JoueurService joueurService, EquipeService equipeService, boolean isSelling) {
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        this.isSelling = isSelling;
        setupUI();
        joueurService.addDataListener(this); // S'abonne aux modifications des données des joueurs
    }

    /**
     * Configure l'interface utilisateur du panneau.
     */
    private void setupUI() {
        setLayout(new BorderLayout());
        updatePanel(); // Met à jour le contenu du panneau
    }

    /**
     * Méthode appelée lorsque les données des joueurs changent, ce qui déclenche la mise à jour du panneau.
     */
    @Override
    public void onDataChanged() {
        updatePanel(); // Met à jour le panneau avec les données des joueurs actuelles
    }

    /**
     * Met à jour le panneau en rechargeant les joueurs à vendre ou à acheter en fonction de l'indicateur isSelling.
     */
    private void updatePanel() {
        removeAll(); // Supprime tous les composants actuels
        List<Joueur> players = joueurService.getPlayerToSell(); // Obtient la liste des joueurs à vendre
        add(PlayerPanelUtility.createPlayerListingPanel(isSelling ? "Joueurs à Vendre" : "Joueurs à Acheter", 
                                                        players, isSelling, joueurService, equipeService),
            BorderLayout.CENTER); // Ajoute un nouveau panneau de listing des joueurs
        revalidate(); // Revalide le layout après l'ajout des composants
        repaint(); // Redessine le panneau après les changements
    }
}
