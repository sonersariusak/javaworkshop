package com.foriba.jws1.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

import com.foriba.jws1.entity.Jws1Order;

@Remote
@Local
public interface OrderService {

	public String addOrder(Jws1Order jws1) throws Exception;

	public String mergeOrder(Jws1Order jws) throws Exception;

	public String updateOrder(long idx, String pName, double amount, String clob, String blob) throws Exception;

	public String updateAmountByOrderedProductName(String pName, double amount) throws Exception;

	public List<Jws1Order> getByOrderProductName(String orderProductName) throws Exception;

	public List<Jws1Order> getByID(long ID) throws Exception;

	public List<Jws1Order> getByOrderArrivalDate(Timestamp startDate, Timestamp endDate) throws Exception;

	public List<Jws1Order> getByOrderDate(Date date) throws Exception;

	public List<Jws1Order> getByOrderedProductName(String orderedProductName) throws Exception;

	public List<Jws1Order> getByOrderedProductNameByAmount(String productName, double amount) throws Exception;

	public List<Jws1Order> getByAmountByOrderArrivalDate(double amount, Timestamp startDate, Timestamp endDate) throws Exception;

}
