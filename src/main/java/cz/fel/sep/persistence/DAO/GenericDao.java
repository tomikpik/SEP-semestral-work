package cz.fel.sep.persistence.DAO;

import cz.fel.sep.persistence.classes.IEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;


@Transactional
public class GenericDao<T extends IEntity> implements IGenericDao<T>, Serializable {
    @Inject
    EntityManager em;

    protected Class<T> persistentClass;

    @Inject
    public GenericDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public void create(T entity) {
        em.persist(entity);
    }

    @Override
    public void read(Integer id) {
        em.find(persistentClass, id);
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public Collection<T> getAll() {
        CriteriaQuery q = em.getCriteriaBuilder().createQuery(persistentClass);
        Root<T> abstractRoot = q.from(persistentClass);
        q.select(abstractRoot);
        Collection<T> collection = em.createQuery(q).getResultList();
        return collection;
    }

    @Override
    public boolean exists(T entity) {
        return em.find(persistentClass, entity.getId()) != null;
    }

}