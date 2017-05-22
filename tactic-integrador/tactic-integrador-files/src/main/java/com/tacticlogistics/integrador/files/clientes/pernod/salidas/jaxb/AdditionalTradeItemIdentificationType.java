//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AdditionalTradeItemIdentificationType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AdditionalTradeItemIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="additionalTradeItemIdentificationValue">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="additionalTradeItemIdentificationType" type="{urn:ean.ucc:2}AdditionalTradeItemIdentificationListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalTradeItemIdentificationType", propOrder = {
    "additionalTradeItemIdentificationValue",
    "additionalTradeItemIdentificationType"
})
public class AdditionalTradeItemIdentificationType {

    @XmlElement(required = true)
    protected String additionalTradeItemIdentificationValue;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AdditionalTradeItemIdentificationListType additionalTradeItemIdentificationType;

    /**
     * Obtiene el valor de la propiedad additionalTradeItemIdentificationValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalTradeItemIdentificationValue() {
        return additionalTradeItemIdentificationValue;
    }

    /**
     * Define el valor de la propiedad additionalTradeItemIdentificationValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalTradeItemIdentificationValue(String value) {
        this.additionalTradeItemIdentificationValue = value;
    }

    /**
     * Obtiene el valor de la propiedad additionalTradeItemIdentificationType.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalTradeItemIdentificationListType }
     *     
     */
    public AdditionalTradeItemIdentificationListType getAdditionalTradeItemIdentificationType() {
        return additionalTradeItemIdentificationType;
    }

    /**
     * Define el valor de la propiedad additionalTradeItemIdentificationType.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalTradeItemIdentificationListType }
     *     
     */
    public void setAdditionalTradeItemIdentificationType(AdditionalTradeItemIdentificationListType value) {
        this.additionalTradeItemIdentificationType = value;
    }

}
