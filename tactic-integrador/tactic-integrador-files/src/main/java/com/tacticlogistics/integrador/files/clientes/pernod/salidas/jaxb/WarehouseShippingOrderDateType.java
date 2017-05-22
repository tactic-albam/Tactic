//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para WarehouseShippingOrderDateType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="WarehouseShippingOrderDateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="deliverByDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="shipByDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WarehouseShippingOrderDateType", namespace = "urn:ean.ucc:deliver:2", propOrder = {
    "deliverByDate",
    "shipByDate"
})
public class WarehouseShippingOrderDateType {

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deliverByDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar shipByDate;

    /**
     * Obtiene el valor de la propiedad deliverByDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliverByDate() {
        return deliverByDate;
    }

    /**
     * Define el valor de la propiedad deliverByDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliverByDate(XMLGregorianCalendar value) {
        this.deliverByDate = value;
    }

    /**
     * Obtiene el valor de la propiedad shipByDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getShipByDate() {
        return shipByDate;
    }

    /**
     * Define el valor de la propiedad shipByDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setShipByDate(XMLGregorianCalendar value) {
        this.shipByDate = value;
    }

}
