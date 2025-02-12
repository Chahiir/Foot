package controller;

/**
 * La classe Composition représente la relation entre un joueur et un match.
 * Elle permet d'associer un joueur à un match spécifique dans une composition d'équipe.
 */
public class Composition {

    private int id;          // Identifiant unique de la composition
    private int joueur_id;   // Identifiant du joueur associé à la composition
    private int match_id;    // Identifiant du match associé à la composition

    /**
     * Constructeur avec tous les paramètres.
     * 
     * @param id        Identifiant unique de la composition
     * @param joueur_id Identifiant du joueur
     * @param match_id  Identifiant du match
     */
    public Composition(int id, int joueur_id, int match_id) {
        super();
        this.id = id;
        this.joueur_id = joueur_id;
        this.match_id = match_id;
    }

    /**
     * Constructeur sans identifiant (utilisé lors de la création d'une nouvelle composition).
     * L'identifiant peut être généré automatiquement par la base de données.
     * 
     * @param joueur_id Identifiant du joueur
     * @param match_id  Identifiant du match
     */
    public Composition(int joueur_id, int match_id) {
        super();
        this.joueur_id = joueur_id;
        this.match_id = match_id;
    }

    /**
     * Obtient l'identifiant unique de la composition.
     * 
     * @return L'identifiant de la composition
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient l'identifiant du joueur associé à cette composition.
     * 
     * @return L'identifiant du joueur
     */
    public int getJoueur_id() {
        return joueur_id;
    }

    /**
     * Obtient l'identifiant du match associé à cette composition.
     * 
     * @return L'identifiant du match
     */
    public int getMatch_id() {
        return match_id;
    }

    /**
     * Définit l'identifiant du joueur pour cette composition.
     * 
     * @param joueur_id Nouvel identifiant du joueur
     */
    public void setJoueur_id(int joueur_id) {
        this.joueur_id = joueur_id;
    }

    /**
     * Définit l'identifiant du match pour cette composition.
     * 
     * @param match_id Nouvel identifiant du match
     */
    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }
}
