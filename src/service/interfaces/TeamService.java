package service.interfaces;

import dao.Equipe;

public interface TeamService {
    public Equipe getEquipeById(int id);

    public void addPlayerToTeam(int playerId, int teamId);

    public void removePlayerFromTeam(int playerId, int teamId);

    public void deleteTeamById( int teamId);
}
