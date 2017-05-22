//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TypedEntityIdentificationType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TypedEntityIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityIdentification" type="{urn:ean.ucc:2}EntityIdentificationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="entityType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *             &lt;totalDigits value="4"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypedEntityIdentificationType", propOrder = {
    "entityIdentification"
})
public class TypedEntityIdentificationType {

    @XmlElement(required = true)
    protected EntityIdentificationType entityIdentification;
    @XmlAttribute(name = "entityType", required = true)
    protected BigInteger entityType;

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

    /**
     * Obtiene el valor de la propiedad entityType.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEntityType() {
        return entityType;
    }

    /**
     * Define el valor de la propiedad entityType.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEntityType(BigInteger value) {
        this.entityType = value;
    }

}
