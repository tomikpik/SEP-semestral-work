package cz.fel.sep.persistence.DAO;


import cz.fel.sep.persistence.classes.IEntity;

import java.util.Collection;

public interface IGenericDao<T extends IEntity> {

    void create(T entity);

    void read(Integer id);

    void update(T entity);

    void delete(T entity);

    Collection<T> getAll();

    boolean exists(T entity);
}