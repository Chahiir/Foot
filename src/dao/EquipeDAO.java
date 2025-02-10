package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table Equipe
 * @version 1.1
 * */
public class EquipeDAO {

    /**
     * Paramètres de connexion à la base de données MySQL
     * URL, LOGIN et PASS sont des constantes
     */
    final static String URL = "jdbc:mysql://localhost:3306/footmanager"; // Replace with your database name
    final static String LOGIN = "root"; // Replace with your MySQL username
    final static String PASS = "root"; // Replace with your MySQL password

    /**
     * Constructeur de la classe
     */
    public EquipeDAO() {
        // Chargement du pilote de bases de données
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC driver
        } catch (ClassNotFoundException e2) {
            System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Permet d'ajouter un article dans la table article
     * La référence de l'article est produite automatiquement par la base de données en utilisant une séquence
     * Le mode est auto-commit par défaut : chaque insertion est validée
     * @param nouvArticle l'article à ajouter
     * @return le nombre de lignes ajoutées dans la table
     */
    public int ajouter(Equipe nouvEquipe) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connexion à la base de données
        try {
            // Tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
            // Les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
            ps = con.prepareStatement("INSERT INTO equipe (id, nom, solde) VALUES (?, ?, ?)");
            ps.setInt(1, nouvEquipe.getId());
            ps.setString(2, nouvEquipe.getNom());
            ps.setInt(3, nouvEquipe.getSolde());

            // Exécution de la requête
            retour = ps.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // Fermeture du preparedStatement et de la connexion
            try {
                if (ps != null) ps.close();
            } catch (Exception t) {}
            try {
                if (con != null) con.close();
            } catch (Exception t) {}
        }
        return retour;
    }

    /**
     * Permet de récupérer un article à partir de sa référence
     * @param reference la référence de l'article à récupérer
     * @return l'article
     * @return null si aucun article ne correspond à cette référence
     */
    public Equipe getEquipe(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equipe retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM equipe WHERE id = ?");
            ps.setInt(1, id);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("solde"));

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

    /**
     * Permet de récupérer tous les articles stockés dans la table article
     * @return une ArrayList d'Articles
     */
    public List<Equipe> getListeEquipes() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Equipe> retour = new ArrayList<Equipe>();

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM equipe");

            // On exécute la requête
            rs = ps.executeQuery();
            // On parcourt les lignes du résultat
            while (rs.next())
                retour.add(new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("solde")));

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // Fermeture du rs, du preparedStatement et de la connexion
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

    // Main permettant de tester la classe
    public static void main(String[] args) throws SQLException {
        EquipeDAO equipeDAO = new EquipeDAO();
        // Test de la méthode ajouter
        Equipe a = new Equipe("Wydad.ac",150);
        int retour = equipeDAO.ajouter(a);

        System.out.println(retour + " lignes ajoutées");

        // Test de la méthode getArticle
        Equipe a2 = equipeDAO.getEquipe(1);
        System.out.println(a2);

        // Test de la méthode getListeArticles
        List<Equipe> liste = equipeDAO.getListeEquipes();
        for (Equipe art : liste) {
            System.out.println(art.toString());
        }
    }
}