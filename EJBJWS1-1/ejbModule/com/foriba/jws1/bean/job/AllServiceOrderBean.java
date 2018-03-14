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
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

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
	public List<Jws1Order> searchOrderDate(String start, String end)
			throws Exception {
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(start);
		Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(end);
		return findByNamedQuery(Jws1Order.class, "findOrderBetweenTwoDate", 1,
				date1, date2);

	}

	@Override
	public String UpdateAdd(Jws1Order jws) throws Exception {
		merge(jws);
		return "update basarili";
	}

	@Override
	public List<Jws1Order> searchOrderID(long ID) throws Exception {
		return findByNamedQuery(Jws1Order.class, "findAccordingToID", 1, ID);
	}

	@Override
	public String OrderAddParameter(long idx, String pName, String orderDate,
			String orderArrivalDate, double amount, String clob, String blob)
			throws Exception {
		Jws1Order jws=new Jws1Order();
		jws.setIdx(idx);
		jws.setProductName(pName);
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
		jws.setOrderDate(date1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date date = simpleDateFormat.parse(orderArrivalDate);
		Timestamp timestamp = new Timestamp(date.getTime());
		jws.setOrderArrivalDate(timestamp);
		BigDecimal b=new BigDecimal(amount);
		jws.setOrderAmount(b.setScale(2,BigDecimal.ROUND_UP));
		jws.setOrderDetail(clob);
		String str = new String(DatatypeConverter.parseBase64Binary(blob));
		jws.setOrderInvoice(str.getBytes());
		persist(jws);
		return "Kayit Basarili";
	}


}
