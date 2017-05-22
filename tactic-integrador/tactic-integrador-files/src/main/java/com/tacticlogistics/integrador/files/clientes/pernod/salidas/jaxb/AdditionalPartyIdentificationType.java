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
 * <p>Clase Java para AdditionalPartyIdentificationType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AdditionalPartyIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="additionalPartyIdentificationValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="additionalPartyIdentificationType" type="{urn:ean.ucc:2}AdditionalPartyIdentificationListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalPartyIdentificationType", propOrder = {
    "additionalPartyIdentificationValue",
    "additionalPartyIdentificationType"
})
public class AdditionalPartyIdentificationType {

    @XmlElement(required = true)
    protected String additionalPartyIdentificationValue;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AdditionalPartyIdentificationListType additionalPartyIdentificationType;

    /**
     * Obtiene el valor de la propiedad additionalPartyIdentificationValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalPartyIdentificationValue() {
        return additionalPartyIdentificationValue;
    }

    /**
     * Define el valor de la propiedad additionalPartyIdentificationValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalPartyIdentificationValue(String value) {
        this.additionalPartyIdentificationValue = value;
    }

    /**
     * Obtiene el valor de la propiedad additionalPartyIdentificationType.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalPartyIdentificationListType }
     *     
     */
    public AdditionalPartyIdentificationListType getAdditionalPartyIdentificationType() {
        return additionalPartyIdentificationType;
    }

    /**
     * Define el valor de la propiedad additionalPartyIdentificationType.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalPartyIdentificationListType }
     *     
     */
    public void setAdditionalPartyIdentificationType(AdditionalPartyIdentificationListType value) {
        this.additionalPartyIdentificationType = value;
    }

}
