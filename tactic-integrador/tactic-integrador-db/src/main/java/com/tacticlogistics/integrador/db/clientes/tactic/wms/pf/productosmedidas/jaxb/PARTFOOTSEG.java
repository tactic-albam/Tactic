//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.04.14 a las 02:21:51 PM COT 
//


package com.tacticlogistics.integrador.db.clientes.tactic.wms.pf.productosmedidas.jaxb;

import java.util.ArrayList;
import java.util.List;

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
 *         &lt;element name="TRNTYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRTNUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FTPCOD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRT_CLIENT_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LNGDSC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SHORT_DSC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOCALE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CASLVL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NSTLEN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NSTLEN_MU" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NSTWID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NSTHGT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PAL_STCK_HGT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DEF_ASSET_TYP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DEFFTP_FLG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOAD_ATTR1_CFG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOAD_ATTR2_CFG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOAD_ATTR3_CFG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOAD_ATTR4_CFG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LOAD_ATTR5_CFG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STKMTD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}PARTFOOT_DTL_SEG" maxOccurs="unbounded"/>
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
    "trntyp",
    "prtnum",
    "ftpcod",
    "prtclientid",
    "lngdsc",
    "shortdsc",
    "localeid",
    "caslvl",
    "nstlen",
    "nstlenmu",
    "nstwid",
    "nsthgt",
    "palstckhgt",
    "defassettyp",
    "defftpflg",
    "loadattr1CFG",
    "loadattr2CFG",
    "loadattr3CFG",
    "loadattr4CFG",
    "loadattr5CFG",
    "stkmtd",
    "partfootdtlseg"
})
@XmlRootElement(name = "PARTFOOT_SEG")
public class PARTFOOTSEG {

    @XmlElement(name = "SEGNAM", required = true)
    protected String segnam;
    @XmlElement(name = "TRNTYP", required = true)
    protected String trntyp;
    @XmlElement(name = "PRTNUM", required = true)
    protected String prtnum;
    @XmlElement(name = "FTPCOD", required = true)
    protected String ftpcod;
    @XmlElement(name = "PRT_CLIENT_ID", required = true)
    protected String prtclientid;
    @XmlElement(name = "LNGDSC", required = true)
    protected String lngdsc;
    @XmlElement(name = "SHORT_DSC", required = true)
    protected String shortdsc;
    @XmlElement(name = "LOCALE_ID", required = true)
    protected String localeid;
    @XmlElement(name = "CASLVL", required = true)
    protected String caslvl;
    @XmlElement(name = "NSTLEN", required = true)
    protected String nstlen;
    @XmlElement(name = "NSTLEN_MU", required = true)
    protected String nstlenmu;
    @XmlElement(name = "NSTWID", required = true)
    protected String nstwid;
    @XmlElement(name = "NSTHGT", required = true)
    protected String nsthgt;
    @XmlElement(name = "PAL_STCK_HGT", required = true)
    protected String palstckhgt;
    @XmlElement(name = "DEF_ASSET_TYP", required = true)
    protected String defassettyp;
    @XmlElement(name = "DEFFTP_FLG", required = true)
    protected String defftpflg;
    @XmlElement(name = "LOAD_ATTR1_CFG", required = true)
    protected String loadattr1CFG;
    @XmlElement(name = "LOAD_ATTR2_CFG", required = true)
    protected String loadattr2CFG;
    @XmlElement(name = "LOAD_ATTR3_CFG", required = true)
    protected String loadattr3CFG;
    @XmlElement(name = "LOAD_ATTR4_CFG", required = true)
    protected String loadattr4CFG;
    @XmlElement(name = "LOAD_ATTR5_CFG", required = true)
    protected String loadattr5CFG;
    @XmlElement(name = "STKMTD", required = true)
    protected String stkmtd;
    @XmlElement(name = "PARTFOOT_DTL_SEG", required = true)
    protected List<PARTFOOTDTLSEG> partfootdtlseg;

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
     * Obtiene el valor de la propiedad trntyp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRNTYP() {
        return trntyp;
    }

