package dao;

import java.sql.*;
import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Resultat;
/**
 * Classe d'accès aux données contenues dans la table Equipe.
 * Permet d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur la table Equipe.
 * @version 1.1
 */
public class ResultatDAO {

    /**
     * Paramètres de connexion à la base de données MySQL.
     * URL, LOGIN et PASS sont des constantes.
     */
	private final String URL;
	private final String LOGIN;
	private final String PASS ;

    /**
     * Constructeur de la classe.
     * Charge le pilote de base de données MySQL.
     */
    public ResultatDAO() {
    	Map<String, String> config = ConfigReader.readConfig("./config.txt");
        this.URL = config.get("url");
        this.LOGIN = config.get("username");
        this.PASS = config.get("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote JDBC MySQL
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote de BDD. Assurez-vous d'avoir ajouté le fichier .jar du pilote MySQL au projet.");
        }
    }

    /**
     * Permet d'ajouter une équipe dans la table equipe.
     * Le mode est auto-commit par défaut : chaque insertion est validée immédiatement.
     * @param nouvEquipe L'équipe à ajouter.
     * @return Le nombre de lignes ajoutées dans la table.
     */
    public int ajouter(Resultat nouvResultat) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("INSERT INTO resultat (id,score_equipe, score_adversaire, match_id) VALUES (?, ?, ?, ?)");
            ps.setInt(1, nouvResultat.getId());
            ps.setInt(2, nouvResultat.getScore_equipe());
            ps.setInt(3, nouvResultat.getScore_adversaire());
            ps.setInt(4, nouvResultat.getMatch_id());


            // Exécution de la requête
            retour = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {}
            try {
                if (con != null) con.close();
            } catch (Exception e) {}
        }
        return retour;
    }


    /**
     * Permet de récupérer toutes les équipes stockées dans la table equipe.
     * @return Une ArrayList contenant toutes les équipes.
     */
    public List<Resultat> getAllResultat() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resultat> retour = new ArrayList<>();

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("SELECT * FROM resultat");

            // Exécution de la requête
            rs = ps.executeQuery();
            // Parcours des résultats
            while (rs.next()) {
                retour.add(new Resultat(rs.getInt("id"), rs.getInt("score_equipe"), rs.getInt("score_adversaire"), rs.getInt("match_id")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {}
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {}
            try {
                if (con != null) con.close();
            } catch (Exception e) {}
        }
        return retour;
    }
    
    
    public Resultat getResultat(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Resultat retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM resultat WHERE id = ?");
            ps.setInt(1, id);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Resultat(rs.getInt("id"), rs.getInt("score_equipe"), rs.getInt("score_adversaire"), rs.getInt("match_id"));

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // Fermeture du ResultSet, du PreparedStatement et de la Connection
            try {
                if (rs != null) rs.close();
            } catch (Exception t) {}
            try {
                if (ps != null) ps.close();
            } catch (Exception t) {}
            try {
                if (con != null) con.close();
            } catch (Exception t) {}
        }
        return retour;
    }

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
            // Fermeture des ressources
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
    
    
   
}