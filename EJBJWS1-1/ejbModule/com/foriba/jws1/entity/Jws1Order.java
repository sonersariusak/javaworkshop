package com.foriba.jws1.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the JWS1_ORDER database table.
 * 
 */
@Entity
@Table(name="JWS1_ORDER")
public class Jws1Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idx;

	@Column(name="ORDER_AMOUNT")
	private BigDecimal orderAmount;

    @Temporal( TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

    @Lob()
	@Column(name="ORDER_DETAIL")
	private String orderDetail;

    @Lob()
	@Column(name="ORDER_INVOICE")
	private byte[] orderInvoice;

	@Column(name="PRODUCT_NAME")
	private String productName;

    @Temporal( TemporalType.DATE)
	@Column(name="SYS_LAST_UPDATE")
	private Date sysLastUpdate;

	@Column(name="SYS_VERSION")
	private BigDecimal sysVersion;

    public Jws1Order() {
    }

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

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getSysLastUpdate() {
		return this.sysLastUpdate;
	}

	public void setSysLastUpdate(Date sysLastUpdate) {
		this.sysLastUpdate = sysLastUpdate;
	}

	public BigDecimal getSysVersion() {
		return this.sysVersion;
	}

	public void setSysVersion(BigDecimal sysVersion) {
		this.sysVersion = sysVersion;
	}

}