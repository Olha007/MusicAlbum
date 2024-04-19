package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Album;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlbumDaoImpl extends MusicAlbumDaoImpl<Album> {

    public AlbumDaoImpl() {
        setClazz(Album.class);
    }

    public List<Album> findByAlbumName(final String albumName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager
                .createQuery("SELECT a FROM Album a WHERE a.albumName = :albumName", Album.class)
                .setParameter("albumName", albumName);
        return query.getResultList();
    }

    public List<Album> findByReleaseYear(final int releaseYear) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager
                .createQuery("SELECT a FROM Album a WHERE a.releaseYear = :releaseYear", Album.class)
                .setParameter("releaseYear", releaseYear);
        return query.getResultList();
    }

    public List<Album> findByArtist(final String firstName, final String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager
                .createQuery("SELECT aag.album FROM AlbumArtistGroup aag WHERE aag.artist.firstName = :firstName AND aag.artist.lastName = :lastName", Album.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);
        return query.getResultList();
    }
}








