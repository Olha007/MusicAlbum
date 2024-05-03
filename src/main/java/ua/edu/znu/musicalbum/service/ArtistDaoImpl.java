package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

public class ArtistDaoImpl extends MusicAlbumDaoImpl<Artist> {

    public ArtistDaoImpl() {
        setClazz(Artist.class);
    }

    public void assignArtist(Long albumId, Long artistId) {
        EntityManager entityManager = getEntityManager();
        AlbumArtistGroup aag = new AlbumArtistGroup();
        entityManager.getTransaction().begin();
        Album album = entityManager.find(Album.class, albumId);
        Artist artist = entityManager.find(Artist.class, artistId);
        aag.setAlbum(album);
        aag.setArtist(artist);
        entityManager.persist(aag);
        entityManager.getTransaction().commit();
    }

    public void removeArtist(Long albumId, Long artistId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Album album = entityManager.find(Album.class, albumId);
        Artist artist = entityManager.find(Artist.class, artistId);
        Collection<AlbumArtistGroup> aags = album.getAlbumArtistGroups();
        for (AlbumArtistGroup aag : aags) {
            if (aag.getArtist() != null && aag.getArtist().equals(artist)) {
                aag.setArtist(null);
                entityManager.persist(aag);
            }
        }
        entityManager.getTransaction().commit();
    }

    public Artist findByName(final String firstName, final String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Artist> query = entityManager
                .createQuery("SELECT a FROM Artist a WHERE a.firstName = :firstName AND a.lastName = :lastName", Artist.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);
        return getSingleResult(query);
    }

    public List<Artist> findByAlbum(final String albumName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Artist> query = entityManager
                .createQuery("SELECT aag.artist FROM AlbumArtistGroup aag WHERE aag.album.albumName = :albumName", Artist.class)
                .setParameter("albumName", albumName);
        return query.getResultList();
    }
}










