package com.foriba.jws1.web.page;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

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
	private Date orderDateText;
	private Date orderArrivalDateText;
	private String orderDetailText;
	private String orderInvoiceText;
	private float orderAmountText;
	private byte[] orderInvoiceData;
	private static final long serialVersionUID = 1L;
	private List<Jws1Order> orderList;
	private Jws1Order selectedOrder;
	private long searchTextForID = 0;
	private com.foriba.jws1.web.model.File uploadedFile;

	public OrderManagementPage() throws Exception {
		onLoad();
	}

	public String onLoad() throws Exception {

		orderList = new ArrayList<Jws1Order>();
		OrderService service = ServiceLocator.getCoreService(OrderService.class);
		orderList = service.getProductNameByOrderSort();
		System.err.println("Soner:  " + orderList.size());

		return "orderManagement";

	}

	public void openDetail() {
		try {
			orderList = new ArrayList<Jws1Order>();
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			orderList = service.getOrderByID(selectedOrder.getIdx());
			System.err.println("Soner" + orderList.size());
			setOrderProductNameText(orderList.get(0).getOrderedProductName());
			setOrderDateText(orderList.get(0).getOrderDate());
			setOrderArrivalDateText(orderList.get(0).getOrderArrivalDate());
			setOrderDetailText(orderList.get(0).getOrderDetail());
			setOrderAmountText(orderList.get(0).getOrderAmount().floatValue());
			String orderInvoice = DatatypeConverter.printBase64Binary(orderList.get(0).getOrderInvoice());
			setOrderInvoiceText(orderInvoice);


		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void uploadListener(UploadEvent uploadEvent) throws Exception {
		try {
			UploadItem item = uploadEvent.getUploadItem();
			byte[] data = FileUtils.readFileToByteArray(item.getFile());
			setOrderInvoiceData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			System.err.println("Soner: update ");
			OrderService service = ServiceLocator.getCoreService(OrderService.class);
			Timestamp t = new Timestamp(orderArrivalDateText.getTime());
			System.out.println("Soner : " + t);
			service.updateOrder(selectedOrder.getIdx(), orderProductNameText, t, orderDateText, orderAmountText, orderDetailText, orderInvoiceText);
			System.err.println("Soner: update 3");
			openDetail();
		}
		catch (Exception e) {
			e.printStackTrace();
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
		orderDateText = null;
		orderArrivalDateText = null;
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
			jws.setOrderDate(orderDateText);
			System.err.println("Soner :" + orderDateText);
			jws.setOrderAmount(amnt.setScale(2, BigDecimal.ROUND_UP));
			jws.setOrderDetail(orderDetailText);
			String str = new String(getOrderInvoiceData());
			jws.setOrderInvoice(str.getBytes());
			Timestamp t = new Timestamp(orderArrivalDateText.getTime());
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

	public void setOrderDateText(Date orderDateText) {
		this.orderDateText = orderDateText;
	}

	public Date getOrderDateText() {
		return orderDateText;
	}

	public void setOrderArrivalDateText(Date orderArrivalDateText) {
		this.orderArrivalDateText = orderArrivalDateText;
	}

	public Date getOrderArrivalDateText() {
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

	public void setUploadedFile(com.foriba.jws1.web.model.File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public com.foriba.jws1.web.model.File getUploadedFile() {
		return uploadedFile;
	}

	public void setOrderInvoiceData(byte[] orderInvoiceData) {
		this.orderInvoiceData = orderInvoiceData;
	}

	public byte[] getOrderInvoiceData() {
		return orderInvoiceData;
	}

}
