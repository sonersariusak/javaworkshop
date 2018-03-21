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

@Stateless
public class OrderBean extends ESGenericBean<BaseEntity> implements OrderService {

	public static void main(String args[]){
		
		
	}
	public String addOrder1(Jws1Order jws) throws Exception {
		int abc=jws.getOrderDate().getYear();
		//persist(jws);
		return "abc"+abc;

	}

	@Override
	public List<Jws1Order> getFindByOrderProductName(String productName) throws Exception {
		if(null != productName) {
			return findByNamedQuery(Jws1Order.class, "getfindAccordingToProductName", 1, productName);
		}
		else {
			return null;
		}


	}

	@Override
	public List<Jws1Order> getFindByBetweenTwoDate(String startDate, String endDate) throws Exception {
		DateUtil dt = new DateUtil();
		String message = "";
		Timestamp tms = null;
		Timestamp tms1 = null;
		try {
			tms = dt.toTimeStampDate(startDate);
			tms1 = dt.toTimeStampDate(endDate);

		}
		catch (Exception e) {
			message = "Tarih ayarlarını kontrol ediniz! Timestamp Tarih ayarı yyyy-MM-dd hh:mm:ss.SSS formatında olmalıdır.";

		}

		return findByNamedQuery(Jws1Order.class, "getfindOrderArrivalBetweenTwoDate", 5, tms, tms1);

	}

	@Override
	public String mergeOrder(Jws1Order jws) throws Exception {
		merge(jws);
		return "Merge basarili";
	}

	@Override
	public List<Jws1Order> getFindByID(long ID) throws Exception {
		return findByNamedQuery(Jws1Order.class, "getfindAccordingToID", 1, ID);
	}

	@Override
	public String addOrder(String pName, String orderDate, String orderArrivalDate, double amount, String clob, String blob) throws Exception {
		DateUtil dt = new DateUtil();
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
		try {
			Date date1 = dt.toDate(orderDate);
			jws.setOrderDate(date1);
			try {
				Date date = dt.toDate(orderArrivalDate);
				Timestamp timestamp = new Timestamp(date.getTime());
				jws.setOrderArrivalDate(timestamp);
			}
			catch (Exception e) {
				message = "Tarih ayarlarını kontrol ediniz! Timestamp Tarih ayarı dd/MM/yyy formatında olmalıdır.";
				return message;
			}
		}
		catch (Exception e) {
			message = "Hata! Tarih ayarlarını kontrol ediniz! Tarih ayarı dd/MM/yyy formatında olmalıdır.";
			return message;
		}
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
	public String mergeOrder(long idx, String pName, String orderDate, String orderArrivalDate, double amount, String clob, String blob) throws Exception {
		DateUtil dt = new DateUtil();
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
		try {
			Date date1 = dt.toDate(orderDate);
			jws.setOrderDate(date1);
			try {
				Timestamp timestamp = dt.toTimeStampDate(orderArrivalDate);
				jws.setOrderArrivalDate(timestamp);
			}
			catch (Exception e) {
				message = "Tarih ayarlarını kontrol ediniz! Timestamp Tarih ayarı yyyy-MM-dd hh:mm:ss.SSS formatında olmalıdır.";
				return message;
			}
		}
		catch (Exception e) {
			message = "Hata! Tarih ayarlarını kontrol ediniz! Tarih ayarı dd/MM/yyy formatında olmalıdır.";
			return message;
		}
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

		merge(jws);
		message = "Merge Basarili";
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
	public String updateOrderProductNameToAmount(String pName, double amount) throws Exception {
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

	@Override
	public List<Jws1Order> getFindByOrderDateBiggerThan(String date) throws Exception {
		// TODO Auto-generated method stub
		DateUtil dt = new DateUtil();
		Date dateNew = dt.toDateShort(date);
		return findByNamedQuery(Jws1Order.class, "getOrderDateBiggerThan", 5, dateNew);
	}

	@Override
	public List<Jws1Order> getFindByProductName(String productName) throws Exception {
		if(null != productName) {
			return findByNamedQuery(Jws1Order.class, "getProductNameLikeQuery", 5, productName);
		}
		else {
			return null;
		}

	}

}
