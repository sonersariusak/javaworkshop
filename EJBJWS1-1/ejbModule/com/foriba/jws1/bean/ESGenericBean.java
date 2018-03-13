package com.foriba.jws1.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;


import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.service.DAOGeneric;
import com.foriba.jws1.service.ESGeneric;

/**
 * 
 * @author S. Gökhan Topçu
 * 
 * DAOGeneric ile özel yazma/sorgulama vb ihtiyaçları olmayan Entity'leri yöneten Entity Service
 *
 */
@Stateless
public class ESGenericBean<T extends BaseEntity> implements ESGeneric<T> {

	private static final long serialVersionUID = -9086454632044235740L;
	
	@EJB
	private DAOGeneric<T> genericDAO;
	

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void persist(T t) throws Exception  {
		try {
			genericDAO.persist(t);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public T merge(T t) throws Exception  {
		try {
			return genericDAO.merge(t);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void remove(T t) throws Exception  {
		try {
			genericDAO.remove(t);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T findByID(Class<T> cls, Object key) throws Exception  {
		try {
			return genericDAO.findByID(cls, key);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void lock(T t, LockModeType lockModeType) throws Exception {
		try {
			genericDAO.lock(t, lockModeType);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void refresh(T t) throws Exception {
		try {
			genericDAO.refresh(t);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void flush() throws Exception {
		try {
			genericDAO.flush();
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public int executeUpdate(String query, Object... params) throws Exception {
		try {
			return genericDAO.executeUpdate(query, params);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public int executeNativeUpdate(String query) throws Exception {
		try {
			return genericDAO.executeNativeUpdate(query);
		} catch (Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public <O>List<O> findByNamedQuery(Class<O> clazz, String namedQuery, int readSize, Object... params) throws Exception {
		try {
			return genericDAO.findByNamedQuery(clazz, namedQuery, readSize, params);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public <O>O findByNamedQuerySingle(Class<O> clazz, String namedQuery, Object... params) throws Exception {
		try {
			return genericDAO.findByNamedQuerySingle(clazz, namedQuery, params);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			if(tr instanceof javax.persistence.NoResultException) {
				return null;
			}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public <O>List<O> findByQuery(Class<O> clazz, String jpaQuery, int readSize, Object... params) throws Exception {
		try {
			return genericDAO.findByQuery(clazz, jpaQuery, readSize, params);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public <O>O findByQuerySingle(Class<O> clazz, String jpaQuery, Object... params) throws Exception {
		try {
			return genericDAO.findByQuerySingle(clazz, jpaQuery, params);
		}
		catch(Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			if(tr instanceof javax.persistence.NoResultException) {
				return null;
			}
			throw new Exception(tr);
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Query createNativeQuery(String jpaQuery) throws Exception {
		try {
			return genericDAO.createNativeQuery(jpaQuery);
		} catch (Exception e) {
			//Find root cause
			Throwable tr = null;
			for(tr = (e.getCause() == null ? e : e.getCause()); (tr != null && tr.getCause() != null); tr = tr.getCause()) {}
			throw new Exception(tr);
		}
	}


	
	
}
