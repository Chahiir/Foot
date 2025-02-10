package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.Joueur;
import service.interfaces.PlayerService;

public class mockPlayerServiceImpl implements PlayerService{

    ArrayList<Joueur> list;

    public mockPlayerServiceImpl(){
        list = new ArrayList<>();
        list.add(new Joueur("Dupont", "Milieu",21,5));
        list.add(new Joueur("Martin", "Attaquant",20,2));
        list.add(new Joueur("Leroy", "Défenseur",23,1));
        list.add(new Joueur("Bernard", "Gardien",18,5));
    }

    @Override
    public List<Joueur> getAllPlayers() {
        return list;
    }

    @Override
    public Joueur getPlayerById(int id) {
        return list.get(id);
    }

    @Override
    public List<Joueur> getPlayersByTextFilter(String querryString) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersByTextFilter'");
    }

    @Override
    public void updatePlayer(Joueur player) {
        list.set(player.getId(), player);
    }

    @Override
    public void sellPlayerById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sellPlayerById'");
    }
    
}
