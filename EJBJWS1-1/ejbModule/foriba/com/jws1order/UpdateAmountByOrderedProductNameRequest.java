
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
 *         &lt;element name="orderedProductName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "orderedProductName",
    "orderAmount"
})
@XmlRootElement(name = "updateAmountByOrderedProductNameRequest")
public class UpdateAmountByOrderedProductNameRequest {

    @XmlElement(required = true)
    protected String orderedProductName;
    protected float orderAmount;

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
