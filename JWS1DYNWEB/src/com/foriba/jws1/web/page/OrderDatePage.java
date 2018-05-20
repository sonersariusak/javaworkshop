package com.foriba.jws1.web.page;

import java.util.ArrayList;
import java.util.List;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;
import com.foriba.jws1.web.service.ServiceLocator;

/**
 * 
 * @author
 * 
 */

public class OrderDatePage extends AbstractPage{

	private static final long serialVersionUID = 1L;

	private List<Jws1Order> orderList;
	private String searchTextForDate="12/12/2017";



	public OrderDatePage() throws Exception {


	}

	public void searchByDate() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);		
			orderList = service.getOrderListByOrderDate(DateUtil.toDate(searchTextForDate));
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

	public void setSearchTextForDate(String searchTextForDate) {
		this.searchTextForDate = searchTextForDate;
	}

	public String getSearchTextForDate() {
		return searchTextForDate;
	}
}
