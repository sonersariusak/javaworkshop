package com.foriba.jws1.bean.job;

import java.util.List;

import javax.ejb.Stateless;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.bean.ESGenericBean;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.AllServiceOrder;

@Stateless
public class AllServiceOrderBean extends ESGenericBean<BaseEntity> implements AllServiceOrder {

	public String OrderAdd(String Name) throws Exception {
		Jws1Order jws = new Jws1Order();
		jws.setIdx(4);
		jws.setProductName(Name);
		jws.setSysVersion(0);
		persist(jws);
		return "kayit basarili";

	}
	
	@Override
	public List<Jws1Order> searchOrderProductName(String ProductName) throws Exception {
		
		return findByNamedQuery(Jws1Order.class, "findAccordingToProductName", 1, ProductName);	
		
	}

}
