package com.example.repository;

import com.example.model.Salle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalleRepositoryImpl implements SalleRepository {

    private final EntityManager em;

    public SalleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Salle> findAvailableRooms(LocalDateTime start, LocalDateTime end) {
        return em.createQuery(
                        "SELECT s FROM Salle s WHERE s.id NOT IN " +
                                "(SELECT r.salle.id FROM Reservation r WHERE r.dateDebut < :end AND r.dateFin > :start)",
                        Salle.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    @Override
    public List<Salle> findByCriteria(Map<String, Object> criteria) {
        String jpql = "SELECT s FROM Salle s WHERE 1=1";
        Map<String, Object> params = new HashMap<>();

        if (criteria.containsKey("nom")) {
            jpql += " AND s.nom LIKE :nom";
            params.put("nom", "%" + criteria.get("nom") + "%");
        }
        if (criteria.containsKey("capaciteMin")) {
            jpql += " AND s.capacite >= :capaciteMin";
            params.put("capaciteMin", criteria.get("capaciteMin"));
        }
        if (criteria.containsKey("capaciteMax")) {
            jpql += " AND s.capacite <= :capaciteMax";
            params.put("capaciteMax", criteria.get("capaciteMax"));
        }
        if (criteria.containsKey("batiment")) {
            jpql += " AND s.batiment = :batiment";
            params.put("batiment", criteria.get("batiment"));
        }
        if (criteria.containsKey("etage")) {
            jpql += " AND s.etage = :etage";
            params.put("etage", criteria.get("etage"));
        }

        TypedQuery<Salle> query = em.createQuery(jpql, Salle.class);
        for (Map.Entry<String, Object> p : params.entrySet()) {
            query.setParameter(p.getKey(), p.getValue());
        }
        return query.getResultList();
    }

    @Override
    public List<Salle> findAllPaginated(int page, int size) {
        return em.createQuery("SELECT s FROM Salle s ORDER BY s.id", Salle.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(s) FROM Salle s", Long.class).getSingleResult();
    }

    @Override
    public Salle findById(Long id) {
        return em.find(Salle.class, id);
    }

    @Override
    public List<Salle> findAll() {
        return em.createQuery("SELECT s FROM Salle s", Salle.class).getResultList();
    }

    @Override
    public void save(Salle salle) {
        em.persist(salle);
    }

    @Override
    public void update(Salle salle) {
        em.merge(salle);
    }

    @Override
    public void delete(Salle salle) {
        em.remove(salle);
    }
}