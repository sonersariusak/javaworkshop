package com.foriba.jws1.web.page;

import java.io.Serializable;
import java.util.List;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.web.service.ServiceLocator;

/**
 * 
 * @author
 * 
 */

public class ProductNamePage extends AbstractPage implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Jws1Order> orderList;
	private String searchTextForProductName = "*";
	private Jws1Order currentParameter;

	public ProductNamePage() throws Exception {

	}

	public void searchByProductName() {
		// AdminLoggerService adminLoggerService = ServiceLocator.getCoreService(AdminLoggerService.class);
		try {
			if(null != orderList) {
				orderList.clear();
			}
			System.err.println("Soner:  " +searchTextForProductName);
			OrderService productNameService = ServiceLocator.getCoreService(OrderService.class);
			orderList = productNameService.getOrderListByOrderedProductName(searchTextForProductName);
			System.err.println("Soner:  " + orderList.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Jws1Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Jws1Order> orderList) {
		this.orderList = orderList;
	}

	public void setCurrentParameter(Jws1Order currentParameter) {
		this.currentParameter = currentParameter;
	}

	public Jws1Order getCurrentParameter() {
		return currentParameter;
	}

	public void setSearchTextForProductName(String searchTextForProductName) {
		this.searchTextForProductName = searchTextForProductName;
	}

	public String getSearchTextForProductName() {
		return searchTextForProductName;
	}

}
