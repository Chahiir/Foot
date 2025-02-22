package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import controller.Article;

/**
 * Classe d'accès aux données de la table article.
 * @version 1.1
 */
public class ArticleDAO {

    /** URL de connexion à la base de données. */
    final static String URL = "jdbc:mysql://localhost:3306/article";

    /** Identifiant de connexion à la base de données. */
    final static String LOGIN = "root";

    /** Mot de passe de connexion à la base de données. */
    final static String PASS = "root";

    /** Constructeur de la classe ArticleDAO. */
    public ArticleDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            System.err.println("Impossible de charger le pilote de BDD.");
        }
    }

    /**
     * Ajoute un article dans la table article.
     * @param nouvArticle Article à ajouter.
     * @return Nombre de lignes ajoutées.
     */
    public int ajouter(Article nouvArticle) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO article (art_designation, art_pu_ht, art_qte_stock) VALUES (?, ?, ?)");
            ps.setString(1, nouvArticle.getDesignation());
            ps.setDouble(2, nouvArticle.getPuHt());
            ps.setInt(3, nouvArticle.getQteStock());
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
     * Récupère un article par sa référence.
     * @param reference Référence de l'article.
     * @return L'article correspondant ou null si inexistant.
     */
    public Article getArticle(int reference) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Article retour = null;
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM article WHERE art_reference = ?");
            ps.setInt(1, reference);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Article(rs.getInt("art_reference"), rs.getString("art_designation"), rs.getDouble("art_pu_ht"), rs.getInt("art_qte_stock"));
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
     * Récupère tous les articles de la table.
     * @return Liste des articles.
     */
    public List<Article> getListeArticles() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> retour = new ArrayList<>();
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM article");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Article(rs.getInt("art_reference"), rs.getString("art_designation"), rs.getDouble("art_pu_ht"), rs.getInt("art_qte_stock")));
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
     * Teste les fonctionnalités de la classe ArticleDAO.
     * @param args Arguments du programme.
     * @throws SQLException Exception SQL.
     */
    public static void main(String[] args) throws SQLException {
        ArticleDAO articleDAO = new ArticleDAO();
        Article a = new Article("Set de 2 raquettes de ping-pong", 149.9, 10);
        int retour = articleDAO.ajouter(a);
        System.out.println(retour + " lignes ajoutées");

        Article a2 = articleDAO.getArticle(1);
        System.out.println(a2);

        List<Article> liste = articleDAO.getListeArticles();
        for (Article art : liste) {
            System.out.println(art.toString());
        }
    }
}
