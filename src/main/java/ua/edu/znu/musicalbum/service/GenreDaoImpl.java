package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreDaoImpl extends MusicAlbumDaoImpl<Genre> {
    public GenreDaoImpl() {
        setClazz(Genre.class);
    }

    //знаходить жанр за назвою
    public Genre findByName(final String genreName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Genre> query = entityManager.createQuery(
                "SELECT g FROM Genre g WHERE g.genreName = :genreName", Genre.class);
        query.setParameter("genreName", genreName);
        return getSingleResult(query);
    }

    //отримує список жанрів асоційованих з певним артистом
    public List<Genre> findByArtist(final String firstName, final String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Genre> query = entityManager.createQuery(
                "SELECT DISTINCT g FROM Genre g JOIN g.songs s JOIN s.albums a JOIN a.albumArtistGroups aag JOIN aag.artist ar WHERE ar.firstName = :firstName AND ar.lastName = :lastName", Genre.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    //знаходить унікальні жанри пісень в певному альбом
    public List<Genre> findByAlbum(final String albumName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Genre> query = entityManager.createQuery(
                "SELECT DISTINCT g FROM Genre g JOIN g.songs s JOIN s.albums a WHERE a.albumName = :albumName", Genre.class);
        query.setParameter("albumName", albumName);
        return query.getResultList();
    }
}


