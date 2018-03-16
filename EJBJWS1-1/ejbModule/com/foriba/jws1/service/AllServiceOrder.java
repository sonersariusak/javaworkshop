package com.foriba.jws1.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.entity.Jws1Order;

public interface AllServiceOrder {
	public String orderAdd(Jws1Order jws) throws Exception;

	public String orderAddParameter(String pName, String orderDate,
			String orderArrivalDate, double amount, String clob, String blob)
			throws Exception;

	public String orderMerge(long idx, String pName, String orderDate,
			String orderArrivalDate, double amount, String clob, String blob)
			throws Exception;

	public String mergeOrder(Jws1Order jws) throws Exception;

	public List<Jws1Order> searchOrderProductName(String ProductName)
			throws Exception;

	public List<Jws1Order> searchOrderID(long ID) throws Exception;

	public List<Jws1Order> searchOrderDate(String startDate, String endDate)
			throws Exception;

	public String updateOrder(long idx, String pName, double amount,
			String clob, String blob) throws Exception;

	public String updateOrderProductNameToAmount(String pName, double amount)
			throws Exception;
}
