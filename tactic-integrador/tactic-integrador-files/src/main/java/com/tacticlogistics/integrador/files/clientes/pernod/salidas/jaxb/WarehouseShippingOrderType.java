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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para WarehouseShippingOrderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WarehouseShippingOrderType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ean.ucc:2}DocumentType">
 *       &lt;sequence>
 *         &lt;element name="warehouseShippingOrderIdentification" type="{urn:ean.ucc:2}EntityIdentificationType"/>
 *         &lt;element name="supplierOrderIdentification" type="{urn:ean.ucc:2}EntityIdentificationType"/>
 *         &lt;element name="purchaseOrderIdentification" type="{urn:ean.ucc:2}EntityIdentificationType" minOccurs="0"/>
 *         &lt;element name="shipTo" type="{urn:ean.ucc:2}PartyIdentificationType"/>
 *         &lt;element name="carrier" type="{urn:ean.ucc:2}PartyIdentificationType" minOccurs="0"/>
 *         &lt;element name="warehouseShippingOrderDate" type="{urn:ean.ucc:deliver:2}WarehouseShippingOrderDateType" maxOccurs="2"/>
 *         &lt;element name="shipmentMethodOfPayment" type="{urn:ean.ucc:deliver:2}WarehousePaymentMethodCodeListType"/>
 *         &lt;element name="transportationMethodType" type="{urn:ean.ucc:2}TransportationMethodTypeCodeListType"/>
 *         &lt;element name="routingGuide" type="{urn:ean.ucc:2}DescriptionType" minOccurs="0"/>
 *         &lt;element name="warehouseTradeItemDescription" type="{urn:ean.ucc:deliver:2}WarehouseTradeItemDescriptionType" maxOccurs="unbounded"/>
 *         &lt;element name="extension" type="{urn:ean.ucc:2}ExtensionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WarehouseShippingOrderType", namespace = "urn:ean.ucc:deliver:2", propOrder = {
    "warehouseShippingOrderIdentification",
    "supplierOrderIdentification",
    "purchaseOrderIdentification",
    "shipTo",
    "carrier",
    "warehouseShippingOrderDate",
    "shipmentMethodOfPayment",
    "transportationMethodType",
    "routingGuide",
    "warehouseTradeItemDescription",
    "extension"
})
public class WarehouseShippingOrderType
    extends DocumentType
{

    @XmlElement(required = true)
    protected EntityIdentificationType warehouseShippingOrderIdentification;
    @XmlElement(required = true)
    protected EntityIdentificationType supplierOrderIdentification;
    protected EntityIdentificationType purchaseOrderIdentification;
    @XmlElement(required = true)
    protected PartyIdentificationType shipTo;
    protected PartyIdentificationType carrier;
    @XmlElement(required = true)
    protected List<WarehouseShippingOrderDateType> warehouseShippingOrderDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected WarehousePaymentMethodCodeListType shipmentMethodOfPayment;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TransportationMethodTypeCodeListType transportationMethodType;
    protected DescriptionType routingGuide;
    @XmlElement(required = true)
    protected List<WarehouseTradeItemDescriptionType> warehouseTradeItemDescription;
    protected ExtensionType extension;

    /**
     * Obtiene el valor de la propiedad warehouseShippingOrderIdentification.
     * 
     * @return
     *     possible object is
     *     {@link EntityIdentificationType }
     *     
     */
    public EntityIdentificationType getWarehouseShippingOrderIdentification() {
        return warehouseShippingOrderIdentification;
    }

    /**
     * Define el valor de la propiedad warehouseShippingOrderIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityIdentificationType }
     *     
     */
    public void setWarehouseShippingOrderIdentification(EntityIdentificationType value) {
        this.warehouseShippingOrderIdentification = value;
    }

    /**
     * Obtiene el valor de la propiedad supplierOrderIdentification.
     * 
     * @return
     *     possible object is
     *     {@link EntityIdentificationType }
     *     
     */
    public EntityIdentificationType getSupplierOrderIdentification() {
        return supplierOrderIdentification;
    }

    /**
     * Define el valor de la propiedad supplierOrderIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityIdentificationType }
     *     
     */
    public void setSupplierOrderIdentification(EntityIdentificationType value) {
        this.supplierOrderIdentification = value;
    }

    /**
     * Obtiene el valor de la propiedad purchaseOrderIdentification.
     * 
     * @return
     *     possible object is
     *     {@link EntityIdentificationType }
     *     
     */
    public EntityIdentificationType getPurchaseOrderIdentification() {
        return purchaseOrderIdentification;
    }

    /**
     * Define el valor de la propiedad purchaseOrderIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityIdentificationType }
     *     
     */
    public void setPurchaseOrderIdentification(EntityIdentificationType value) {
        this.purchaseOrderIdentification = value;
    }

    /**
     * Obtiene el valor de la propiedad shipTo.
     * 
     * @return
     *     possible object is
     *     {@link PartyIdentificationType }
     *     
     */
    public PartyIdentificationType getShipTo() {
        return shipTo;
    }

    /**
     * Define el valor de la propiedad shipTo.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyIdentificationType }
     *     
     */
    public void setShipTo(PartyIdentificationType value) {
        this.shipTo = value;
    }

    /**
     * Obtiene el valor de la propiedad carrier.
     * 
     * @return
     *     possible object is
     *     {@link PartyIdentificationType }
     *     
     */
    public PartyIdentificationType getCarrier() {
        return carrier;
    }

    /**
     * Define el valor de la propiedad carrier.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyIdentificationType }
     *     
     */
    public void setCarrier(PartyIdentificationType value) {
        this.carrier = value;
    }

    /**
     * Gets the value of the warehouseShippingOrderDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warehouseShippingOrderDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarehouseShippingOrderDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WarehouseShippingOrderDateType }
     * 
     * 
     */
    public List<WarehouseShippingOrderDateType> getWarehouseShippingOrderDate() {
        if (warehouseShippingOrderDate == null) {
            warehouseShippingOrderDate = new ArrayList<WarehouseShippingOrderDateType>();
        }
        return this.warehouseShippingOrderDate;
    }

    /**
     * Obtiene el valor de la propiedad shipmentMethodOfPayment.
     * 
     * @return
     *     possible object is
     *     {@link WarehousePaymentMethodCodeListType }
     *     
     */
    public WarehousePaymentMethodCodeListType getShipmentMethodOfPayment() {
        return shipmentMethodOfPayment;
    }

    /**
     * Define el valor de la propiedad shipmentMethodOfPayment.
     * 
     * @param value
     *     allowed object is
     *     {@link WarehousePaymentMethodCodeListType }
     *     
     */
    public void setShipmentMethodOfPayment(WarehousePaymentMethodCodeListType value) {
        this.shipmentMethodOfPayment = value;
    }

    /**
     * Obtiene el valor de la propiedad transportationMethodType.
     * 
     * @return
     *     possible object is
     *     {@link TransportationMethodTypeCodeListType }
     *     
     */
    public TransportationMethodTypeCodeListType getTransportationMethodType() {
        return transportationMethodType;
    }

    /**
     * Define el valor de la propiedad transportationMethodType.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportationMethodTypeCodeListType }
     *     
     */
    public void setTransportationMethodType(TransportationMethodTypeCodeListType value) {
        this.transportationMethodType = value;
    }

    /**
     * Obtiene el valor de la propiedad routingGuide.
     * 
     * @return
     *     possible object is
     *     {@link DescriptionType }
     *     
     */
    public DescriptionType getRoutingGuide() {
        return routingGuide;
    }

    /**
     * Define el valor de la propiedad routingGuide.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptionType }
     *     
     */
    public void setRoutingGuide(DescriptionType value) {
        this.routingGuide = value;
    }

    /**
     * Gets the value of the warehouseTradeItemDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the warehouseTradeItemDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarehouseTradeItemDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WarehouseTradeItemDescriptionType }
     * 
     * 
     */
    public List<WarehouseTradeItemDescriptionType> getWarehouseTradeItemDescription() {
        if (warehouseTradeItemDescription == null) {
            warehouseTradeItemDescription = new ArrayList<WarehouseTradeItemDescriptionType>();
        }
        return this.warehouseTradeItemDescription;
    }

    /**
     * Obtiene el valor de la propiedad extension.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Define el valor de la propiedad extension.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

}
