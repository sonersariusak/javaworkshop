
package foriba.com.jws1order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Jws1OrderList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Jws1OrderList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="orderedProductName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="orderArrivalDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="orderDetail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sysVersion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sysLastUpdate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="orderInvoice" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Jws1OrderList", propOrder = {
    "id",
    "orderedProductName",
    "orderDate",
    "orderArrivalDate",
    "orderDetail",
    "orderAmount",
    "sysVersion",
    "sysLastUpdate",
    "orderInvoice"
})
public class Jws1OrderList {

    @XmlElement(name = "ID")
    protected long id;
    @XmlElement(required = true)
    protected String orderedProductName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orderDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar orderArrivalDate;
    @XmlElement(required = true)
    protected String orderDetail;
    protected float orderAmount;
    protected long sysVersion;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sysLastUpdate;
    @XmlElement(required = true)
    protected byte[] orderInvoice;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the orderedProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderedProductName() {
        return orderedProductName;
    }

    /**
     * Sets the value of the orderedProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedProductName(String value) {
        this.orderedProductName = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderDate(XMLGregorianCalendar value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the orderArrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderArrivalDate() {
        return orderArrivalDate;
    }

    /**
     * Sets the value of the orderArrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderArrivalDate(XMLGregorianCalendar value) {
        this.orderArrivalDate = value;
    }

    /**
     * Gets the value of the orderDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDetail() {
        return orderDetail;
    }

    /**
     * Sets the value of the orderDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDetail(String value) {
        this.orderDetail = value;
    }

    /**
     * Gets the value of the orderAmount property.
     * 
     */
    public float getOrderAmount() {
        return orderAmount;
    }

    /**
     * Sets the value of the orderAmount property.
     * 
     */
    public void setOrderAmount(float value) {
        this.orderAmount = value;
    }

    /**
     * Gets the value of the sysVersion property.
     * 
     */
    public long getSysVersion() {
        return sysVersion;
    }

    /**
     * Sets the value of the sysVersion property.
     * 
     */
    public void setSysVersion(long value) {
        this.sysVersion = value;
    }

    /**
     * Gets the value of the sysLastUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSysLastUpdate() {
        return sysLastUpdate;
    }

    /**
     * Sets the value of the sysLastUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSysLastUpdate(XMLGregorianCalendar value) {
        this.sysLastUpdate = value;
    }

    /**
     * Gets the value of the orderInvoice property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOrderInvoice() {
        return orderInvoice;
    }

    /**
     * Sets the value of the orderInvoice property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOrderInvoice(byte[] value) {
        this.orderInvoice = ((byte[]) value);
    }

}
