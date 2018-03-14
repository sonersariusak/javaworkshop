package com.foriba.jws1.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.entity.Jws1Order;

public interface AllServiceOrder {
	public String OrderAdd(Jws1Order jws) throws Exception;

	public String OrderAddParameter(long idx, String pName, String orderDate,
			String orderArrivalDate, double amount, String clob, String blob)
			throws Exception;

	public String UpdateAdd(Jws1Order jws) throws Exception;

	public List<Jws1Order> searchOrderProductName(String ProductName)
			throws Exception;

	public List<Jws1Order> searchOrderID(long ID) throws Exception;

	public List<Jws1Order> searchOrderDate(String start, String end)
			throws Exception;

}
