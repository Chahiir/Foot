package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.Staff;
/**
 * Classe d'accès aux données contenues dans la table Equipe
 * @version 1.1
 * */
public class StaffDAO {

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
    public StaffDAO() {
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
    public int ajouter(Staff nouvStaff) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connexion à la base de données
        try {
            // Tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
            // Les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
            ps = con.prepareStatement("INSERT INTO staff (id, nom, role,specialite, equipe_id) VALUES (?, ?,?, ?, ?)");
            ps.setInt(1, nouvStaff.getId());
            ps.setString(2, nouvStaff.getNom());
            ps.setString(3, nouvStaff.getRole());
            ps.setString(4, nouvStaff.getSpecialite());
            ps.setInt(5, nouvStaff.getEquipe_id());

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
    public Staff getStaff(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Staff retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM staff WHERE id = ?");
            ps.setInt(1, id);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Staff(rs.getInt("id"), rs.getString("nom"),rs.getString("role"),rs.getString("specialite") ,rs.getInt("equipe_id"));

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
    public List<Staff> getListeStaff() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Staff> retour = new ArrayList<Staff>();

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM staff");

            // On exécute la requête
            rs = ps.executeQuery();
            // On parcourt les lignes du résultat
            while (rs.next())
                retour.add(new Staff(rs.getInt("id"), rs.getString("nom"),rs.getString("role"),rs.getString("specialite") ,rs.getInt("equipe_id")));

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
        StaffDAO staffDAO  = new StaffDAO();
        // Test de la méthode ajouter
        Staff a = new Staff("TEST","Physic","Massage",1);
        int retour = staffDAO.ajouter(a);

        System.out.println(retour + " lignes ajoutées");

        // Test de la méthode getArticle
        Staff a2 = staffDAO.getStaff(1);
        System.out.println(a2);

        // Test de la méthode getListeArticles
        List<Staff> liste = staffDAO.getListeStaff();
        for (Staff art : liste) {
            System.out.println(art.toString());
        }
    }
}