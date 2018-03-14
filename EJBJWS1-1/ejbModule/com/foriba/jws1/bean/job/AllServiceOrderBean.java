package com.foriba.jws1.bean.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

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
	public List<Jws1Order> searchOrderDate(String start, String end)
			throws Exception {
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(start);
		Date lFromDate2 = datetimeFormatter1.parse(end);
		return findByNamedQuery(Jws1Order.class, "findOrderBetweenTwoDate", 1,
				lFromDate1, lFromDate2);

	}

	@Override
	public String UpdateAdd(Jws1Order jws) throws Exception {
		merge(jws);
		return "update basarili";
	}

}
