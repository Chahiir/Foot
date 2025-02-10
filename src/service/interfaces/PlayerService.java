package service.interfaces;

import java.util.List;

import dao.Joueur;

public interface PlayerService {
    public List<Joueur> getAllPlayers();

    public Joueur getPlayerById(int id);

    public List<Joueur> getPlayersByTextFilter(String querryString);

    public void updatePlayer(Joueur player);

    public void sellPlayerById(int id);
}
