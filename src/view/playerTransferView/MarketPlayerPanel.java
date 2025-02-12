package view.playerTransferView;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import controller.Joueur;
import service.interfaces.EquipeService;
import service.interfaces.JoueurService;
import service.interfaces.PlayerDataListener;

public class MarketPlayerPanel extends JPanel implements PlayerDataListener{
    
    private JoueurService joueurService;
    private EquipeService equipeService;
    private boolean isSelling;

    public MarketPlayerPanel(JoueurService joueurService, EquipeService equipeService, boolean isSelling) {
        this.joueurService = joueurService;
        this.equipeService = equipeService;
        this.isSelling = isSelling;
        setupUI();
        joueurService.addDataListener(this);
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        updatePanel();
    }

    @Override
    public void onDataChanged() {
        updatePanel();
    }

    private void updatePanel() {
        removeAll();
        List<Joueur> players = joueurService.getPlayerToSell();
        add(PlayerPanelUtility.createPlayerListingPanel(isSelling ? "Joueurs à Vendre" : "Joueurs à Acheter", 
                                                        players, isSelling, joueurService, equipeService));
        revalidate();
        repaint();
    }
}

