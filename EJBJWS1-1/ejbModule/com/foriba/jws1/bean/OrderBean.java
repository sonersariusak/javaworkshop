package com.foriba.jws1.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;

@SuppressWarnings("serial")
@Stateless
public class OrderBean extends ESGenericBean<BaseEntity> implements OrderService {

	public static void main(String args[]) {


	}

	@Override
	public List<Jws1Order> getFindByOrderProductName(String productName) throws Exception {
		if(null != productName) {
			return findByNamedQuery(Jws1Order.class, "Order.getFindByOrderProductName", 10, "%"+productName+"%");
		}
		else {
			return null;
		}

	}

	@Override
	public List<Jws1Order> getFindByBetweenTwoDate(Timestamp startDate, Timestamp endDate) throws Exception {

		return findByNamedQuery(Jws1Order.class, "Order.getFindByOrderArrivalBetweenTwoDate", 10, startDate, endDate);

	}

	@Override
	public String mergeOrder(Jws1Order jws) throws Exception {
		merge(jws);
		return "Merge basarili";
	}

	@Override
	public List<Jws1Order> getFindByID(long ID) throws Exception {
		return findByNamedQuery(Jws1Order.class, "Order.getFindByID", 1, ID);

	}

	@Override
	public String addOrder(String pName, Date orderDate, Timestamp orderArrivalDate, double amount, String clob, String blob) throws Exception {
		String message = "";
		Jws1Order jws = new Jws1Order();
		try {
			if(null != pName) {
				jws.setProductName(pName);
			}
		}
		catch (Exception e) {
			return message = "Product Name alanı doldurulmalıdır.";
		}

		jws.setOrderDate(orderDate);
		jws.setOrderArrivalDate(orderArrivalDate);
		BigDecimal b = new BigDecimal(amount);
		jws.setOrderAmount(b.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDetail(clob);
		String str = "";
		try {
			str = new String(DatatypeConverter.parseBase64Binary(blob));
			jws.setOrderInvoice(str.getBytes());
		}
		catch (Exception e) {
			message = "blob alan Base64 encode olmalıdır!";
			return message;
		}

		persist(jws);
		message = "Kayit Basarili";
		return message;
	}

	@Override
	public String updateOrder(long idx, String pName, double amount, String clob, String blob) throws Exception {
		String message = "";
		String str = null;
		if(null == pName || "".equals(pName)) {
			message = "Product Name alanı doldurulmalıdır.";
		}
		else {
			BigDecimal b = new BigDecimal(amount);
			try {
				str = new String(DatatypeConverter.parseBase64Binary(blob));
			}
			catch (Exception e) {
				message = "blob alan Base64 encode olmalıdır.?";
				return message;
			}

			int count =
					executeUpdate("UPDATE Jws1Order c SET c.productName = ?2," + "c.orderAmount = ?3, c.orderDetail = ?4, c.orderInvoice = ?5 WHERE c.idx=?1", idx, pName, b.setScale(
							2,
							BigDecimal.ROUND_UP), clob, str.getBytes());
			message = "Update Başarılı" + ", Güncellenen kayıt sayısı:" + count;
		}
		return message;
	}

	@Override
	public String updateOrderByProductNameChangeAmount(String pName, double amount) throws Exception {
		String message = "";
		if(null == pName || "".equals(pName)) {
			message = "Product Name alanı doldurulmalıdır.";
		}
		else {
			BigDecimal b = new BigDecimal(amount);
			int count = executeUpdate("updateOrderProductNameToAmount", pName, b.setScale(2, BigDecimal.ROUND_UP));
			message = "Update Başarılı" + ", Güncellenen kayıt sayısı:" + count;
		}
		return message;
	}


	public List<Jws1Order> getFindByOrderDateBiggerThan(Date date) throws Exception {
		DateUtil dt = new DateUtil();
		String convertDate = dt.ToDateString(date);
		return findByNamedQuery(Jws1Order.class, "Order.getFindByDateBiggerThan", 10, convertDate);
	}

	@Override
	public List<Jws1Order> getFindByProductName(String productName) throws Exception {
		if(null != productName) {
			return findByQuery(Jws1Order.class, "Order.getFindByProductNameQuery", 10, productName);
		}
		else {
			return null;
		}

	}

	@Override
	public List<Jws1Order> getFindByAmountBiggerThanBetweenTwoDay(double amount, Timestamp startDate, Timestamp endDate) throws Exception {
		BigDecimal b = new BigDecimal(amount);
		return findByNamedQuery(Jws1Order.class, "Order.getFindByAmountBiggerThanBetweenTwoDay", 10,b.setScale(2, BigDecimal.ROUND_UP),startDate, endDate);
		
	}

}
