package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Album;
import ua.edu.znu.musicalbum.model.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongDaoImpl extends MusicAlbumDaoImpl<Song> {
    public SongDaoImpl() {
        setClazz(Song.class);
    }

    public void assignSong(Long albumId, Long songId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Album album = entityManager.find(Album.class, albumId);
        Song song = entityManager.find(Song.class, songId);
        album.getSongs().add(song);
        entityManager.persist(album);
        entityManager.getTransaction().commit();
    }

    public void removeSong(Long albumId, Long songId) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Album album = entityManager.find(Album.class, albumId);
        Song song = entityManager.find(Song.class, songId);
        album.getSongs().remove(song);
        entityManager.persist(album);
        entityManager.getTransaction().commit();
    }

    public List<Song> findByName(final String songName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Song> query = entityManager
                .createQuery("SELECT s FROM Song s WHERE s.songName = :songName", Song.class)
                .setParameter("songName", songName);
        return query.getResultList();
    }
}



