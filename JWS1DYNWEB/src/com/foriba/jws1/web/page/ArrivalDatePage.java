package com.foriba.jws1.web.page;

import java.sql.Timestamp;
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

public class ArrivalDatePage extends AbstractPage{

	private static final long serialVersionUID = 1L;

	private List<Jws1Order> orderList;
	private String searchTextForStartDate="2017-01-01";
	private String searchTextForEndDate="2018-12-12";



	public ArrivalDatePage() throws Exception {


	}

	public void searchByArrivalDate() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			Timestamp t1 = DateUtil.toTimeStampDate(searchTextForStartDate);
			Timestamp t2 = DateUtil.toTimeStampDate(searchTextForEndDate);
			orderList = service.getOrderListByOrderArrivalDate(t1, t2);
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

	public void setSearchTextForStartDate(String searchTextForStartDate) {
		this.searchTextForStartDate = searchTextForStartDate;
	}

	public String getSearchTextForStartDate() {
		return searchTextForStartDate;
	}

	public void setSearchTextForEndDate(String searchTextForEndDate) {
		this.searchTextForEndDate = searchTextForEndDate;
	}

	public String getSearchTextForEndDate() {
		return searchTextForEndDate;
	}
}
