package com.gymmgtsystem.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepository.class);

    @Autowired
    private EntityManager em;
    
    @Transactional
    public <T> T persist(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }
    
    public <T> T findById(Class entity,Integer id) {
        Query query = em.createQuery("select o from "+entity.getSimpleName()+" o where o.id =:id ",entity).setParameter("id",id);
        try {
            return (T)query.getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    public <T> List<T> findByNativeQuery(String sql, Class<T> entity, Map<String,Object> params) {
        if(sql == null || sql.isEmpty()) {
            return new ArrayList<>();
        }
 //       printSQL(sql,params);
        Query query = em.createNativeQuery(sql,entity);
        if(params != null && params.size() >0) {
            params.forEach((k,v)->query.setParameter(k,v));
        }

        return query.getResultList();
    }
    
    public <T> T findSingleResultByNativeQuery(String sql,Class<T> entity,Map<String,Object> params) {
        if(sql == null || sql.isEmpty()) {
            return null;
        }
//        printSQL(sql,params);
        Query query = em.createNativeQuery(sql,entity);
        if(params != null && params.size() >0) {
            params.forEach((k,v)->query.setParameter(k,v));
        }
        query.setFirstResult(0).setMaxResults(1);
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
