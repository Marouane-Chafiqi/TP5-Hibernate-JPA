package com.example.entities;


import org.hibernate.loader.collection.OneToManyJoinWalker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private Integer capacite;
    private String description;
    private String batiment;
    private Integer etage;
    private List<Reservation> reservations = new ArrayList<>();


    public Salle() {
    }

    public Salle(String nom, Integer capacite, String description, String batiment, Integer etage, List<Reservation> reservations) {
        this.nom = nom;
        this.capacite = capacite;
        this.description = description;
        this.batiment = batiment;
        this.etage = etage;
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public Integer getEtage() {
        return etage;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
