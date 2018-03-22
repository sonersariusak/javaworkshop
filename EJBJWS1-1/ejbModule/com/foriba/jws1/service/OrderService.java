package com.foriba.jws1.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.foriba.jws1.entity.Jws1Order;

@Local
public interface OrderService {
	public String addOrder1(Jws1Order jws) throws Exception;

	public String addOrder(String pName, String orderDate, String orderArrivalDate, double amount, String clob, String blob) throws Exception;

	public String mergeOrder(long idx, String pName, String orderDate, String orderArrivalDate, double amount, String clob, String blob) throws Exception;

	public String mergeOrder(Jws1Order jws) throws Exception;

	public String updateOrder(long idx, String pName, double amount, String clob, String blob) throws Exception;

	public String updateOrderSetAmountFromProductName(String pName, double amount) throws Exception;

	public List<Jws1Order> getFindByProductName(String ProductName) throws Exception;

	public List<Jws1Order> getFindByID(long ID) throws Exception;

	public List<Jws1Order> getFindByBetweenTwoDate(Timestamp startDate, Timestamp endDate) throws Exception;

	public List<Jws1Order> getFindByOrderDateBiggerThan(Date date) throws Exception;

	public List<Jws1Order> getFindByOrderProductName(String productName) throws Exception;

}
