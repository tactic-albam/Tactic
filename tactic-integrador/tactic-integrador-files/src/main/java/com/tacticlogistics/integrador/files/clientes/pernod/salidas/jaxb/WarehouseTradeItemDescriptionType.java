//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para WarehouseTradeItemDescriptionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WarehouseTradeItemDescriptionType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ean.ucc:2}LineItemType">
 *       &lt;sequence>
 *         &lt;element name="tradeItemIdentification" type="{urn:ean.ucc:2}TradeItemIdentificationType"/>
 *         &lt;element name="quantityRequested" type="{urn:ean.ucc:2}MeasurementValueType"/>
 *         &lt;element name="lotNumberOfItem" maxOccurs="unbounded">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WarehouseTradeItemDescriptionType", namespace = "urn:ean.ucc:deliver:2", propOrder = {
    "tradeItemIdentification",
    "quantityRequested",
    "lotNumberOfItem"
})
public class WarehouseTradeItemDescriptionType
    extends LineItemType
{

    @XmlElement(required = true)
    protected TradeItemIdentificationType tradeItemIdentification;
    @XmlElement(required = true)
    protected MeasurementValueType quantityRequested;
    @XmlElement(required = true)
    protected List<String> lotNumberOfItem;

    /**
     * Obtiene el valor de la propiedad tradeItemIdentification.
     * 
     * @return
     *     possible object is
     *     {@link TradeItemIdentificationType }
     *     
     */
    public TradeItemIdentificationType getTradeItemIdentification() {
        return tradeItemIdentification;
    }

    /**
     * Define el valor de la propiedad tradeItemIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link TradeItemIdentificationType }
     *     
     */
    public void setTradeItemIdentification(TradeItemIdentificationType value) {
        this.tradeItemIdentification = value;
    }

    /**
     * Obtiene el valor de la propiedad quantityRequested.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementValueType }
     *     
     */
    public MeasurementValueType getQuantityRequested() {
        return quantityRequested;
    }

    /**
     * Define el valor de la propiedad quantityRequested.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementValueType }
     *     
     */
    public void setQuantityRequested(MeasurementValueType value) {
        this.quantityRequested = value;
    }

    /**
     * Gets the value of the lotNumberOfItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lotNumberOfItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLotNumberOfItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLotNumberOfItem() {
        if (lotNumberOfItem == null) {
            lotNumberOfItem = new ArrayList<String>();
        }
        return this.lotNumberOfItem;
    }

}
