package ua.edu.znu.musicalbum.service;

import ua.edu.znu.musicalbum.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDaoImpl extends MusicAlbumDaoImpl<Group> {

    public GroupDaoImpl() {
        setClazz(Group.class);
    }

    // Пошук групи за назвою
    public List<Group> findByGroupName(String groupName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Group> query = entityManager.createQuery(
                        "SELECT g FROM Group g WHERE g.groupName = :groupName", Group.class)
                .setParameter("groupName", groupName);
        return getResultList(query);
    }

    // Створення нової групи
    public void create(Group group) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Оновлення інформації про групу
    public void update(Group group) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    // Видалення групи
    public void delete(Group group) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(group) ? group : entityManager.merge(group));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

