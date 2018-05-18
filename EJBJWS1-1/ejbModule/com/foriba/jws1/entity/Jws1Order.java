package com.foriba.jws1.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.foriba.jws1.base.BaseEntity;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the JWS1_ORDER database table.
 * 
 */
@Entity
@Table(name = "JWS1_ORDER")
@NamedQueries( {@NamedQuery(name = "Order.getOrderByID", query = "SELECT c FROM Jws1Order c where c.idx=?1"),
		//@NamedQuery(name = "Order.deleteOrder", query = "Delete c FROM Jws1Order c where c.idx = ?1"),
		@NamedQuery(name = "Order.getOrderListByOrderProductName", query = "SELECT c FROM Jws1Order c where c.orderedProductName = ?1"),
		@NamedQuery(name = "Order.getProductNameByOrderSort", query = "SELECT c FROM Jws1Order c order by c.idx desc"),
		@NamedQuery(name = "Order.getOrderListByOrderArrivalDate", query = "SELECT c FROM Jws1Order c where c.orderArrivalDate BETWEEN ?1 and ?2"),
		@NamedQuery(name = "Order.getOrderListByOrderedProductName", query = "SELECT c FROM Jws1Order c where c.orderedProductName LIKE ?1"),
		@NamedQuery(name = "Order.getOrderListByOrderDate", query = "SELECT c FROM Jws1Order c where c.orderDate > ?1"),
		@NamedQuery(name = "Order.getOrderListByOrderedProductNameByAmount", query = "SELECT c FROM Jws1Order c where c.orderedProductName LIKE ?1 and c.orderAmount<= ?2"),
		@NamedQuery(name = "Order.getOrderListByAmountByOrderArrivalDate", query = "SELECT c FROM Jws1Order c where c.orderAmount>?1 and c.orderArrivalDate BETWEEN ?2 and ?3")})
public class Jws1Order extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_GEN", sequenceName = "JWS1_IDX_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
	@Column(name = "IDX")
	private long idx;

	@Column(name = "ORDER_AMOUNT")
	private BigDecimal orderAmount;

	@Column(name = "ORDER_ARRIVAL_DATE")
	private Timestamp orderArrivalDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE")
	private Date orderDate;

	@Lob()
	@Column(name = "ORDER_DETAIL")
	private String orderDetail;

	@Lob()
	@Column(name = "ORDER_INVOICE")
	private byte[] orderInvoice;

	@Column(name = "ORDERED_PRODUCT_NAME")
	private String orderedProductName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SYS_LAST_UPDATE")
	private Date sysLastUpdate;

	@Version
	@Column(name="SYS_VERSION")
	private long sysVersion;

	public Jws1Order() {}

	public long getIdx() {
		return this.idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Timestamp getOrderArrivalDate() {
		return this.orderArrivalDate;
	}

	public void setOrderArrivalDate(Timestamp orderArrivalDate) {
		this.orderArrivalDate = orderArrivalDate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public byte[] getOrderInvoice() {
		return this.orderInvoice;
	}

	public void setOrderInvoice(byte[] orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

	public String getOrderedProductName() {
		return this.orderedProductName;
	}

	public void setOrderedProductName(String orderedProductName) {
		this.orderedProductName = orderedProductName;
	}
	
	public Date getSysLastUpdate() {
		return this.sysLastUpdate;
	}

	public void setSysLastUpdate(Date sysLastUpdate) {
		this.sysLastUpdate = sysLastUpdate;
	}

	public long getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(long sysVersion) {
		this.sysVersion = sysVersion;
	}

}
