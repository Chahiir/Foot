package dao;

import java.sql.*;
import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import controller.Equipe;

/**
 * Classe d'accès aux données contenues dans la table Equipe.
 * @version 1.1
 */
public class EquipeDAO {

    /** URL de connexion à la base de données. */
    private final String URL;

    /** Identifiant de connexion à la base de données. */
    private final String LOGIN;

    /** Mot de passe de connexion à la base de données. */
    private final String PASS;

    /**
     * Constructeur de la classe EquipeDAO.
     */
    public EquipeDAO() {
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
     * Ajoute une équipe dans la table Equipe.
     * @param nouvEquipe Nouvelle équipe.
     * @return Nombre de lignes ajoutées.
     */
    public int ajouter(Equipe nouvEquipe) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO equipe (id, nom, solde) VALUES (?, ?, ?)");
            ps.setInt(1, nouvEquipe.getId());
            ps.setString(2, nouvEquipe.getNom());
            ps.setInt(3, nouvEquipe.getSolde());
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
     * Récupère une équipe à partir de son identifiant.
     * @param id Identifiant de l'équipe.
     * @return Équipe correspondante ou null si inexistante.
     */
    public Equipe getEquipe(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equipe retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM equipe WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("solde"));
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
     * Met à jour les informations d'une équipe.
     * @param equipe Équipe à mettre à jour.
     */
    public void updateEquipe(Equipe equipe) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("UPDATE equipe SET nom = ?, solde = ? WHERE id = ?");
            ps.setString(1, equipe.getNom());
            ps.setInt(2, equipe.getSolde());
            ps.setInt(3, equipe.getId());
            ps.executeUpdate();
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    }

    /**
     * Récupère toutes les équipes stockées dans la table Equipe.
     * @return Liste des équipes.
     */
    public List<Equipe> getListeEquipes() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Equipe> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM equipe");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Equipe(rs.getInt("id"), rs.getString("nom"), rs.getInt("solde")));
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
     * Supprime une équipe de la table Equipe.
     * @param id Identifiant de l'équipe.
     */
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
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    /**
     * Vérifie si une équipe a un nombre suffisant de joueurs.
     * @param equipeId Identifiant de l'équipe.
     * @param requiredPlayers Nombre minimum de joueurs requis.
     * @return True si l'équipe a assez de joueurs, sinon False.
     */
    public static boolean hasEnoughPlayers(int equipeId, int requiredPlayers) {
        String sql = "SELECT COUNT(*) FROM joueur WHERE equipe_id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/footmanager", "root", "root");
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, equipeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) >= requiredPlayers;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Méthode principale pour tester la classe EquipeDAO.
     * @param args Arguments du programme.
     * @throws SQLException Exception SQL.
     */
    public static void main(String[] args) throws SQLException {
        EquipeDAO equipeDAO = new EquipeDAO();
        Equipe equipe1 = new Equipe("Wydad AC", 150);
        int lignesAjoutees = equipeDAO.ajouter(equipe1);
        System.out.println(lignesAjoutees + " lignes ajoutées");

        Equipe equipe2 = equipeDAO.getEquipe(1);
        System.out.println(equipe2);

        List<Equipe> listeEquipes = equipeDAO.getListeEquipes();
        for (Equipe equipe : listeEquipes) {
            System.out.println(equipe.toString());
        }
    }
}
