package com.foriba.jws1.service;

import java.io.Serializable;
import java.util.List;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.entity.Jws1Order;

public interface AllServiceOrder{
	
	public String OrderAdd(Jws1Order jws) throws Exception;
	public List<Jws1Order> searchOrderProductName(String ProductName) throws Exception;

}
