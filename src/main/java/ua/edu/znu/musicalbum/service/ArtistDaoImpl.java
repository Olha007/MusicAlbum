package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ArtistDaoImpl extends MusicAlbumDaoImpl<Artist> {

    public ArtistDaoImpl() {
        setClazz(Artist.class);
    }

    // Пошук артистів за жанром
    public List<Artist> findByGenre(final Genre genre) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Artist> query = entityManager.createQuery(
                        "SELECT DISTINCT a FROM Artist a " +
                                "JOIN a.albumArtistGroups aa " +
                                "JOIN aa.album al " +
                                "WHERE :genre MEMBER OF al.genres", Artist.class)
                .setParameter("genre", genre);
        return getResultList(query);
    }

    // Пошук артистів за ім'ям або прізвищем
    public List<Artist> findByName(String name) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Artist> query = entityManager.createQuery(
                        "SELECT a FROM Artist a " +
                                "WHERE a.firstName = :name OR a.lastName = :name", Artist.class)
                .setParameter("name", name);
        return getResultList(query);
    }

    // Створення нового артиста
    public void create(Artist artist) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Оновлення інформації про артиста
    public void update(Artist artist) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Видалення артиста
    public void delete(Artist artist) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(artist) ? artist : entityManager.merge(artist));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Пошук артистів за альбомом
    public List<Artist> findByAlbum(Album album) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Artist> query = entityManager.createQuery(
                        "SELECT DISTINCT a FROM Artist a " +
                                "JOIN a.albumArtistGroups aa " +
                                "WHERE aa.album = :album", Artist.class)
                .setParameter("album", album);
        return getResultList(query);
    }

}








