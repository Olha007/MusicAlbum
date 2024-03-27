package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Album;
import ua.edu.znu.musicalbum.model.Artist;
import ua.edu.znu.musicalbum.model.Genre;
import ua.edu.znu.musicalbum.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlbumDaoImpl extends MusicAlbumDaoImpl<Album> {

    public AlbumDaoImpl() {
        setClazz(Album.class);
    }

    public Album findByAlbumName(final String albumName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager
                .createQuery("SELECT a FROM Album a WHERE a.albumName = :albumName", Album.class)
                .setParameter("albumName", albumName);
        return getSingleResult(query);
    }

    public List<Album> findByReleaseYear(final int releaseYear) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager.createQuery(
                        "SELECT a FROM Album a WHERE a.releaseYear = :releaseYear", Album.class)
                .setParameter("releaseYear", releaseYear);
        return query.getResultList();
    }

    public List<Album> findByArtist(final String firstName, final String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager.createQuery(
                        "SELECT aag.album FROM AlbumArtistGroup aag WHERE aag.artist.firstName = :firstName AND aag.artist.lastName = :lastName", Album.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);
        return query.getResultList();
    }
}
//    public List<Genre> getGenresForAlbum(final Long albumId) {
//        EntityManager entityManager = getEntityManager();
//        TypedQuery<Genre> query = entityManager.createQuery(
//                        "SELECT DISTINCT s.genre FROM Song s JOIN s.albums a WHERE a.id = :albumId", Genre.class)
//                .setParameter("albumId", albumId);
//        return query.getResultList();
//    }
//
//    public List<Artist> getArtistsForAlbum(final Long albumId) {
//        EntityManager entityManager = getEntityManager();
//        TypedQuery<Artist> query = entityManager.createQuery(
//                        "SELECT distinct aag.artist FROM AlbumArtistGroup aag WHERE aag.album.id = :albumId", Artist.class)
//                .setParameter("albumId", albumId);
//        return query.getResultList();
//    }
//
//    public List<Group> getGroupsForAlbum(final Long albumId) {
//        EntityManager entityManager = getEntityManager();
//        TypedQuery<Group> query = entityManager.createQuery(
//                        "SELECT distinct aag.group FROM AlbumArtistGroup aag WHERE aag.album.id = :albumId", Group.class)
//                .setParameter("albumId", albumId);
//        return query.getResultList();
//    }
//    }








