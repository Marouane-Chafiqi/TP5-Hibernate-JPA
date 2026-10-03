package com.example;

import com.example.model.Reservation;
import com.example.model.Salle;
import com.example.model.Utilisateur;
import com.example.repository.SalleRepositoryImpl;
import com.example.service.SalleService;
import com.example.service.SalleServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations");
        EntityManager em = emf.createEntityManager();
        SalleService service = new SalleServiceImpl(new SalleRepositoryImpl(em));

        em.getTransaction().begin();

        Utilisateur user = new Utilisateur("Dupont", "Jean", "jean@example.com");
        em.persist(user);

        Salle a = new Salle("Salle A", 30);
        a.setBatiment("Batiment A");
        em.persist(a);

        Salle b = new Salle("Salle B", 15);
        b.setBatiment("Batiment B");
        em.persist(b);

        Salle c = new Salle("Salle C", 50);
        c.setBatiment("Batiment B");
        em.persist(c);

        LocalDateTime debut = LocalDateTime.now().plusDays(1);
        LocalDateTime fin = debut.plusHours(2);
        Reservation r = new Reservation(debut, fin, "Reunion");
        r.setUtilisateur(user);
        r.setSalle(a);
        em.persist(r);

        em.getTransaction().commit();

        System.out.println("Salles disponibles:");
        List<Salle> libres = service.findAvailableRooms(debut, fin);
        for (Salle s : libres) {
            System.out.println("- " + s.getNom());
        }

        System.out.println("Recherche (capacite >= 20 et Batiment B):");
        Map<String, Object> criteres = new HashMap<>();
        criteres.put("capaciteMin", 20);
        criteres.put("batiment", "Batiment B");
        List<Salle> trouvees = service.searchRooms(criteres);
        for (Salle s : trouvees) {
            System.out.println("- " + s.getNom());
        }

        System.out.println("Pagination (2 par page):");
        System.out.println("Nombre de pages: " + service.getTotalPages(2));
        List<Salle> page1 = service.getPaginatedRooms(1, 2);
        for (Salle s : page1) {
            System.out.println("- " + s.getNom());
        }

        em.close();
        emf.close();
    }
}