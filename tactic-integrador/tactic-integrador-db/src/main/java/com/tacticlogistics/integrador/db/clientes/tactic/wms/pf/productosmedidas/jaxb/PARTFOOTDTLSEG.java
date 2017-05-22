//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.04.14 a las 02:21:51 PM COT 
//


package com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SEGNAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UOMCOD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UOMLVL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HGT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GRSWGT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NETWGT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PAL_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CAS_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PAK_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STK_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RCV_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UNTQTY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="THRESH_PCT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BULK_PCK_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "segnam",
    "uomcod",
    "uomlvl",
    "len",
    "wid",
    "hgt",
    "grswgt",
    "netwgt",
    "palflg",
    "casflg",
    "pakflg",
    "stkflg",
    "rcvflg",
    "untqty",
    "threshpct",
    "bulkpckflg"
})
@XmlRootElement(name = "PARTFOOT_DTL_SEG")
public class PARTFOOTDTLSEG {

    @XmlElement(name = "SEGNAM", required = true)
    protected String segnam;
    @XmlElement(name = "UOMCOD", required = true)
    protected String uomcod;
    @XmlElement(name = "UOMLVL", required = true)
    protected String uomlvl;
    @XmlElement(name = "LEN", required = true)
    protected String len;
    @XmlElement(name = "WID", required = true)
    protected String wid;
    @XmlElement(name = "HGT", required = true)
    protected String hgt;
    @XmlElement(name = "GRSWGT", required = true)
    protected String grswgt;
    @XmlElement(name = "NETWGT", required = true)
    protected String netwgt;
    @XmlElement(name = "PAL_FLG", required = true)
    protected String palflg;
    @XmlElement(name = "CAS_FLG", required = true)
    protected String casflg;
    @XmlElement(name = "PAK_FLG", required = true)
    protected String pakflg;
    @XmlElement(name = "STK_FLG", required = true)
    protected String stkflg;
    @XmlElement(name = "RCV_FLG", required = true)
    protected String rcvflg;
    @XmlElement(name = "UNTQTY", required = true)
    protected String untqty;
    @XmlElement(name = "THRESH_PCT", required = true)
    protected String threshpct;
    @XmlElement(name = "BULK_PCK_FLG", required = true)
    protected String bulkpckflg;

    /**
     * Obtiene el valor de la propiedad segnam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEGNAM() {
        return segnam;
    }

    /**
     * Define el valor de la propiedad segnam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEGNAM(String value) {
        this.segnam = value;
    }

    /**
     * Obtiene el valor de la propiedad uomcod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOMCOD() {
        return uomcod;
    }

    /**
     * Define el valor de la propiedad uomcod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOMCOD(String value) {
        this.uomcod = value;
    }

    /**
     * Obtiene el valor de la propiedad uomlvl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOMLVL() {
        return uomlvl;
    }

    /**
     * Define el valor de la propiedad uomlvl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOMLVL(String value) {
        this.uomlvl = value;
    }

    /**
     * Obtiene el valor de la propiedad len.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLEN() {
        return len;
    }

    /**
     * Define el valor de la propiedad len.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLEN(String value) {
        this.len = value;
    }

    /**
     * Obtiene el valor de la propiedad wid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWID() {
        return wid;
    }

    /**
     * Define el valor de la propiedad wid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWID(String value) {
        this.wid = value;
    }

    /**
     * Obtiene el valor de la propiedad hgt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHGT() {
        return hgt;
    }

    /**
     * Define el valor de la propiedad hgt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHGT(String value) {
        this.hgt = value;
    }

    /**
     * Obtiene el valor de la propiedad grswgt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRSWGT() {
        return grswgt;
    }

    /**
     * Define el valor de la propiedad grswgt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRSWGT(String value) {
        this.grswgt = value;
    }

    /**
     * Obtiene el valor de la propiedad netwgt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNETWGT() {
        return netwgt;
    }

    /**
     * Define el valor de la propiedad netwgt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNETWGT(String value) {
        this.netwgt = value;
    }

    /**
     * Obtiene el valor de la propiedad palflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPALFLG() {
        return palflg;
    }

    /**
     * Define el valor de la propiedad palflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPALFLG(String value) {
        this.palflg = value;
    }

    /**
     * Obtiene el valor de la propiedad casflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCASFLG() {
        return casflg;
    }

    /**
     * Define el valor de la propiedad casflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCASFLG(String value) {
        this.casflg = value;
    }

    /**
     * Obtiene el valor de la propiedad pakflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAKFLG() {
        return pakflg;
    }

    /**
     * Define el valor de la propiedad pakflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAKFLG(String value) {
        this.pakflg = value;
    }

    /**
     * Obtiene el valor de la propiedad stkflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTKFLG() {
        return stkflg;
    }

    /**
     * Define el valor de la propiedad stkflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTKFLG(String value) {
        this.stkflg = value;
    }

    /**
     * Obtiene el valor de la propiedad rcvflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCVFLG() {
        return rcvflg;
    }

    /**
     * Define el valor de la propiedad rcvflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCVFLG(String value) {
        this.rcvflg = value;
    }

    /**
     * Obtiene el valor de la propiedad untqty.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNTQTY() {
        return untqty;
    }

    /**
     * Define el valor de la propiedad untqty.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNTQTY(String value) {
        this.untqty = value;
    }

    /**
     * Obtiene el valor de la propiedad threshpct.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTHRESHPCT() {
        return threshpct;
    }

    /**
     * Define el valor de la propiedad threshpct.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTHRESHPCT(String value) {
        this.threshpct = value;
    }

    /**
     * Obtiene el valor de la propiedad bulkpckflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBULKPCKFLG() {
        return bulkpckflg;
    }

    /**
     * Define el valor de la propiedad bulkpckflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBULKPCKFLG(String value) {
        this.bulkpckflg = value;
    }

}
