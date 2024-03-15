package ua.edu.znu.musicalbum.service;

import javax.persistence.TypedQuery;
import java.util.List;

public interface MusicAlbumDao<T> {

    void setClazz(Class<T> clazzToSet);

    T findById(final long id);

    List<T> findAll();

    T getSingleResult(TypedQuery<T> query);

    List<T> getResultList(TypedQuery<T> query);

    void create(final T entity);

    void update(final T entity);

    void delete(final T entity);
}

