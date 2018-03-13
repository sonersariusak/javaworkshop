package com.foriba.jws1.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import com.foriba.jws1.base.BaseEntity;

@Local
@Remote
public interface DAOGeneric<T extends BaseEntity> extends Serializable {

	public void persist(T t) throws Exception;
	public T merge(T t) throws Exception;
	public void remove(T t) throws Exception;
	public T findByID(Class<T> t, Object key) throws Exception;
	public void lock(T t, LockModeType lockModeType) throws Exception;
	public void refresh(T t) throws Exception;
	public void flush() throws Exception;
	public int executeUpdate(String query, Object... params) throws Exception;
	public int executeNativeUpdate(String query) throws Exception;
	public <O>List<O> findByNamedQuery(Class<O> clazz, String namedQuery, int readSize, Object... params) throws Exception; 
	public <O>O findByNamedQuerySingle(Class<O> clazz, String namedQuery, Object... params) throws Exception;
	public <O>List<O> findByQuery(Class<O> clazz, String query, int readSize, Object... params) throws Exception;	
	public <O>O findByQuerySingle(Class<O> clazz, String query, Object... params) throws Exception;
	public Query createNativeQuery(String jpaQuery) throws Exception;
}
