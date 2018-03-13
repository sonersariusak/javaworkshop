package com.foriba.jws1.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;


import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.service.DAOGeneric;

/**
 * @author S. Gökhan Topçu
 * 
 * Generic DataAccessObject - Temel fonksiyonlari içerir
 *  
 */
@Stateless
public class DAOGenericBean<T extends BaseEntity> implements DAOGeneric<T> {


	private static final long serialVersionUID = 5243709247477437376L;
	private static final boolean FLUSH_ALL = false;
	
	@PersistenceContext(unitName="EINV_DS_EJB_10")
	private EntityManager em;
	
	/*
	@PostConstruct
	public void init() {
		em.setFlushMode(FlushModeType.COMMIT);
	}
	*/

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void persist(T t) throws Exception {
		t.setSysLastUpdate(new Date());
		em.persist(t);
		if(FLUSH_ALL){
			em.flush();
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public T merge(T t) throws Exception {
		t.setSysLastUpdate(new Date());
		t =  em.merge(t);
		if(FLUSH_ALL){
			em.flush();
		}
		return t;
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(T t) throws Exception {
		em.remove(t);
		if(FLUSH_ALL){
			em.flush();
		}
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T findByID(Class<T> t, Object key) throws Exception {
		Object o = em.find(t, key);
		if(o != null) {
			return (T)o;
		}
		return null;
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void lock(T t, LockModeType lockModeType) throws Exception {
		em.lock(t, lockModeType);
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void refresh(T t) throws Exception {
		em.refresh(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void flush() throws Exception {
		em.flush();
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public int executeUpdate(String query, Object... params) throws Exception {
		Query q = em.createQuery(query);
		setParams(q, params);
		return q.executeUpdate();
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public int executeNativeUpdate(String query) throws Exception {
		Query q = createNativeQuery(query);
		return q.executeUpdate();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <O>List<O> findByNamedQuery(Class<O> clazz, String namedQuery, int readSize, Object... params) throws Exception {
		return createNamedQuery(clazz, namedQuery, params).setMaxResults(readSize).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O>O findByNamedQuerySingle(Class<O> clazz, String namedQuery, Object... params) throws Exception {
		return (O)createNamedQuery(clazz, namedQuery, params).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O>List<O> findByQuery(Class<O> clazz, String jpaQuery, int readSize, Object... params) throws Exception {
		return createDynamicQuery(clazz, jpaQuery, params).setMaxResults(readSize).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O>O findByQuerySingle(Class<O> clazz, String jpaQuery, Object... params) throws Exception {
		return (O)createDynamicQuery(clazz, jpaQuery, params).getSingleResult();
	}
		
	private Query createNamedQuery(Class<?> clazz, String namedQuery, Object... params) throws Exception {
		Query query = em.createNamedQuery(namedQuery);
		setParams(query, params);
		return query;
	}
	
	private Query createDynamicQuery(Class<?> clazz, String jpaQuery, Object... params) throws Exception {
		Query query = em.createQuery(jpaQuery);
		setParams(query, params);
		return query;
	}
	
	public Query createNativeQuery(String jpaQuery) throws Exception {
		Query query = em.createNativeQuery(jpaQuery);
		return query;
	}

	private void setParams(Query query, Object... params) throws Exception {
		int index = 1;
		for (Object param : params) {
			if (param instanceof java.sql.Date) {
				query.setParameter(index++, (java.sql.Date) param, TemporalType.DATE);
			} else if (param instanceof java.sql.Timestamp) {
				query.setParameter(index++, (java.sql.Timestamp) param, TemporalType.TIMESTAMP);
			} else if (param instanceof java.util.Date) {
				query.setParameter(index++, (java.util.Date) param, TemporalType.TIMESTAMP);
			} else if (param instanceof java.util.Calendar) {
				query.setParameter(index++, (java.util.Calendar) param, TemporalType.TIMESTAMP);
			} else {
				query.setParameter(index++, param);
			}

		}
	}


}
