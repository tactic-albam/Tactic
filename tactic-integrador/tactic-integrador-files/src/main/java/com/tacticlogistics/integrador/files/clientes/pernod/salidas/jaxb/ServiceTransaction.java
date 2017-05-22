//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:03 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ServiceTransaction complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ServiceTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TypeOfServiceTransaction" type="{http://www.unece.org/cefact/namespaces/StandardBusinessDocumentHeader}TypeOfServiceTransaction" />
 *       &lt;attribute name="IsNonRepudiationRequired" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsAuthenticationRequired" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsNonRepudiationOfReceiptRequired" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsIntegrityCheckRequired" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsApplicationErrorResponseRequested" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeToAcknowledgeReceipt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeToAcknowledgeAcceptance" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeToPerform" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Recurrence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceTransaction")
public class ServiceTransaction {

    @XmlAttribute(name = "TypeOfServiceTransaction")
    protected TypeOfServiceTransaction typeOfServiceTransaction;
    @XmlAttribute(name = "IsNonRepudiationRequired")
    protected String isNonRepudiationRequired;
    @XmlAttribute(name = "IsAuthenticationRequired")
    protected String isAuthenticationRequired;
    @XmlAttribute(name = "IsNonRepudiationOfReceiptRequired")
    protected String isNonRepudiationOfReceiptRequired;
    @XmlAttribute(name = "IsIntegrityCheckRequired")
    protected String isIntegrityCheckRequired;
    @XmlAttribute(name = "IsApplicationErrorResponseRequested")
    protected String isApplicationErrorResponseRequested;
    @XmlAttribute(name = "TimeToAcknowledgeReceipt")
    protected String timeToAcknowledgeReceipt;
    @XmlAttribute(name = "TimeToAcknowledgeAcceptance")
    protected String timeToAcknowledgeAcceptance;
    @XmlAttribute(name = "TimeToPerform")
    protected String timeToPerform;
    @XmlAttribute(name = "Recurrence")
    protected String recurrence;

    /**
     * Obtiene el valor de la propiedad typeOfServiceTransaction.
     * 
     * @return
     *     possible object is
     *     {@link TypeOfServiceTransaction }
     *     
     */
    public TypeOfServiceTransaction getTypeOfServiceTransaction() {
        return typeOfServiceTransaction;
    }

    /**
     * Define el valor de la propiedad typeOfServiceTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeOfServiceTransaction }
     *     
     */
    public void setTypeOfServiceTransaction(TypeOfServiceTransaction value) {
        this.typeOfServiceTransaction = value;
    }

    /**
     * Obtiene el valor de la propiedad isNonRepudiationRequired.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsNonRepudiationRequired() {
        return isNonRepudiationRequired;
    }

    /**
     * Define el valor de la propiedad isNonRepudiationRequired.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsNonRepudiationRequired(String value) {
        this.isNonRepudiationRequired = value;
    }

    /**
     * Obtiene el valor de la propiedad isAuthenticationRequired.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAuthenticationRequired() {
        return isAuthenticationRequired;
    }

    /**
     * Define el valor de la propiedad isAuthenticationRequired.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAuthenticationRequired(String value) {
        this.isAuthenticationRequired = value;
    }

    /**
     * Obtiene el valor de la propiedad isNonRepudiationOfReceiptRequired.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsNonRepudiationOfReceiptRequired() {
        return isNonRepudiationOfReceiptRequired;
    }

    /**
     * Define el valor de la propiedad isNonRepudiationOfReceiptRequired.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsNonRepudiationOfReceiptRequired(String value) {
        this.isNonRepudiationOfReceiptRequired = value;
    }

    /**
     * Obtiene el valor de la propiedad isIntegrityCheckRequired.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsIntegrityCheckRequired() {
        return isIntegrityCheckRequired;
    }

    /**
     * Define el valor de la propiedad isIntegrityCheckRequired.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsIntegrityCheckRequired(String value) {
        this.isIntegrityCheckRequired = value;
    }

    /**
     * Obtiene el valor de la propiedad isApplicationErrorResponseRequested.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsApplicationErrorResponseRequested() {
        return isApplicationErrorResponseRequested;
    }

    /**
     * Define el valor de la propiedad isApplicationErrorResponseRequested.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsApplicationErrorResponseRequested(String value) {
        this.isApplicationErrorResponseRequested = value;
    }

    /**
     * Obtiene el valor de la propiedad timeToAcknowledgeReceipt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeToAcknowledgeReceipt() {
        return timeToAcknowledgeReceipt;
    }

    /**
     * Define el valor de la propiedad timeToAcknowledgeReceipt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeToAcknowledgeReceipt(String value) {
        this.timeToAcknowledgeReceipt = value;
    }

    /**
     * Obtiene el valor de la propiedad timeToAcknowledgeAcceptance.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeToAcknowledgeAcceptance() {
        return timeToAcknowledgeAcceptance;
    }

    /**
     * Define el valor de la propiedad timeToAcknowledgeAcceptance.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeToAcknowledgeAcceptance(String value) {
        this.timeToAcknowledgeAcceptance = value;
    }

    /**
     * Obtiene el valor de la propiedad timeToPerform.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeToPerform() {
        return timeToPerform;
    }

    /**
     * Define el valor de la propiedad timeToPerform.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeToPerform(String value) {
        this.timeToPerform = value;
    }

    /**
     * Obtiene el valor de la propiedad recurrence.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurrence() {
        return recurrence;
    }

    /**
     * Define el valor de la propiedad recurrence.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurrence(String value) {
        this.recurrence = value;
    }

}
