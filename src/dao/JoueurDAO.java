package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import config.ConfigReader;
import controller.Joueur;

/**
 * Classe d'accès aux données contenues dans la table Joueur.
 * @version 1.1
 */
public class JoueurDAO {

    /** URL de connexion à la base de données. */
    private final String URL;

    /** Identifiant de connexion à la base de données. */
    private final String LOGIN;

    /** Mot de passe de connexion à la base de données. */
    private final String PASS;

    /**
     * Constructeur de la classe JoueurDAO.
     */
    public JoueurDAO() {
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
     * Ajoute un joueur dans la table Joueur.
     * @param nouvJoueur Nouveau joueur.
     * @return Nombre de lignes ajoutées.
     */
    public int ajouter(Joueur nouvJoueur) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO joueur (id, nom, prenom, position, age, prix, aVendre, equipe_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, nouvJoueur.getId());
            ps.setString(2, nouvJoueur.getNom());
            ps.setString(3, nouvJoueur.getPrenom());
            ps.setString(4, nouvJoueur.getPosition());
            ps.setInt(5, nouvJoueur.getAge());
            ps.setInt(6, nouvJoueur.getPrix());
            ps.setBoolean(7, nouvJoueur.isaVendre());
            ps.setInt(8, nouvJoueur.getEquipe_id());
            retour = ps.executeUpdate();
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception t) {}
            try { if (con != null) con.close(); } catch (Exception t) {}
        }
        return retour;
    }

    /**
     * Récupère un joueur à partir de son identifiant.
     * @param id Identifiant du joueur.
     * @return Joueur correspondant ou null si inexistant.
     */
    public Joueur getJoueur(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Joueur retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM joueur WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Joueur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("position"), rs.getInt("age"), rs.getInt("prix"),
                        rs.getBoolean("aVendre"), rs.getInt("equipe_id"));
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
     * Récupère les joueurs d'une équipe donnée.
     * @param id Identifiant de l'équipe.
     * @return Liste des joueurs de l'équipe.
     */
    public List<Joueur> getTeamJoueurs(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Joueur> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM joueur WHERE equipe_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Joueur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("position"), rs.getInt("age"), rs.getInt("prix"),
                        rs.getBoolean("aVendre"), rs.getInt("equipe_id")));
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
     * Récupère tous les joueurs stockés dans la table Joueur.
     * @return Liste des joueurs.
     */
    public List<Joueur> getListeJoueurs() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Joueur> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM joueur");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Joueur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("position"), rs.getInt("age"), rs.getInt("prix"),
                        rs.getBoolean("aVendre"), rs.getInt("equipe_id")));
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
     * Met à jour les informations d'un joueur.
     * @param joueur Joueur à mettre à jour.
     * @return Nombre de lignes mises à jour.
     */
    public int updateJoueur(Joueur joueur) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("UPDATE joueur SET nom = ?, prenom = ?, position = ?, age = ?, prix = ?, aVendre = ?, equipe_id = ? WHERE id = ?");
            ps.setString(1, joueur.getNom());
            ps.setString(2, joueur.getPrenom());
            ps.setString(3, joueur.getPosition());
            ps.setInt(4, joueur.getAge());
            ps.setInt(5, joueur.getPrix());
            ps.setBoolean(6, joueur.isaVendre());
            ps.setInt(7, joueur.getEquipe_id());
            ps.setInt(8, joueur.getId());
            retour = ps.executeUpdate();
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
        return retour;
    }

    /**
     * Récupère la liste des joueurs qui sont à vendre.
     * @return Liste des joueurs disponibles sur le marché des transferts.
     */
    public List<Joueur> getListeJoueursAVendre() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Joueur> retour = new ArrayList<>();

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM joueur WHERE aVendre = true");

            // Exécution de la requête
            rs = ps.executeQuery();

            // Parcours des résultats et ajout à la liste
            while (rs.next()) {
                retour.add(new Joueur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("position"),
                    rs.getInt("age"),
                    rs.getInt("prix"),
                    rs.getBoolean("aVendre"),
                    rs.getInt("equipe_id")
                ));
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
     * Récupère les joueurs correspondant à un filtre textuel.
     * @param queryString Texte de recherche.
     * @return Liste des joueurs correspondants.
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
                joueurs.add(new Joueur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("position"), rs.getInt("age"), rs.getInt("prix"),
                        rs.getBoolean("aVendre"), rs.getInt("equipe_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return joueurs;
    }
}
