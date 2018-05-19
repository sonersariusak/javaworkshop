package com.foriba.jws1.web.page;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class AmountByArrivalDatePage extends AbstractPage implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Jws1Order> orderList;
	private double searchTextForAmount = 0;
	private String searchTextForStartDate;
	private String searchTextForEndDate;


	public AmountByArrivalDatePage() throws Exception {


	}

	public void searchAmountByArrivalDate() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			Timestamp t1 = DateUtil.toTimeStampDate(searchTextForStartDate);
			Timestamp t2 = DateUtil.toTimeStampDate(searchTextForEndDate);
			orderList = service.getOrderListByAmountByOrderArrivalDate(searchTextForAmount, t1, t2);
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

	public void setSearchTextForStartDate(String searchTextForDate) {
		this.searchTextForStartDate = searchTextForDate;
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
