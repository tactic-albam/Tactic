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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DocumentType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contentVersion" type="{urn:ean.ucc:2}VersionType" minOccurs="0"/>
 *         &lt;element name="documentStructureVersion" type="{urn:ean.ucc:2}VersionType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="lastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="creationDateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="documentStatus" use="required" type="{urn:ean.ucc:2}DocumentStatusListType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentType", propOrder = {
    "contentVersion",
    "documentStructureVersion"
})
@XmlSeeAlso({
    WarehouseShippingOrderType.class
})
public abstract class DocumentType {

    protected VersionType contentVersion;
    protected VersionType documentStructureVersion;
    @XmlAttribute(name = "lastUpdateDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastUpdateDate;
    @XmlAttribute(name = "creationDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDateTime;
    @XmlAttribute(name = "documentStatus", required = true)
    protected DocumentStatusListType documentStatus;

    /**
     * Obtiene el valor de la propiedad contentVersion.
     * 
     * @return
     *     possible object is
     *     {@link VersionType }
     *     
     */
    public VersionType getContentVersion() {
        return contentVersion;
    }

    /**
     * Define el valor de la propiedad contentVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionType }
     *     
     */
    public void setContentVersion(VersionType value) {
        this.contentVersion = value;
    }

    /**
     * Obtiene el valor de la propiedad documentStructureVersion.
     * 
     * @return
     *     possible object is
     *     {@link VersionType }
     *     
     */
    public VersionType getDocumentStructureVersion() {
        return documentStructureVersion;
    }

    /**
     * Define el valor de la propiedad documentStructureVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionType }
     *     
     */
    public void setDocumentStructureVersion(VersionType value) {
        this.documentStructureVersion = value;
    }

    /**
     * Obtiene el valor de la propiedad lastUpdateDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Define el valor de la propiedad lastUpdateDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateDate(XMLGregorianCalendar value) {
        this.lastUpdateDate = value;
    }

    /**
     * Obtiene el valor de la propiedad creationDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * Define el valor de la propiedad creationDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateTime(XMLGregorianCalendar value) {
        this.creationDateTime = value;
    }

    /**
     * Obtiene el valor de la propiedad documentStatus.
     * 
     * @return
     *     possible object is
     *     {@link DocumentStatusListType }
     *     
     */
    public DocumentStatusListType getDocumentStatus() {
        return documentStatus;
    }

    /**
     * Define el valor de la propiedad documentStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentStatusListType }
     *     
     */
    public void setDocumentStatus(DocumentStatusListType value) {
        this.documentStatus = value;
    }

}
