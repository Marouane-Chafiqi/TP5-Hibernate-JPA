# TP 5 : Salles disponibles, recherche multi-critères et pagination

## Objectif
Avec JPA / Hibernate et une base H2 en mémoire :
- trouver les salles disponibles pour un créneau horaire
- rechercher des salles selon plusieurs critères (capacité, bâtiment, ...)
- paginer la liste des salles

## Technologies
Java 8, Maven, JPA 2.2, Hibernate 5.6, H2, IntelliJ IDEA

## Structure
```
src/main/java/com/example
├── App.java          (classe principale : données de test + tests)
├── model             (Utilisateur, Salle, Reservation, Equipement)
├── repository        (SalleRepository, SalleRepositoryImpl : requêtes JPQL)
└── service           (SalleService, SalleServiceImpl)
src/main/resources/META-INF/persistence.xml
```

## Exécution
1. Ouvrir le projet dans IntelliJ (File > Open > pom.xml).
2. Attendre le chargement des dépendances Maven.
3. Clic droit sur `App.java` > Run 'App.main()'.

## Principe des requêtes
- **Salles disponibles** : on exclut les salles qui ont une réservation qui chevauche le créneau
  (`dateDebut < fin AND dateFin > debut`).
- **Recherche multi-critères** : la requête JPQL est construite selon les critères fournis.
- **Pagination** : `setFirstResult((page - 1) * size)` et `setMaxResults(size)`.

## Résultat de l'exécution

<img width="1099" height="449" alt="JPA 1" src="https://github.com/user-attachments/assets/ba3ac12d-6acf-4db3-b941-41d90657e7e8" />


- **Disponibles** : Salle B et Salle C (Salle A est réservée)
- **Recherche** (capacité >= 20 et Batiment B) : Salle C
- **Pagination** (2 par page) : 2 pages, la page 1 contient Salle A et Salle B
