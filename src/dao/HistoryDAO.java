package dao;

import java.sql.*;
import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.History;
/**
 * Classe d'accès aux données contenues dans la table Equipe.
 * Permet d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur la table Equipe.
 * @version 1.1
 */
public class HistoryDAO {

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
    public HistoryDAO() {
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
    public int ajouter(History nouvHistory) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("INSERT INTO history (id, oldEquipe_id, newEquipe_id, joueur_id,prix, date) VALUES (?, ?, ?,? ,?, ?)");
            ps.setInt(1, nouvHistory.getId());
            ps.setInt(2, nouvHistory.getOldEquipe_id());
            ps.setInt(3, nouvHistory.getNewEquipe_id());
            ps.setInt(4, nouvHistory.getJoueur_id());
            ps.setInt(5, nouvHistory.getPrix());
            ps.setDate(6, java.sql.Date.valueOf(nouvHistory.getDate()));


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
    public List<History> getHistory() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<History> retour = new ArrayList<>();

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("SELECT * FROM history");

            // Exécution de la requête
            rs = ps.executeQuery();
            // Parcours des résultats
            while (rs.next()) {
                retour.add(new History(rs.getInt("id"), rs.getInt("oldEquipe_id"), rs.getInt("newEquipe_id"), rs.getInt("joueur_id"),rs.getInt("prix"),rs.getDate("date").toLocalDate()));
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

    public void deleteTransfer(int id) {
    	Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "DELETE FROM history WHERE id = ?";
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