package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import config.ConfigReader;
import controller.Joueur;
/**
 * Classe d'accès aux données contenues dans la table Equipe
 * @version 1.1
 * */
public class JoueurDAO {

    /**
     * Paramètres de connexion à la base de données MySQL
     * URL, LOGIN et PASS sont des constantes
     */
	private final String URL;
	private final String LOGIN;
	private final String PASS ;

    /**
     * Constructeur de la classe
     */
    public JoueurDAO() {
    	Map<String, String> config = ConfigReader.readConfig("./config.txt");
        this.URL = config.get("url");
        this.LOGIN = config.get("username");
        this.PASS = config.get("password");
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
            ps = con.prepareStatement("INSERT INTO joueur (id, nom,prenom, position,age, prix,aVendre, equipe_id) VALUES (?, ?,?, ?, ?,?, ?, ?)");
            ps.setInt(1, nouvJoueur.getId());
            ps.setString(2, nouvJoueur.getNom());
            ps.setString(3, nouvJoueur.getPrenom());
            ps.setString(4, nouvJoueur.getPosition());
            ps.setInt(5, nouvJoueur.getAge());
            ps.setInt(6, nouvJoueur.getPrix());
            ps.setBoolean(7, nouvJoueur.isaVendre());
            ps.setInt(8, nouvJoueur.getEquipe_id());

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
                retour = new Joueur(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"), rs.getString("position"),rs.getInt("age") , rs.getInt("prix"),rs.getBoolean("aVendre"),rs.getInt("equipe_id"));

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
                retour.add(new Joueur(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"),rs.getString("position"),rs.getInt("age") , rs.getInt("prix"),rs.getBoolean("aVendre"),rs.getInt("equipe_id")));

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
    
    
    public List<Joueur> getListeJoueursAVendre(){
    	 Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         List<Joueur> retour = new ArrayList<Joueur>();

         // Connexion à la base de données
         try {
             con = DriverManager.getConnection(URL, LOGIN, PASS);
             ps = con.prepareStatement("SELECT * FROM joueur WHERE aVendre = true");

             // On exécute la requête
             rs = ps.executeQuery();
             // On parcourt les lignes du résultat
             while (rs.next())
                 retour.add(new Joueur(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"),rs.getString("position"),rs.getInt("age") , rs.getInt("prix"),rs.getBoolean("aVendre"),rs.getInt("equipe_id")));

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
    
    public int updateJoueur(Joueur joueur) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connexion à la base de données
        try {
            // Tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);

            // Préparation de l'instruction SQL pour la mise à jour
            ps = con.prepareStatement("UPDATE joueur SET nom = ?, prenom = ?, position = ?, age = ?, prix = ?, aVendre = ?, equipe_id = ? WHERE id = ?");
            
            // Attribution des valeurs
            ps.setString(1, joueur.getNom());
            ps.setString(2, joueur.getPrenom());
            ps.setString(3, joueur.getPosition());
            ps.setInt(4, joueur.getAge());
            ps.setInt(5, joueur.getPrix());
            ps.setBoolean(6, joueur.isaVendre());
            ps.setInt(7, joueur.getEquipe_id());
            ps.setInt(8, joueur.getId());

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
     * Récupère les joueurs correspondant à un filtre textuel.
     * @param queryString Le texte de recherche.
     * @return Une liste de joueurs correspondant au filtre.
     */
    public List<Joueur> getJoueursByFilter(String queryString) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Joueur> joueurs = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "SELECT * FROM joueur WHERE nom LIKE ? OR prenom LIKE ? OR position LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + queryString + "%");
            ps.setString(2, "%" + queryString + "%");
            ps.setString(3, "%" + queryString + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                joueurs.add(new Joueur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenon"),
                    rs.getString("position"),
                    rs.getInt("age"),
                    rs.getInt("prix"),
                    rs.getBoolean("aVendre"),
                    rs.getInt("equipe_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return joueurs;
    }
    
    // Main permettant de tester la classe
    public static void main(String[] args) throws SQLException {
        JoueurDAO joueurDAO = new JoueurDAO();
        // Test de la méthode ajouter
        Joueur a = new Joueur("TEST","prenom","Att",25,15,1);
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