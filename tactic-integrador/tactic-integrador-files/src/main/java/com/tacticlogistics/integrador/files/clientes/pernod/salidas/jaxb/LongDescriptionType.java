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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para LongDescriptionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LongDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="language" type="{urn:ean.ucc:2}ISO639CodeType"/>
 *         &lt;element name="longText">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LongDescriptionType", propOrder = {
    "language",
    "longText"
})
public class LongDescriptionType {

    @XmlElement(required = true)
    protected ISO639CodeType language;
    @XmlElement(required = true)
    protected String longText;

    /**
     * Obtiene el valor de la propiedad language.
     * 
     * @return
     *     possible object is
     *     {@link ISO639CodeType }
     *     
     */
    public ISO639CodeType getLanguage() {
        return language;
    }

    /**
     * Define el valor de la propiedad language.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO639CodeType }
     *     
     */
    public void setLanguage(ISO639CodeType value) {
        this.language = value;
    }

    /**
     * Obtiene el valor de la propiedad longText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongText() {
        return longText;
    }

    /**
     * Define el valor de la propiedad longText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongText(String value) {
        this.longText = value;
    }

}
