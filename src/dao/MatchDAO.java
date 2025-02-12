package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import config.ConfigReader;
import controller.Match;

/**
 * Classe d'accès aux données contenues dans la table Match.
 * @version 1.1
 */
public class MatchDAO {

    /** URL de connexion à la base de données. */
    private final String URL;

    /** Identifiant de connexion à la base de données. */
    private final String LOGIN;

    /** Mot de passe de connexion à la base de données. */
    private final String PASS;

    /**
     * Constructeur de la classe MatchDAO.
     */
    public MatchDAO() {
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
     * Ajoute un match dans la table Match.
     * @param nouvMatch Nouveau match.
     * @return Nombre de lignes ajoutées.
     */
    public int ajouter(Match nouvMatch) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO match (id, date, adversaire_id, equipe_id) VALUES (?, ?, ?, ?)");
            ps.setInt(1, nouvMatch.getId());
            ps.setDate(2, java.sql.Date.valueOf(nouvMatch.getDate()));
            ps.setInt(3, nouvMatch.getAdversaire());
            ps.setInt(4, nouvMatch.getEquipe());
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
     * Récupère tous les matchs stockés dans la table Match.
     * @return Liste des matchs.
     */
    public List<Match> getAllMatch() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Match> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id")));
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
     * Récupère un match par son identifiant.
     * @param id Identifiant du match.
     * @return Match correspondant ou null si inexistant.
     */
    public Match getMatch(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id"));
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception t) {}
            try { if (ps != null) ps.close(); } catch (Exception t) {}
            try { if (con != null) con.close(); } catch (Exception t) {}
        }
        return retour;
    }

    /**
     * Récupère un match en fonction de l'équipe et de l'adversaire.
     * @param equipe Identifiant de l'équipe.
     * @param adversaire Identifiant de l'adversaire.
     * @return Match correspondant ou null si inexistant.
     */
    public Match getMatch(int equipe, int adversaire) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match WHERE equipe_id = ? AND adversaire_id = ?");
            ps.setInt(1, equipe);
            ps.setInt(2, adversaire);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id"));
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception t) {}
            try { if (ps != null) ps.close(); } catch (Exception t) {}
            try { if (con != null) con.close(); } catch (Exception t) {}
        }
        return retour;
    }
    
    /**
     * Récupère un match en fonction de la date.
     * @param date Date du match.
     * @return Le match correspondant ou null s'il n'existe pas.
     */
    public Match getMatchByDate(Date date) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match retour = null;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match WHERE date = ?");
            ps.setDate(1, date);

            // Exécution de la requête
            rs = ps.executeQuery();

            // Vérification et récupération des données
            if (rs.next()) {
                retour = new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(),
                        rs.getInt("adversaire_id"), rs.getInt("equipe_id"));
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // Fermeture des ressources
            try { if (rs != null) rs.close(); } catch (Exception t) {}
            try { if (ps != null) ps.close(); } catch (Exception t) {}
            try { if (con != null) con.close(); } catch (Exception t) {}
        }
        return retour;
    }


    /**
     * Supprime un match de la table Match.
     * @param id Identifiant du match.
     */
    public void deleteMatch(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "DELETE FROM match WHERE id = ?";
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
