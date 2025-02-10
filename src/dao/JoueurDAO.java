package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données contenues dans la table Equipe
 * @version 1.1
 * */
public class JoueurDAO {

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
    public JoueurDAO() {
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
    public int ajouter(Joueur nouvJoueur) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connexion à la base de données
        try {
            // Tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
            // Les getters permettent de récupérer les valeurs des attributs souhaités de nouvArticle
            ps = con.prepareStatement("INSERT INTO joueur (id, nom, position,age, prix, equipe_id) VALUES (?, ?, ?,?, ?, ?)");
            ps.setInt(1, nouvJoueur.getId());
            ps.setString(2, nouvJoueur.getNom());
            ps.setString(3, nouvJoueur.getPosition());
            ps.setInt(4, nouvJoueur.getAge());
            ps.setInt(5, nouvJoueur.getPrix());
            ps.setInt(6, nouvJoueur.getEquipe_id());

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
    public Joueur getJoueur(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Joueur retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM joueur WHERE id = ?");
            ps.setInt(1, id);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Joueur(rs.getInt("id"), rs.getString("nom"),rs.getString("position"),rs.getInt("age") , rs.getInt("prix"),rs.getInt("equipe_id"));

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
    public List<Joueur> getListeJoueurs() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Joueur> retour = new ArrayList<Joueur>();

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM joueur");

            // On exécute la requête
            rs = ps.executeQuery();
            // On parcourt les lignes du résultat
            while (rs.next())
                retour.add(new Joueur(rs.getInt("id"), rs.getString("nom"),rs.getString("position"),rs.getInt("age") , rs.getInt("prix"),rs.getInt("equipe_id")));

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
        JoueurDAO joueurDAO = new JoueurDAO();
        // Test de la méthode ajouter
        Joueur a = new Joueur("TEST","Att",25,15,1);
        int retour = joueurDAO.ajouter(a);

        System.out.println(retour + " lignes ajoutées");

        // Test de la méthode getArticle
        Joueur a2 = joueurDAO.getJoueur(1);
        System.out.println(a2);

        // Test de la méthode getListeArticles
        List<Joueur> liste = joueurDAO.getListeJoueurs();
        for (Joueur art : liste) {
            System.out.println(art.toString());
        }
    }
}