package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreDaoImpl extends MusicAlbumDaoImpl<Genre> {

    public GenreDaoImpl() {
        setClazz(Genre.class);
    }

    public List<Genre> findByArtist(final String firstName, final String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Genre> query = entityManager
                .createQuery("SELECT DISTINCT g FROM Genre g JOIN g.songs s JOIN s.albums a JOIN a.albumArtistGroups aag JOIN aag.artist ar WHERE ar.firstName = :firstName AND ar.lastName = :lastName", Genre.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Genre> findByAlbum(final String albumName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Genre> query = entityManager
                .createQuery("SELECT DISTINCT g FROM Genre g JOIN g.songs s JOIN s.albums a WHERE a.albumName = :albumName", Genre.class)
                .setParameter("albumName", albumName);
        return query.getResultList();
    }
}



