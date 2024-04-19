package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDaoImpl extends MusicAlbumDaoImpl<Group> {

    public GroupDaoImpl() {
        setClazz(Group.class);
    }

    public Group findByName(final String groupName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager
                .createQuery("SELECT g FROM Group g WHERE g.groupName = :groupName", Group.class)
                .setParameter("groupName", groupName);
        return getSingleResult(query);
    }

    public List<Group> findByGenre(final String genreName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager
                .createQuery("SELECT DISTINCT aag.group FROM AlbumArtistGroup aag JOIN aag.album al JOIN al.songs s JOIN s.genre g WHERE g.name = :genreName", Group.class)
                .setParameter("genreName", genreName);
        return query.getResultList();
    }

    public List<Group> findByAlbumReleaseYear(final int releaseYear) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager
                .createQuery("SELECT DISTINCT aag.group FROM AlbumArtistGroup aag JOIN aag.album al WHERE al.releaseYear = :releaseYear", Group.class)
                .setParameter("releaseYear", releaseYear);
        return query.getResultList();
    }
}



