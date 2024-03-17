package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Song;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongDaoImpl extends MusicAlbumDaoImpl<Song> {

    public SongDaoImpl() {
        setClazz(Song.class);
    }

    //пошук пісні за назвою
    public List<Song> findByName(final String songName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Song> query = entityManager.createQuery(
                "SELECT s FROM Song s WHERE s.songName = :songName", Song.class);
        query.setParameter("songName", songName);
        return query.getResultList();
    }
}


