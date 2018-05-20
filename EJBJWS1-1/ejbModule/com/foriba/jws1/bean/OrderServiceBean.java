package com.foriba.jws1.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.stream.Entity;

@SuppressWarnings("serial")
@Stateless
public class OrderServiceBean extends ESGenericBean<BaseEntity> implements OrderService {

	/**
	 * In the getOrderListByOrderedProductName method query, LIKE is used for the getOrderListByOrderedProductName field.
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderListByOrderedProductName(String productName) throws Exception {
		return findByNamedQuery(Jws1Order.class, "Order.getOrderListByOrderedProductName", 60, "%" + productName + "%");

	}

	/**
	 * The getOrderListByOrderArrivalDate method retrieves the records between the start and end orderArrivalDate.
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderListByOrderArrivalDate(Timestamp startDate, Timestamp endDate) throws Exception {

		return findByNamedQuery(Jws1Order.class, "Order.getOrderListByOrderArrivalDate", 60, startDate, endDate);

	}

	/**
	 * The mergeOrder method updates if there is a matching record according to IDX, or adds it as a new record if it does not exist.
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public String mergeOrder(Jws1Order jws) throws Exception {
		merge(jws);
		return "The Merge process has been successful.";
	}

	/**
	 * The getOrderByID method searches by the ID entered.
	 * 
	 * @return record of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderByID(long ID) throws Exception {
		return findByNamedQuery(Jws1Order.class, "Order.getOrderByID", 1, ID);

	}

	/**
	 * The addOrder method adds a new record to the table.
	 * 
	 * @param
	 */
	@Override
	public String addOrder(Jws1Order jws1) throws Exception {
		persist(jws1);
		return "Succesfull";
	}

	/**
	 * The updateOrder method updates the table.
	 * 
	 * @param
	 */
	@Override
	public String updateOrder(long idx, String pName, double amount, String clob, String blob) throws Exception {
		String message = "";
		String str = null;
		BigDecimal b = new BigDecimal(amount);
		try {
			str = new String(DatatypeConverter.parseBase64Binary(blob));
		}
		catch (Exception e) {
			message = "The blob field must be Base64 encoded.";
			return message;
		}
		int count =
				executeUpdate("UPDATE Jws1Order c SET c.orderedProductName = ?2," + "c.orderAmount = ?3, c.orderDetail = ?4, c.orderInvoice = ?5 WHERE c.idx=?1", idx, pName, b.setScale(
						2,
						BigDecimal.ROUND_UP), clob, str.getBytes());
		message = "Update Succesful!, Number of records updated:" + count;

		return message;
	}

	/**
	 * updateAmountByOrderedProductName method by ProductName updates the order Product Name amount.
	 * 
	 * @param
	 */
	@Override
	public String updateAmountByOrderedProductName(String productName, double amount) throws Exception {
		String message = "";
		if(null == productName || "".equals(productName)) {
			message = "The Product Name field must be filled.";
		}
		else {
			BigDecimal b = new BigDecimal(amount);
			int count = executeUpdate("UPDATE Jws1Order c SET c.orderAmount= ?2 where c.orderedProductName = ?1", productName, b.setScale(2, BigDecimal.ROUND_UP));
			message = "Update Succesful!, Number of records updated:" + count;
		}
		return message;
	}

	/**
	 * The getOrderListByOrderDate method lists the records that are older than the entered date..
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderListByOrderDate(Date date) throws Exception {
		return findByNamedQuery(Jws1Order.class, "Order.getOrderListByOrderDate", 60, date);
	}

	/**
	 * The getOrderListByAmountByOrderArrivalDate method returns the data between the two dates entered and the lower of the entered amount.
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderListByAmountByOrderArrivalDate(double amount, Timestamp startDate, Timestamp endDate) throws Exception {
		BigDecimal b = new BigDecimal(amount);
		return findByNamedQuery(Jws1Order.class, "Order.getOrderListByAmountByOrderArrivalDate", 60, b.setScale(2, BigDecimal.ROUND_UP), startDate, endDate);

	}

	/**
	 * The getOrderListByOrderedProductNameByAmount method lists the entered orderedProductName and the smaller of the entered Amount. The orderedProductName field is used for LIKE.
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderListByOrderedProductNameByAmount(String productName, double amount) throws Exception {
		BigDecimal b = new BigDecimal(amount);
		return findByNamedQuery(Jws1Order.class, "Order.getOrderListByOrderedProductNameByAmount", 60, "%" + productName + "%", b.setScale(2, BigDecimal.ROUND_UP));

	}

	/**
	 * The getOrderListByOrderProductName method lists the records according to the entered orderedProductName.
	 * 
	 * @return records of JWS1_ORDER Table
	 */
	@Override
	public List<Jws1Order> getOrderListByOrderProductName(String orderedProductName) throws Exception {
		return findByNamedQuery(Jws1Order.class, "Order.getOrderListByOrderProductName", 60, orderedProductName);

	}

	@Override
	public String deleteOrder(long idx) throws Exception {
		Jws1Order jws = findByNamedQuery(Jws1Order.class, "Order.getOrderByID", 1, idx).get(0);
		remove(jws);
		return null;
	}


	@Override
	public List<Jws1Order> getProductNameByOrderSort() throws Exception {
		return findByNamedQuery(Jws1Order.class, "Order.getProductNameByOrderSort", 20);
	
	}


}
