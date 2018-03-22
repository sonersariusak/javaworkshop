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

	public String addOrder1(Jws1Order jws) throws Exception {

		persist(jws);
		return "Kayıt gerçekleşti";

	}

	@Override
	public List<Jws1Order> getFindByOrderProductName(String productName) throws Exception {
		if(null != productName) {
			return findByNamedQuery(Jws1Order.class, "Order.getFindByProductName", 1, productName);
		}
		else {
			return null;
		}


	}

	@Override
	public List<Jws1Order> getFindByBetweenTwoDate(Timestamp startDate, Timestamp endDate) throws Exception {

		DateUtil dt = new DateUtil();
		Timestamp ts = dt.toTimeStampFormat(startDate);
		Timestamp ts1 = dt.toTimeStampFormat(endDate);

		return findByNamedQuery(Jws1Order.class, "Order.getFindByOrderArrivalBetweenTwoDate", 5, ts, ts1);

	}

	@Override
	public String mergeOrder(Jws1Order jws) throws Exception {
		merge(jws);
		return "Merge basarili";
	}

	@Override
	public List<Jws1Order> getFindByID(long ID) throws Exception {
		// return findByID(Jws1Order.class, ID);
		return null;
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
	public String updateOrderSetAmountFromProductName(String pName, double amount) throws Exception {
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
		String date1 = dt.ToDateString(date);
		Date da = dt.toDateMID(date1);
		return findByNamedQuery(Jws1Order.class, "Order.getFindByOrderDateBiggerThan", 5, da);
	}

	@Override
	public List<Jws1Order> getFindByProductName(String productName) throws Exception {
		if(null != productName) {
			return findByNamedQuery(Jws1Order.class, "Order.getFindByProductNameLikeQuery", 5, productName);
		}
		else {
			return null;
		}

	}

}
