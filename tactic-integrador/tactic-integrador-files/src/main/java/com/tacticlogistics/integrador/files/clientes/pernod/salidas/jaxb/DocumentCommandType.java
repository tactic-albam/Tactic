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
 * <p>Clase Java para DocumentCommandType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentCommandType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ean.ucc:2}AbstractCommandType">
 *       &lt;sequence>
 *         &lt;element name="documentCommandHeader" type="{urn:ean.ucc:2}DocumentCommandHeaderType"/>
 *         &lt;element name="documentCommandOperand" type="{urn:ean.ucc:2}DocumentCommandOperandType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentCommandType", propOrder = {
    "documentCommandHeader",
    "documentCommandOperand"
})
public class DocumentCommandType
    extends AbstractCommandType
{

    @XmlElement(required = true)
    protected DocumentCommandHeaderType documentCommandHeader;
    @XmlElement(required = true)
    protected DocumentCommandOperandType documentCommandOperand;

    /**
     * Obtiene el valor de la propiedad documentCommandHeader.
     * 
     * @return
     *     possible object is
     *     {@link DocumentCommandHeaderType }
     *     
     */
    public DocumentCommandHeaderType getDocumentCommandHeader() {
        return documentCommandHeader;
    }

    /**
     * Define el valor de la propiedad documentCommandHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentCommandHeaderType }
     *     
     */
    public void setDocumentCommandHeader(DocumentCommandHeaderType value) {
        this.documentCommandHeader = value;
    }

    /**
     * Obtiene el valor de la propiedad documentCommandOperand.
     * 
     * @return
     *     possible object is
     *     {@link DocumentCommandOperandType }
     *     
     */
    public DocumentCommandOperandType getDocumentCommandOperand() {
        return documentCommandOperand;
    }

    /**
     * Define el valor de la propiedad documentCommandOperand.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentCommandOperandType }
     *     
     */
    public void setDocumentCommandOperand(DocumentCommandOperandType value) {
        this.documentCommandOperand = value;
    }

}
