package com.foriba.jws1.bean.job;

import javax.ejb.Stateless;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.bean.ESGenericBean;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.AddOrder;

@Stateless
public class AddOrderBean extends ESGenericBean<BaseEntity> implements AddOrder {

	public String Add(String Name) throws Exception {
		Jws1Order jws = new Jws1Order();
		jws.setIdx(0);
		jws.setProductName(Name);
		jws.setSysVersion(0);
		ESGenericBean<BaseEntity> b = new ESGenericBean<BaseEntity>();
		b.persist(jws);
		return "kayıt başarılı";

	}

}
