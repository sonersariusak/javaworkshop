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
@Table(name="JWS1_ORDER")
@NamedQueries( {
		@NamedQuery(name = "findAccordingToProductName", query = "SELECT c FROM Jws1Order c where c.productName=?1") })
public class Jws1Order extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JWS1_ORDER_IDX_GENERATOR", sequenceName="JWS1_IDX_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JWS1_ORDER_IDX_GENERATOR")
	private long idx;

	@Column(name="ORDER_AMOUNT")
	private BigDecimal orderAmount;

	@Column(name="ORDER_ARRIVAL_DATE")
	private Timestamp orderArrivalDate;

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

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}