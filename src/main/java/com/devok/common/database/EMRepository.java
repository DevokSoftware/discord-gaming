package com.devok.common.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EMRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected T find(Class<T> tClass, String id) {
        return entityManager.find(tClass, id);
    }

    protected T find(Class<T> tClass, Object obj) {
        return entityManager.find(tClass, obj);
    }

    protected List<T> findAll(String query){
        return entityManager.createQuery(query).getResultList();
    }

    protected T merge(T entity) {
        return entityManager.merge(entity);
    }

    protected T persist(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    protected void remove(T entity) {
        entityManager.remove(entity);
    }

    protected <T> TypedQuery<T> createNamedQuery(String str, Class<T> tClass) {
        return entityManager.createNamedQuery(str, tClass);
    }

    protected EntityManager getEntityManager(){
        return entityManager;
    }
}
