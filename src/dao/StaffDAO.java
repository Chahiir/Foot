package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import config.ConfigReader;
import controller.Staff;

/**
 * Classe d'accès aux données contenues dans la table Staff
 * Permet d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur la table Staff.
 * @version 1.1
 */
public class StaffDAO {

    /**
     * Paramètres de connexion à la base de données MySQL
     */
	private final String URL;
	private final String LOGIN;
	private final String PASS ;

    /**
     * Constructeur de la classe
     * Initialise les paramètres de connexion à la base de données en lisant un fichier de configuration.
     * Charge également le pilote JDBC pour MySQL.
     */
    public StaffDAO() {
    	Map<String, String> config = ConfigReader.readConfig("./config.txt");
        this.URL = config.get("url");
        this.LOGIN = config.get("username");
        this.PASS = config.get("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Chargement du pilote JDBC MySQL
        } catch (ClassNotFoundException e2) {
            System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Ajoute un nouveau membre du staff dans la base de données.
     * @param nouvStaff Le membre du staff à ajouter.
     * @return Le nombre de lignes ajoutées dans la table.
     */
    public int ajouter(Staff nouvStaff) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO staff (id, nom, role, specialite, equipe_id) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, nouvStaff.getId());
            ps.setString(2, nouvStaff.getNom());
            ps.setString(3, nouvStaff.getRole());
            ps.setString(4, nouvStaff.getSpecialite());
            ps.setInt(5, nouvStaff.getEquipe_id());

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
     * Récupère un membre du staff à partir de son identifiant.
     * @param id L'identifiant du membre du staff.
     * @return Le membre du staff correspondant ou null s'il n'existe pas.
     */
    public Staff getStaff(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Staff retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM staff WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Staff(rs.getInt("id"), rs.getString("nom"), rs.getString("role"), rs.getString("specialite"), rs.getInt("equipe_id"));
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
     * Récupère la liste complète des membres du staff stockés dans la base de données.
     * @return Une liste contenant tous les membres du staff.
     */
    public List<Staff> getListeStaff() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Staff> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM staff");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Staff(rs.getInt("id"), rs.getString("nom"), rs.getString("role"), rs.getString("specialite"), rs.getInt("equipe_id")));
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
     * Met à jour les informations d'un membre du staff dans la base de données.
     * @param staff Le membre du staff à mettre à jour.
     * @return Le nombre de lignes mises à jour.
     */
    public int updateStaff(Staff staff) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("UPDATE staff SET nom = ?, role = ?, specialite = ?, equipe_id = ? WHERE id = ?");
            ps.setString(1, staff.getNom());
            ps.setString(2, staff.getRole());
            ps.setString(3, staff.getSpecialite());
            ps.setInt(4, staff.getEquipe_id());
            ps.setInt(5, staff.getId());

            retour = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception t) {}
            try { if (con != null) con.close(); } catch (Exception t) {}
        }
        return retour;
    }

    /**
     * Supprime un membre du staff de la base de données.
     * @param id Identifiant du membre du staff à supprimer.
     */
    public void deleteStaff(int id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("DELETE FROM staff WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception t) {}
            try { if (con != null) con.close(); } catch (Exception t) {}
        }
    }

    /**
     * Fonction principale permettant de tester la classe StaffDAO.
     * @param args Arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) throws SQLException {
        StaffDAO staffDAO = new StaffDAO();

        // Test de la méthode ajouter
        Staff a = new Staff("TEST", "Physic", "Massage", 1);
        int retour = staffDAO.ajouter(a);
        System.out.println(retour + " lignes ajoutées");

        // Test de la méthode getStaff
        Staff a2 = staffDAO.getStaff(1);
        System.out.println(a2);

        // Test de la méthode getListeStaff
        List<Staff> liste = staffDAO.getListeStaff();
        for (Staff art : liste) {
            System.out.println(art.toString());
        }
    }
}
