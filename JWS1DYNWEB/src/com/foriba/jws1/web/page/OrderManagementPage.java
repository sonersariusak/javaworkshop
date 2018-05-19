package com.foriba.jws1.web.page;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
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

public class OrderManagementPage extends AbstractPage{

	private String orderProductNameText = null;
	private Date orderDateText;
	private Timestamp orderArrivalDateText;
	private String orderDetailText;
	private Byte orderInvoiceText;
	private float orderAmountText;
	private static final long serialVersionUID = 1L;
	private List<Jws1Order> orderList;

	public OrderManagementPage() throws Exception {
		onLoad();
	}

	public String onLoad() throws Exception {
		try {
			if(null != orderList) {
				orderList.clear();
			}
			System.err.println("Soner:  " +"APPLE");
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getProductNameByOrderSort();
			System.err.println("Soner:  " + orderList.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "orderManagement";

	}

	public void addOrder() {
		try {
		System.err.println("Soner:  1");
		OrderService service = ServiceLocator.getCoreService(OrderService.class);
		System.err.println("Soner:  1");
		BigDecimal amnt = new BigDecimal(orderAmountText);
		Jws1Order jws = new Jws1Order();
		jws.setOrderedProductName(orderProductNameText);
		try {
			jws.setOrderDate(DateUtil.toDateString(orderDateText));
		}
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jws.setOrderAmount(amnt.setScale(2, BigDecimal.ROUND_UP));
		jws.setOrderDetail(orderDetailText);
		jws.setOrderInvoice(null);
		jws.setOrderArrivalDate(orderArrivalDateText);
		System.err.println("Soner:  2");
		try {
			service.addOrder(jws);
			System.err.println("Soner:  3");
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

	public void setOrderDateText(Date orderDateText) {
		this.orderDateText = orderDateText;
	}

	public Date getOrderDateText() {
		return orderDateText;
	}

	public void setOrderArrivalDateText(Timestamp orderArrivalDateText) {
		this.orderArrivalDateText = orderArrivalDateText;
	}

	public Timestamp getOrderArrivalDateText() {
		return orderArrivalDateText;
	}

	public void setOrderDetailText(String orderDetailText) {
		this.orderDetailText = orderDetailText;
	}

	public String getOrderDetailText() {
		return orderDetailText;
	}

	public void setOrderInvoiceText(Byte orderInvoiceText) {
		this.orderInvoiceText = orderInvoiceText;
	}

	public Byte getOrderInvoiceText() {
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

}
