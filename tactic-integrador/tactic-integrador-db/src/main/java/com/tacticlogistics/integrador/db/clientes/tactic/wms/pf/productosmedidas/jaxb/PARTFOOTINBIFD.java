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
 *         &lt;element ref="{}CTRL_SEG"/>
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
    "ctrlseg"
})
@XmlRootElement(name = "PARTFOOT_INB_IFD")
public class PARTFOOTINBIFD {

    @XmlElement(name = "CTRL_SEG", required = true)
    protected CTRLSEG ctrlseg;

    /**
     * Obtiene el valor de la propiedad ctrlseg.
     * 
     * @return
     *     possible object is
     *     {@link CTRLSEG }
     *     
     */
    public CTRLSEG getCTRLSEG() {
        return ctrlseg;
    }

    /**
     * Define el valor de la propiedad ctrlseg.
     * 
     * @param value
     *     allowed object is
     *     {@link CTRLSEG }
     *     
     */
    public void setCTRLSEG(CTRLSEG value) {
        this.ctrlseg = value;
    }

}
