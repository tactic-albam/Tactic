//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:03 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ManifestItem complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ManifestItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MimeTypeQualifierCode" type="{http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader}MimeTypeQualifier"/>
 *         &lt;element name="UniformResourceIdentifier" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LanguageCode" type="{http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader}Language" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManifestItem", propOrder = {
    "mimeTypeQualifierCode",
    "uniformResourceIdentifier",
    "description",
    "languageCode"
})
public class ManifestItem {

    @XmlElement(name = "MimeTypeQualifierCode", required = true)
    protected String mimeTypeQualifierCode;
    @XmlElement(name = "UniformResourceIdentifier", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uniformResourceIdentifier;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "LanguageCode")
    protected String languageCode;

    /**
     * Obtiene el valor de la propiedad mimeTypeQualifierCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeTypeQualifierCode() {
        return mimeTypeQualifierCode;
    }

    /**
     * Define el valor de la propiedad mimeTypeQualifierCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeTypeQualifierCode(String value) {
        this.mimeTypeQualifierCode = value;
    }

    /**
     * Obtiene el valor de la propiedad uniformResourceIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniformResourceIdentifier() {
        return uniformResourceIdentifier;
    }

    /**
     * Define el valor de la propiedad uniformResourceIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniformResourceIdentifier(String value) {
        this.uniformResourceIdentifier = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad languageCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Define el valor de la propiedad languageCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

}
