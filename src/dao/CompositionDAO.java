package dao;

import java.sql.*;
import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Composition;
/**
 * Classe d'accès aux données contenues dans la table Equipe.
 * Permet d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur la table Equipe.
 * @version 1.1
 */
public class CompositionDAO {

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
    public CompositionDAO() {
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
    public int ajouter(Composition nouvComp) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("INSERT INTO composition (id,joueur_id, match_id) VALUES (?, ?, ?)");
            ps.setInt(1, nouvComp.getId());
            ps.setInt(2, nouvComp.getJoueur_id());
            ps.setInt(3, nouvComp.getMatch_id());


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
    public List<Composition> getCompositionMatch(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Composition> retour = new ArrayList<>();

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("SELECT * FROM composition WHERE match_id = ?");
            ps.setInt(1, id);
            
            // Exécution de la requête
            rs = ps.executeQuery();
            // Parcours des résultats
            while (rs.next()) {
                retour.add(new Composition(rs.getInt("id"), rs.getInt("joueur_id"), rs.getInt("match_id")));
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
            // Fermeture des ressources
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
    
    
   
}