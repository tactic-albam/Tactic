//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CommandType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CommandType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ean.ucc:2}command"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommandType", propOrder = {
    "command"
})
public class CommandType {

    @XmlElementRef(name = "command", namespace = "urn:ean.ucc:2", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractCommandType> command;

    /**
     * Obtiene el valor de la propiedad command.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DocumentCommandType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractCommandType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractCommandType> getCommand() {
        return command;
    }

    /**
     * Define el valor de la propiedad command.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DocumentCommandType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractCommandType }{@code >}
     *     
     */
    public void setCommand(JAXBElement<? extends AbstractCommandType> value) {
        this.command = value;
    }

}
