package com.foriba.jws1.web.page;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.web.service.ServiceLocator;

/**
 * 
 * @author
 * 
 */

public class ProductNameByAmountPage extends AbstractPage implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Jws1Order> orderList;
	private double searchTextForAmount = 0;
	private String searchTextForProductName;

	public ProductNameByAmountPage() throws Exception {


	}

	public void searchByProductNameByAmount() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getOrderListByOrderedProductNameByAmount(searchTextForProductName,searchTextForAmount);
			System.err.println("Soner: " + orderList.size());

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

	public void setSearchTextForAmount(double searchTextForAmount) {
		this.searchTextForAmount = searchTextForAmount;
	}

	public double getSearchTextForAmount() {
		return searchTextForAmount;
	}


	public void setSearchTextForProductName(String searchTextForProductName) {
		this.searchTextForProductName = searchTextForProductName;
	}

	public String getSearchTextForProductName() {
		return searchTextForProductName;
	}

}
