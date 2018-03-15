package com.foriba.jws1.bean.job;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.bean.ESGenericBean;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.AllServiceOrder;

@Stateless
public class AllServiceOrderBean extends ESGenericBean<BaseEntity> implements
		AllServiceOrder {

	public String OrderAdd(Jws1Order jws) throws Exception {

		persist(jws);
		return "kayit basarili";

	}

	@Override
	public List<Jws1Order> searchOrderProductName(String ProductName)
			throws Exception {

		return findByNamedQuery(Jws1Order.class, "findAccordingToProductName",
				1, ProductName);

	}

	@Override
	public List<Jws1Order> searchOrderDate(String startDate, String endDate)
			throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = simpleDateFormat.parse(startDate);
		Date date1 = simpleDateFormat.parse(endDate);
		return findByNamedQuery(Jws1Order.class, "findOrderBetweenTwoDate", 1,
				date, date1);

	}

	@Override
	public String MergeOrder(Jws1Order jws) throws Exception {
		merge(jws);
		return "Merge basarili";
	}

	@Override
	public List<Jws1Order> searchOrderID(long ID) throws Exception {
		return findByNamedQuery(Jws1Order.class, "findAccordingToID", 1, ID);
	}

	@Override
	public String OrderAddParameter(String pName, String orderDate,
			String orderArrivalDate, double amount, String clob, String blob)
			throws Exception {
		Jws1Order jws = new Jws1Order();
		jws.setProductName(pName);
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
		jws.setOrderDate(date1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS");
		Date date = simpleDateFormat.parse(orderArrivalDate);
		Timestamp timestamp = new Timestamp(date.getTime());
		jws.setOrderArrivalDate(timestamp);
		BigDecimal b = new BigDecimal(amount);
		jws.setOrderAmount(b.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDetail(clob);
		String str = new String(DatatypeConverter.parseBase64Binary(blob));
		jws.setOrderInvoice(str.getBytes());
		persist(jws);
		return "Kayit Basarili";
	}

	@Override
	public String OrderMerge(long idx, String pName, String orderDate,
			String orderArrivalDate, double amount, String clob, String blob)
			throws Exception {
		Jws1Order jws = new Jws1Order();
		jws.setIdx(idx);
		jws.setProductName(pName);
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
		jws.setOrderDate(date1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS");
		Date date = simpleDateFormat.parse(orderArrivalDate);
		Timestamp timestamp = new Timestamp(date.getTime());
		jws.setOrderArrivalDate(timestamp);
		BigDecimal b = new BigDecimal(amount);
		jws.setOrderAmount(b.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDetail(clob);
		String str = new String(DatatypeConverter.parseBase64Binary(blob));
		jws.setOrderInvoice(str.getBytes());
		merge(jws);
		return "update Basarili";
	}

	@Override
	public String UpdateOrder(long idx, String pName, double amount,
		String clob, String blob) throws Exception {
		BigDecimal b = new BigDecimal(amount);
		String str = new String(DatatypeConverter.parseBase64Binary(blob));
		executeUpdate(
				"UPDATE Jws1Order c SET c.productName = ?2,"
						+ "c.orderAmount = ?3, c.orderDetail = ?4, c.orderInvoice = ?5 WHERE c.idx=?1",
				idx, pName, b.setScale(2, BigDecimal.ROUND_UP), clob, str
						.getBytes());
		return "Update Başarılı";
	}

}
