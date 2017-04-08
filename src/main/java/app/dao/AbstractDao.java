package app.dao;

import java.util.List;

public interface AbstractDao<E, K> {
    public List<E> getAll();
    public E getEntityById(K id);
    public E update(E entity);
    public void delete(K id);
    public E create(E entity);
}
