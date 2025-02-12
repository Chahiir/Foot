package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import config.ConfigReader;
import controller.Resultat;

/**
 * Classe d'accès aux données contenues dans la table Resultat.
 * @version 1.1
 */
public class ResultatDAO {

    /** URL de connexion à la base de données. */
    private final String URL;

    /** Identifiant de connexion à la base de données. */
    private final String LOGIN;

    /** Mot de passe de connexion à la base de données. */
    private final String PASS;

    /**
     * Constructeur de la classe ResultatDAO.
     */
    public ResultatDAO() {
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
     * Ajoute un résultat dans la table Resultat.
     * @param nouvResultat Nouveau résultat.
     * @return Nombre de lignes ajoutées.
     */
    public int ajouter(Resultat nouvResultat) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO resultat (id, score_equipe, score_adversaire, match_id) VALUES (?, ?, ?, ?)");
            ps.setInt(1, nouvResultat.getId());
            ps.setInt(2, nouvResultat.getScore_equipe());
            ps.setInt(3, nouvResultat.getScore_adversaire());
            ps.setInt(4, nouvResultat.getMatch_id());
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
     * Récupère tous les résultats stockés dans la table Resultat.
     * @return Liste des résultats.
     */
    public List<Resultat> getAllResultat() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resultat> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM resultat");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Resultat(rs.getInt("id"), rs.getInt("score_equipe"), rs.getInt("score_adversaire"), rs.getInt("match_id")));
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
     * Récupère un résultat par son identifiant.
     * @param id Identifiant du résultat.
     * @return Résultat correspondant ou null si inexistant.
     */
    public Resultat getResultat(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Resultat retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM resultat WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Resultat(rs.getInt("id"), rs.getInt("score_equipe"), rs.getInt("score_adversaire"), rs.getInt("match_id"));
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
     * Génère un score aléatoire entre 0 et 5.
     * @return Score généré.
     */
    public static int generateScore() {
        return (int) (Math.random() * 6);
    }

    /**
     * Supprime un résultat de la table Resultat.
     * @param id Identifiant du résultat.
     */
    public void deleteResultat(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "DELETE FROM resultat WHERE id = ?";
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
