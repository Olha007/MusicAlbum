package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDaoImpl extends MusicAlbumDaoImpl<Group> {

    public GroupDaoImpl() {
        setClazz(Group.class);
    }

    //пошук групи за назвою
    public Group findByName(final String groupName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager.createQuery(
                "SELECT g FROM Group g WHERE g.groupName = :groupName", Group.class);
        query.setParameter("groupName", groupName);
        return getSingleResult(query);
    }

    //отримання всіх груп, які мають альбоми в певному жанрі
    public List<Group> findByGenre(final String genreName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager.createQuery(
                "SELECT DISTINCT aag.group FROM AlbumArtistGroup aag JOIN aag.album.albums al JOIN al.songs s JOIN s.genre g WHERE g.genreName = :genreName", Group.class);
        query.setParameter("genreName", genreName);
        return query.getResultList();
    }

    //отримання груп, які випустили альбом в певний рік
    public List<Group> findByAlbumReleaseYear(final int releaseYear) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager.createQuery(
                "SELECT DISTINCT aag.group FROM AlbumArtistGroup aag JOIN aag.album al WHERE al.releaseYear = :releaseYear", Group.class);
        query.setParameter("releaseYear", releaseYear);
        return query.getResultList();
    }
}


