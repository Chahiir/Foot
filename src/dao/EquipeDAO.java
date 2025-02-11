package dao;

import java.sql.*;
import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Equipe;
/**
 * Classe d'accès aux données contenues dans la table Equipe.
 * Permet d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur la table Equipe.
 * @version 1.1
 */
public class EquipeDAO {

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
    public EquipeDAO() {
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
    public int ajouter(Equipe nouvEquipe) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("INSERT INTO equipe (id, nom, solde) VALUES (?, ?, ?)");
            ps.setInt(1, nouvEquipe.getId());
            ps.setString(2, nouvEquipe.getNom());
            ps.setInt(3, nouvEquipe.getSolde());

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
     * Permet de récupérer une équipe à partir de son identifiant.
     * @param id L'identifiant de l'équipe à récupérer.
     * @return L'équipe correspondante, ou null si aucune équipe ne correspond à cet identifiant.
     */
    public Equipe getEquipe(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equipe retour = null;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("SELECT * FROM equipe WHERE id = ?");
            ps.setInt(1, id);

            // Exécution de la requête
            rs = ps.executeQuery();
            // Récupération du résultat
            if (rs.next()) {
                retour = new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("solde"));
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
    
    
    public int updateEquipe(Equipe equipe) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connexion à la base de données
        try {
            // Tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);

            // Préparation de l'instruction SQL pour la mise à jour
            ps = con.prepareStatement("UPDATE equipe SET nom = ?, solde = ? WHERE id = ?");
            
            // Attribution des valeurs
            ps.setString(1, equipe.getNom());
            ps.setInt(2, equipe.getSolde());
            

            // Exécution de la requête
            retour = ps.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // Fermeture des ressources
            try {
                if (ps != null) ps.close();
            } catch (Exception ignored) {}
            try {
                if (con != null) con.close();
            } catch (Exception ignored) {}
        }
        return retour;
    }


    /**
     * Permet de récupérer toutes les équipes stockées dans la table equipe.
     * @return Une ArrayList contenant toutes les équipes.
     */
    public List<Equipe> getListeEquipes() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Equipe> retour = new ArrayList<>();

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("SELECT * FROM equipe");

            // Exécution de la requête
            rs = ps.executeQuery();
            // Parcours des résultats
            while (rs.next()) {
                retour.add(new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("solde")));
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

    public void deleteEquipe(int id) {
    	Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "DELETE FROM equipe WHERE id = ?";
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
    
    
    /**
     * Méthode principale pour tester la classe EquipeDAO.
     * @param args Arguments de la ligne de commande (non utilisés ici).
     * @throws SQLException En cas d'erreur SQL.
     */
    public static void main(String[] args) throws SQLException {
        EquipeDAO equipeDAO = new EquipeDAO();

        // Test de la méthode ajouter
        Equipe equipe1 = new Equipe("Wydad AC", 150);
        int lignesAjoutees = equipeDAO.ajouter(equipe1);
        System.out.println(lignesAjoutees + " lignes ajoutées");

        // Test de la méthode getEquipe
        Equipe equipe2 = equipeDAO.getEquipe(1);
        System.out.println(equipe2);

        // Test de la méthode getListeEquipes
        List<Equipe> listeEquipes = equipeDAO.getListeEquipes();
        for (Equipe equipe : listeEquipes) {
            System.out.println(equipe.toString());
        }
    }
}