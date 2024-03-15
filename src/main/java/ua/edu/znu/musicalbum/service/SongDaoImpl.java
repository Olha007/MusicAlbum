package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongDaoImpl extends MusicAlbumDaoImpl<Song> {

    public SongDaoImpl() {
        setClazz(Song.class);
    }

    public List<Song> findBySongName(String songName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Song> query = entityManager.createQuery(
                        "SELECT s FROM Song s WHERE s.songName = :songName", Song.class)
                .setParameter("songName", songName);
        return getResultList(query);
    }

    public void create(Song song) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Song song) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Song song) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(song) ? song : entityManager.merge(song));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

