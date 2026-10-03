package com.example.entities;


import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Reservation {

    private long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String motif;
    private Utilisateur utilisateur;
    private Salle salle;



}
