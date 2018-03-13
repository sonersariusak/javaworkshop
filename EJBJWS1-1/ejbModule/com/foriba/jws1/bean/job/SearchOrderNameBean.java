package com.foriba.jws1.bean.job;

import java.util.List;

import javax.ejb.Stateless;

import com.foriba.jws1.base.BaseEntity;
import com.foriba.jws1.bean.ESGenericBean;
import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.SearchOrder;
@Stateless
public class SearchOrderNameBean extends ESGenericBean<BaseEntity> implements SearchOrder<BaseEntity>{

	@Override
	public List<Jws1Order> searchOrderProductName(String ProductName) throws Exception {
		
		return findByNamedQuery(Jws1Order.class, "findAccordingToProductName", 1, ProductName);	
		
	}
	
	

}
