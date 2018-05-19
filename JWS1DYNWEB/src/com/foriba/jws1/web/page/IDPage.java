package com.foriba.jws1.web.page;

import java.io.Serializable;
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

public class IDPage extends AbstractPage implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Jws1Order> orderList;
	private long searchTextForID = 0;
	private Jws1Order currentParameter;


	public IDPage() throws Exception {


	}

	public void searchByID() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getOrderByID(getSearchTextForID());

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

	public void setSearchTextForID(long searchTextForID) {
		this.searchTextForID = searchTextForID;
	}

	public long getSearchTextForID() {
		return searchTextForID;
	}

}
