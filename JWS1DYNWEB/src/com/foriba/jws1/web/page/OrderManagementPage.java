package com.foriba.jws1.web.page;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.foriba.jws1.entity.Jws1Order;
import com.foriba.jws1.service.OrderService;
import com.foriba.jws1.util.DateUtil;
import com.foriba.jws1.web.service.ServiceLocator;


/**
 * 
 * @author
 * 
 */

public class OrderManagementPage extends AbstractPage {

	private long idx;
	private String orderProductNameText = null;
	private String orderDateText;
	private String orderArrivalDateText;
	private String orderDetailText;
	private String orderInvoiceText;
	private float orderAmountText;
	private static final long serialVersionUID = 1L;
	private List<Jws1Order> orderList;
	private Jws1Order selectedOrder;
	private long searchTextForID=0;

	public OrderManagementPage() throws Exception {
		onLoad();
	}

	public String onLoad() throws Exception {
		try {
			if(null != orderList) {
				orderList.clear();
			}
			System.err.println("Soner:  " + "APPLE");
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getProductNameByOrderSort();
			System.err.println("Soner:  " + orderList.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "orderManagement";

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
	public void delete() {
		try {
			System.out.println("soner Delete: ");
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			service.deleteOrder(selectedOrder.getIdx());
			System.out.println("soner Delete: ");
			onLoad();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refresh() throws Exception {
		orderProductNameText = "";
		orderDateText = "";
		orderArrivalDateText = "";
		orderDetailText = "";
		orderInvoiceText = "";
		orderAmountText = 0;
		onLoad();
	}

	public void addOrder() {
		try {
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			BigDecimal amnt = new BigDecimal(orderAmountText);
			Jws1Order jws = new Jws1Order();
			jws.setOrderedProductName(orderProductNameText);
			try {
				jws.setOrderDate(DateUtil.toDate(orderDateText));
				System.err.println("Soner :" + DateUtil.toDate(orderDateText));
			}
			catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jws.setOrderAmount(amnt.setScale(2, BigDecimal.ROUND_UP));
			jws.setOrderDetail(orderDetailText);
			String str = new String(DatatypeConverter.parseBase64Binary(orderInvoiceText));
			jws.setOrderInvoice(str.getBytes());
			Timestamp t = DateUtil.toTimeStampDate(orderArrivalDateText);
			jws.setOrderArrivalDate(t);
			try {
				service.addOrder(jws);
				onLoad();
			}
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setOrderProductNameText(String orderProductNameText) {
		this.orderProductNameText = orderProductNameText;
	}

	public String getOrderProductNameText() {
		return orderProductNameText;
	}

	public void setOrderDateText(String orderDateText) {
		this.orderDateText = orderDateText;
	}

	public String getOrderDateText() {
		return orderDateText;
	}

	public void setOrderArrivalDateText(String orderArrivalDateText) {
		this.orderArrivalDateText = orderArrivalDateText;
	}

	public String getOrderArrivalDateText() {
		return orderArrivalDateText;
	}

	public void setOrderDetailText(String orderDetailText) {
		this.orderDetailText = orderDetailText;
	}

	public String getOrderDetailText() {
		return orderDetailText;
	}

	public void setOrderInvoiceText(String orderInvoiceText) {
		this.orderInvoiceText = orderInvoiceText;
	}

	public String getOrderInvoiceText() {
		return orderInvoiceText;
	}

	public void setOrderAmountText(float orderAmountText) {
		this.orderAmountText = orderAmountText;
	}

	public float getOrderAmountText() {
		return orderAmountText;
	}

	public List<Jws1Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Jws1Order> orderList) {
		this.orderList = orderList;
	}

	public void setSelectedOrder(Jws1Order selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public Jws1Order getSelectedOrder() {
		return selectedOrder;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getIdx() {
		return idx;
	}

	public void setSearchTextForID(long searchTextForID) {
		this.searchTextForID = searchTextForID;
	}

	public long getSearchTextForID() {
		return searchTextForID;
	}

}
