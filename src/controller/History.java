package controller;

import java.time.LocalDate;

public class History {
    private int id;
    private int newEquipe_id;
    private int oldEquipe_id;
    private int joueur_id;
    private LocalDate date;

    // Constructor with all parameters (including date)
    public History(int id, int newEquipe_id, int oldEquipe_id, int joueur_id, LocalDate date) {
        this.id = id;
        this.newEquipe_id = newEquipe_id;
        this.oldEquipe_id = oldEquipe_id;
        this.joueur_id = joueur_id;
        this.date = date;
    }
    public History(int newEquipe_id, int oldEquipe_id, int joueur_id) {
    	this.newEquipe_id = newEquipe_id;
    	this.oldEquipe_id = oldEquipe_id;
    	this.joueur_id = joueur_id;
    	this.date = LocalDate.now();
    }

    // Overloaded constructor without the date parameter
    public History(int id, int newEquipe_id, int oldEquipe_id, int joueur_id) {
        this(id, newEquipe_id, oldEquipe_id, joueur_id, LocalDate.now()); // Default date is today
    }

    // Getters and setters (if needed)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewEquipe_id() {
        return newEquipe_id;
    }

    public void setNewEquipe_id(int newEquipe_id) {
        this.newEquipe_id = newEquipe_id;
    }

    public int getOldEquipe_id() {
        return oldEquipe_id;
    }

    public void setOldEquipe_id(int oldEquipe_id) {
        this.oldEquipe_id = oldEquipe_id;
    }

    public int getJoueur_id() {
        return joueur_id;
    }

    public void setJoueur_id(int joueur_id) {
        this.joueur_id = joueur_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", newEquipe_id=" + newEquipe_id +
                ", oldEquipe_id=" + oldEquipe_id +
                ", joueur_id=" + joueur_id +
                ", date=" + date +
                '}';
    }
}