
package foriba.com.jws1order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderedProductName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderDetail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderInvoice" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="orderAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "orderedProductName",
    "orderDetail",
    "orderInvoice",
    "orderAmount"
})
@XmlRootElement(name = "updateOrderRequest")
public class UpdateOrderRequest {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(required = true)
    protected String orderedProductName;
    @XmlElement(required = true)
    protected String orderDetail;
    @XmlElement(required = true)
    protected byte[] orderInvoice;
    protected float orderAmount;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
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

}