    /**
     * Define el valor de la propiedad trntyp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRNTYP(String value) {
        this.trntyp = value;
    }

    /**
     * Obtiene el valor de la propiedad prtnum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRTNUM() {
        return prtnum;
    }

    /**
     * Define el valor de la propiedad prtnum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRTNUM(String value) {
        this.prtnum = value;
    }

    /**
     * Obtiene el valor de la propiedad ftpcod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFTPCOD() {
        return ftpcod;
    }

    /**
     * Define el valor de la propiedad ftpcod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFTPCOD(String value) {
        this.ftpcod = value;
    }

    /**
     * Obtiene el valor de la propiedad prtclientid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRTCLIENTID() {
        return prtclientid;
    }

    /**
     * Define el valor de la propiedad prtclientid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRTCLIENTID(String value) {
        this.prtclientid = value;
    }

    /**
     * Obtiene el valor de la propiedad lngdsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLNGDSC() {
        return lngdsc;
    }

    /**
     * Define el valor de la propiedad lngdsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLNGDSC(String value) {
        this.lngdsc = value;
    }

    /**
     * Obtiene el valor de la propiedad shortdsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHORTDSC() {
        return shortdsc;
    }

    /**
     * Define el valor de la propiedad shortdsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHORTDSC(String value) {
        this.shortdsc = value;
    }

    /**
     * Obtiene el valor de la propiedad localeid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOCALEID() {
        return localeid;
    }

    /**
     * Define el valor de la propiedad localeid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOCALEID(String value) {
        this.localeid = value;
    }

    /**
     * Obtiene el valor de la propiedad caslvl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCASLVL() {
        return caslvl;
    }

    /**
     * Define el valor de la propiedad caslvl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCASLVL(String value) {
        this.caslvl = value;
    }

    /**
     * Obtiene el valor de la propiedad nstlen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSTLEN() {
        return nstlen;
    }

    /**
     * Define el valor de la propiedad nstlen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSTLEN(String value) {
        this.nstlen = value;
    }

    /**
     * Obtiene el valor de la propiedad nstlenmu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSTLENMU() {
        return nstlenmu;
    }

    /**
     * Define el valor de la propiedad nstlenmu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSTLENMU(String value) {
        this.nstlenmu = value;
    }

    /**
     * Obtiene el valor de la propiedad nstwid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSTWID() {
        return nstwid;
    }

    /**
     * Define el valor de la propiedad nstwid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSTWID(String value) {
        this.nstwid = value;
    }

    /**
     * Obtiene el valor de la propiedad nsthgt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNSTHGT() {
        return nsthgt;
    }

    /**
     * Define el valor de la propiedad nsthgt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNSTHGT(String value) {
        this.nsthgt = value;
    }

    /**
     * Obtiene el valor de la propiedad palstckhgt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPALSTCKHGT() {
        return palstckhgt;
    }

    /**
     * Define el valor de la propiedad palstckhgt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPALSTCKHGT(String value) {
        this.palstckhgt = value;
    }

    /**
     * Obtiene el valor de la propiedad defassettyp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDEFASSETTYP() {
        return defassettyp;
    }

    /**
     * Define el valor de la propiedad defassettyp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDEFASSETTYP(String value) {
        this.defassettyp = value;
    }

    /**
     * Obtiene el valor de la propiedad defftpflg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDEFFTPFLG() {
        return defftpflg;
    }

    /**
     * Define el valor de la propiedad defftpflg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDEFFTPFLG(String value) {
        this.defftpflg = value;
    }

    /**
     * Obtiene el valor de la propiedad loadattr1CFG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOADATTR1CFG() {
        return loadattr1CFG;
    }

    /**
     * Define el valor de la propiedad loadattr1CFG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOADATTR1CFG(String value) {
        this.loadattr1CFG = value;
    }

    /**
     * Obtiene el valor de la propiedad loadattr2CFG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOADATTR2CFG() {
        return loadattr2CFG;
    }

    /**
     * Define el valor de la propiedad loadattr2CFG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOADATTR2CFG(String value) {
        this.loadattr2CFG = value;
    }

    /**
     * Obtiene el valor de la propiedad loadattr3CFG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOADATTR3CFG() {
        return loadattr3CFG;
    }

    /**
     * Define el valor de la propiedad loadattr3CFG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOADATTR3CFG(String value) {
        this.loadattr3CFG = value;
    }

    /**
     * Obtiene el valor de la propiedad loadattr4CFG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOADATTR4CFG() {
        return loadattr4CFG;
    }

    /**
     * Define el valor de la propiedad loadattr4CFG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOADATTR4CFG(String value) {
        this.loadattr4CFG = value;
    }

    /**
     * Obtiene el valor de la propiedad loadattr5CFG.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOADATTR5CFG() {
        return loadattr5CFG;
    }

    /**
     * Define el valor de la propiedad loadattr5CFG.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOADATTR5CFG(String value) {
        this.loadattr5CFG = value;
    }

    /**
     * Obtiene el valor de la propiedad stkmtd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTKMTD() {
        return stkmtd;
    }

    /**
     * Define el valor de la propiedad stkmtd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTKMTD(String value) {
        this.stkmtd = value;
    }

    /**
     * Gets the value of the partfootdtlseg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partfootdtlseg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPARTFOOTDTLSEG().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PARTFOOTDTLSEG }
     * 
     * 
     */
    public List<PARTFOOTDTLSEG> getPARTFOOTDTLSEG() {
        if (partfootdtlseg == null) {
            partfootdtlseg = new ArrayList<PARTFOOTDTLSEG>();
        }
        return this.partfootdtlseg;
    }

}
