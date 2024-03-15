package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class GenreDaoImpl extends MusicAlbumDaoImpl<Genre> {

    public GenreDaoImpl() {
        setClazz(Genre.class);
    }

    // Пошук жанру за назвою
    public List<Genre> findByGenreName(String genreName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Genre> query = entityManager.createQuery(
                        "SELECT g FROM Genre g WHERE g.name = :genreName", Genre.class)
                .setParameter("genreName", genreName);
        List<Genre> genres = getResultList(query);
        if (genres.isEmpty()) {
            return Collections.emptyList();
        }
        return genres;
    }


    // Створення нового жанру
    public void create(Genre genre) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(genre);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Оновлення інформації про жанр
    public void update(Genre genre) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(genre);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Видалення жанру
    public void delete(Genre genre) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(genre) ? genre : entityManager.merge(genre));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

