package dao;

import java.sql.*;
import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import controller.Composition;

/**
 * Classe d'accès aux données contenues dans la table Composition.
 * @version 1.1
 */
public class CompositionDAO {

    /** URL de connexion à la base de données. */
    private final String URL;

    /** Identifiant de connexion à la base de données. */
    private final String LOGIN;

    /** Mot de passe de connexion à la base de données. */
    private final String PASS;

    /**
     * Constructeur de la classe CompositionDAO.
     */
    public CompositionDAO() {
        Map<String, String> config = ConfigReader.readConfig("./config.txt");
        this.URL = config.get("url");
        this.LOGIN = config.get("username");
        this.PASS = config.get("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote de BDD.");
        }
    }

    /**
     * Ajoute une composition dans la table Composition.
     * @param nouvComp Nouvelle composition.
     * @return Nombre de lignes ajoutées.
     */
    public int ajouter(Composition nouvComp) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO composition (id, joueur_id, match_id) VALUES (?, ?, ?)");
            ps.setInt(1, nouvComp.getId());
            ps.setInt(2, nouvComp.getJoueur_id());
            ps.setInt(3, nouvComp.getMatch_id());
            retour = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Récupère la composition d'un match donné.
     * @param id Identifiant du match.
     * @return Liste des compositions associées au match.
     */
    public List<Composition> getCompositionMatch(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Composition> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM composition WHERE match_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Composition(rs.getInt("id"), rs.getInt("joueur_id"), rs.getInt("match_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Insère les joueurs d'une équipe dans la composition d'un match.
     * @param con Connexion à la base de données.
     * @param equipeId Identifiant de l'équipe.
     * @param matchId Identifiant du match.
     * @throws SQLException Exception SQL.
     */
    public static void insertPlayersIntoComposition(Connection con, int equipeId, int matchId) throws SQLException {
        String selectPlayersSql = "SELECT id FROM joueur WHERE equipe_id = ? LIMIT 11";
        String insertCompositionSql = "INSERT INTO composition (joueur_id, match_id) VALUES (?, ?)";

        try (PreparedStatement selectPs = con.prepareStatement(selectPlayersSql);
             PreparedStatement insertPs = con.prepareStatement(insertCompositionSql)) {
            selectPs.setInt(1, equipeId);
            try (ResultSet rs = selectPs.executeQuery()) {
                while (rs.next()) {
                    int joueurId = rs.getInt("id");
                    insertPs.setInt(1, joueurId);
                    insertPs.setInt(2, matchId);
                    insertPs.executeUpdate();
                }
            }
        }
    }

    /**
     * Supprime une composition de la table Composition.
     * @param id Identifiant de la composition.
     */
    public void deleteComposition(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "DELETE FROM composition WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
}
