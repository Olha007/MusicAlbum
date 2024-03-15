package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Album;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlbumDaoImpl extends MusicAlbumDaoImpl<Album> {

    public AlbumDaoImpl() {
        setClazz(Album.class);
    }

    // Метод для пошуку альбомів за назвою
    public List<Album> findByAlbumName(String albumName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Album> query = entityManager.createQuery(
                        "SELECT a FROM Album a WHERE a.albumName = :albumName", Album.class)
                .setParameter("albumName", albumName);
        return getResultList(query);
    }

    public void create(Album album) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(Album album) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Album album) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(album) ? album : entityManager.merge(album));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}



