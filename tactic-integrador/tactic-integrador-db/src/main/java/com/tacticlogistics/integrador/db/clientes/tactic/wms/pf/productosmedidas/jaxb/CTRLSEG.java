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
 *         &lt;element name="TRNNAM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TRNVER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WHSE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}PARTFOOT_SEG"/>
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
    "trnnam",
    "trnver",
    "whseid",
    "partfootseg"
})
@XmlRootElement(name = "CTRL_SEG")
public class CTRLSEG {

    @XmlElement(name = "TRNNAM", required = true)
    protected String trnnam;
    @XmlElement(name = "TRNVER", required = true)
    protected String trnver;
    @XmlElement(name = "WHSE_ID", required = true)
    protected String whseid;
    @XmlElement(name = "PARTFOOT_SEG", required = true)
    protected PARTFOOTSEG partfootseg;

    /**
     * Obtiene el valor de la propiedad trnnam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRNNAM() {
        return trnnam;
    }

    /**
     * Define el valor de la propiedad trnnam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRNNAM(String value) {
        this.trnnam = value;
    }

    /**
     * Obtiene el valor de la propiedad trnver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRNVER() {
        return trnver;
    }

    /**
     * Define el valor de la propiedad trnver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRNVER(String value) {
        this.trnver = value;
    }

    /**
     * Obtiene el valor de la propiedad whseid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWHSEID() {
        return whseid;
    }

    /**
     * Define el valor de la propiedad whseid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWHSEID(String value) {
        this.whseid = value;
    }

    /**
     * Obtiene el valor de la propiedad partfootseg.
     * 
     * @return
     *     possible object is
     *     {@link PARTFOOTSEG }
     *     
     */
    public PARTFOOTSEG getPARTFOOTSEG() {
        return partfootseg;
    }

    /**
     * Define el valor de la propiedad partfootseg.
     * 
     * @param value
     *     allowed object is
     *     {@link PARTFOOTSEG }
     *     
     */
    public void setPARTFOOTSEG(PARTFOOTSEG value) {
        this.partfootseg = value;
    }

}
