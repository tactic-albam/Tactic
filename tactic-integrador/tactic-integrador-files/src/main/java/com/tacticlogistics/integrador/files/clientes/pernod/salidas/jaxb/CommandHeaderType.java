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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CommandHeaderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CommandHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityIdentification" type="{urn:ean.ucc:2}EntityIdentificationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommandHeaderType", propOrder = {
    "entityIdentification"
})
@XmlSeeAlso({
    DocumentCommandHeaderType.class
})
public abstract class CommandHeaderType {

    @XmlElement(required = true)
    protected EntityIdentificationType entityIdentification;

    /**
     * Obtiene el valor de la propiedad entityIdentification.
     * 
     * @return
     *     possible object is
     *     {@link EntityIdentificationType }
     *     
     */
    public EntityIdentificationType getEntityIdentification() {
        return entityIdentification;
    }

    /**
     * Define el valor de la propiedad entityIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityIdentificationType }
     *     
     */
    public void setEntityIdentification(EntityIdentificationType value) {
        this.entityIdentification = value;
    }

}
