package com.foriba.jws1.service;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.foriba.jws1.base.BaseEntity;

@Local
@Remote
public interface ESGeneric<T extends BaseEntity> extends DAOGeneric<T> {
	
}