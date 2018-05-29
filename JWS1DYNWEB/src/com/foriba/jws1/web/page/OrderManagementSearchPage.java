package com.foriba.jws1.web.page;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;
import com.foriba.jws1.web.service.ServiceLocator;


/**
 * 
 * @author
 * 
 */

public class OrderManagementSearchPage extends AbstractPage {

	private List<Jws1Order> orderList;
	private long searchTextForID = 0;
	private String searchTextForProductName = "*";
	private String searchTextForStartDate = "2017-01-01";
	private String searchTextForEndDate = "2018-12-12";
	private String searchTextForDate = "12/12/2017";
	private double searchTextForAmount = 0;
	private String selectedItem;
	private boolean showIdxPanel = false;
	private boolean showProductNamePanel = false;
	private boolean showArrivalDatePanel = false;
	private boolean showOrderDatePanel = false;
	private boolean showProductNameAmountPanel = false;
	private boolean showArrivalDateAmountPanel = false;


	private static final long serialVersionUID = 1L;

	public OrderManagementSearchPage() throws Exception {
		onLoad();
	}

	public String onLoad() throws Exception {

		return "searchPage";

	}

	public void selectionChanged(ValueChangeEvent e) {
		selectedItem = e.getNewValue().toString();
		if(selectedItem.equals("1")) {
			setShowIdxPanel(true);
			setShowProductNamePanel(false);
			setShowArrivalDatePanel(false);
			setShowOrderDatePanel(false);
			setShowProductNameAmountPanel(false);
			setShowArrivalDateAmountPanel(false);
			System.err.println("Soner:" + selectedItem);
		}
		else if(selectedItem.equals("2")) {
			setShowIdxPanel(false);
			setShowProductNamePanel(true);
			setShowArrivalDatePanel(false);
			setShowOrderDatePanel(false);
			setShowProductNameAmountPanel(false);
			setShowArrivalDateAmountPanel(false);
			System.err.println("Soner:" + selectedItem);
		}
		else if(selectedItem.equals("3")) {
			setShowIdxPanel(false);
			setShowProductNamePanel(false);
			setShowArrivalDatePanel(true);
			setShowOrderDatePanel(false);
			setShowProductNameAmountPanel(false);
			setShowArrivalDateAmountPanel(false);
			System.err.println("Soner:" + selectedItem);
		}
		else if(selectedItem.equals("4")) {
			setShowIdxPanel(false);
			setShowProductNamePanel(false);
			setShowArrivalDatePanel(false);
			setShowOrderDatePanel(true);
			setShowProductNameAmountPanel(false);
			setShowArrivalDateAmountPanel(false);
			System.err.println("Soner:" + selectedItem);
		}
		else if(selectedItem.equals("5")) {
			setShowIdxPanel(false);
			setShowProductNamePanel(false);
			setShowArrivalDatePanel(false);
			setShowOrderDatePanel(false);
			setShowProductNameAmountPanel(true);
			setShowArrivalDateAmountPanel(false);
			System.err.println("Soner:" + selectedItem);
		}
		else if(selectedItem.equals("6")) {
			setShowIdxPanel(false);
			setShowProductNamePanel(false);
			setShowArrivalDatePanel(false);
			setShowOrderDatePanel(false);
			setShowProductNameAmountPanel(false);
			setShowArrivalDateAmountPanel(true);
			System.err.println("Soner:" + selectedItem);
		}

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

	public void searchByProductName() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService productNameService = ServiceLocator.getCoreService(OrderService.class);
			orderList = productNameService.getOrderListByOrderedProductName(getSearchTextForProductName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByArrivalDate() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			Timestamp t1 = DateUtil.toTimeStampDate(searchTextForStartDate);
			Timestamp t2 = DateUtil.toTimeStampDate(searchTextForEndDate);
			orderList = service.getOrderListByOrderArrivalDate(t1, t2);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByDate() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getOrderListByOrderDate(DateUtil.toDate(searchTextForDate));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchByProductNameByAmount() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getOrderListByOrderedProductNameByAmount(searchTextForProductName,getSearchTextForAmount());
			System.err.println("Soner: " + orderList.size());

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void searchAmountByArrivalDate() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			Timestamp t1 = DateUtil.toTimeStampDate(searchTextForStartDate);
			Timestamp t2 = DateUtil.toTimeStampDate(searchTextForEndDate);
			orderList = service.getOrderListByAmountByOrderArrivalDate(searchTextForAmount, t1, t2);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() throws Exception {
		searchTextForAmount = 0;
		searchTextForDate = null;
		searchTextForEndDate =null;
		searchTextForID = 0;
		searchTextForProductName = "*";
		searchTextForStartDate = null;
		orderList.clear();
		onLoad();
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setShowIdxPanel(boolean showIdxPanel) {
		this.showIdxPanel = showIdxPanel;
	}

	public boolean isShowIdxPanel() {
		return showIdxPanel;
	}


	public void setShowProductNamePanel(boolean showProductNamePanel) {
		this.showProductNamePanel = showProductNamePanel;
	}

	public boolean isShowProductNamePanel() {
		return showProductNamePanel;
	}

	public void setShowArrivalDatePanel(boolean showArrivalDatePanel) {
		this.showArrivalDatePanel = showArrivalDatePanel;
	}

	public boolean isShowArrivalDatePanel() {
		return showArrivalDatePanel;
	}

	public void setShowOrderDatePanel(boolean showOrderDatePanel) {
		this.showOrderDatePanel = showOrderDatePanel;
	}

	public boolean isShowOrderDatePanel() {
		return showOrderDatePanel;
	}

	public void setShowProductNameAmountPanel(boolean showProductNameAmountPanel) {
		this.showProductNameAmountPanel = showProductNameAmountPanel;
	}

	public boolean isShowProductNameAmountPanel() {
		return showProductNameAmountPanel;
	}

	public void setShowArrivalDateAmountPanel(boolean showArrivalDateAmountPanel) {
		this.showArrivalDateAmountPanel = showArrivalDateAmountPanel;
	}

	public boolean isShowArrivalDateAmountPanel() {
		return showArrivalDateAmountPanel;
	}

	public void setSearchTextForID(long searchTextForID) {
		this.searchTextForID = searchTextForID;
	}

	public long getSearchTextForID() {
		return searchTextForID;
	}

	public void setOrderList(List<Jws1Order> orderList) {
		this.orderList = orderList;
	}

	public List<Jws1Order> getOrderList() {
		return orderList;
	}

	public void setSearchTextForProductName(String searchTextForProductName) {
		this.searchTextForProductName = searchTextForProductName;
	}

	public String getSearchTextForProductName() {
		return searchTextForProductName;
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

	public void setSearchTextForDate(String searchTextForDate) {
		this.searchTextForDate = searchTextForDate;
	}

	public String getSearchTextForDate() {
		return searchTextForDate;
	}

	public void setSearchTextForAmount(double searchTextForAmount) {
		this.searchTextForAmount = searchTextForAmount;
	}

	public double getSearchTextForAmount() {
		return searchTextForAmount;
	}
}
