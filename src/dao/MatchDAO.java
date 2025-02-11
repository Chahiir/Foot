package dao;

import java.sql.*;
import java.time.LocalDate;

import config.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Match;
/**
 * Classe d'accès aux données contenues dans la table Equipe.
 * Permet d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur la table Equipe.
 * @version 1.1
 */
public class MatchDAO {

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
    public MatchDAO() {
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
    public int ajouter(Match nouvMatch) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("INSERT INTO match (id,date, adversaire_id, equipe_id) VALUES (?, ?, ?, ?)");
            ps.setInt(1, nouvMatch.getId());
            ps.setDate(2, java.sql.Date.valueOf(nouvMatch.getDate()));
            ps.setInt(3, nouvMatch.getAdversaire());
            ps.setInt(4, nouvMatch.getEquipe());


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
    public List<Match> getAllMatch() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Match> retour = new ArrayList<>();

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de la requête SQL
            ps = con.prepareStatement("SELECT * FROM match");

            // Exécution de la requête
            rs = ps.executeQuery();
            // Parcours des résultats
            while (rs.next()) {
                retour.add(new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id")));
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
    
    
    public Match getMatch(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match WHERE id = ?");
            ps.setInt(1, id);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id"));

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

    public Match getMatch(int equipe, int adversaire) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match WHERE equipe_id = ? AND adversaire_id = ?");
            ps.setInt(1, equipe);
            ps.setInt(2, adversaire);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id"));

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
    
    public Match getMatchByDate(Date date) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Match retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM match WHERE date = ?");
            ps.setDate(1, date);

            // On exécute la requête
            rs = ps.executeQuery();
            // Passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Match(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("adversaire_id"), rs.getInt("equipe_id"));

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
    
    public void generateMatch(int equipe1Id, int equipe2Id, LocalDate matchDate) {
        if (!EquipeDAO.hasEnoughPlayers(equipe1Id, 11) || !EquipeDAO.hasEnoughPlayers(equipe2Id, 11)) {
            System.out.println("One or both teams do not have enough players.");
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, LOGIN, PASS)) {
            con.setAutoCommit(false); // Start transaction

            // Insert match into the `match` table
            String insertMatchSql = "INSERT INTO match (date, adversaire_id, equipe_id) VALUES (?, ?, ?)";
            int matchId;
            try (PreparedStatement ps = con.prepareStatement(insertMatchSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDate(1, Date.valueOf(matchDate));
                ps.setInt(2, equipe2Id);
                ps.setInt(3, equipe1Id);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        matchId = rs.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve match ID.");
                    }
                }
            }

            // Generate a logical score
            int scoreEquipe1 = ResultatDAO.generateScore();
            int scoreEquipe2 = ResultatDAO.generateScore();

            // Insert result into the `resultat` table
            String insertResultSql = "INSERT INTO resultat (score_equipe, score_adversaire, match_id) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertResultSql)) {
                ps.setInt(1, scoreEquipe1);
                ps.setInt(2, scoreEquipe2);
                ps.setInt(3, matchId);
                ps.executeUpdate();
            }

            // Insert players into the `composition` table
            CompositionDAO.insertPlayersIntoComposition(con, equipe1Id, matchId);
            CompositionDAO.insertPlayersIntoComposition(con, equipe2Id, matchId);

            con.commit(); // Commit transaction
            System.out.println("Match generated successfully. Match ID: " + matchId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteMatch(int id) {
    	Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            String sql = "DELETE FROM match WHERE id = ?";
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