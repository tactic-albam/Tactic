//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseIdentification" type="{urn:ean.ucc:2}EntityIdentificationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="responseStatus" use="required" type="{urn:ean.ucc:2}ResponseStatusListType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseType", propOrder = {
    "responseIdentification"
})
public abstract class ResponseType {

    @XmlElement(required = true)
    protected EntityIdentificationType responseIdentification;
    @XmlAttribute(name = "responseStatus", required = true)
    protected ResponseStatusListType responseStatus;

    /**
     * Obtiene el valor de la propiedad responseIdentification.
     * 
     * @return
     *     possible object is
     *     {@link EntityIdentificationType }
     *     
     */
    public EntityIdentificationType getResponseIdentification() {
        return responseIdentification;
    }

    /**
     * Define el valor de la propiedad responseIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityIdentificationType }
     *     
     */
    public void setResponseIdentification(EntityIdentificationType value) {
        this.responseIdentification = value;
    }

    /**
     * Obtiene el valor de la propiedad responseStatus.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusListType }
     *     
     */
    public ResponseStatusListType getResponseStatus() {
        return responseStatus;
    }

    /**
     * Define el valor de la propiedad responseStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusListType }
     *     
     */
    public void setResponseStatus(ResponseStatusListType value) {
        this.responseStatus = value;
    }

}
