//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.09 a las 07:17:25 AM COT 
//


package com.tacticlogistics.integrador.files.clientes.pernod.salidas.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AdditionalTradeItemIdentificationListType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="AdditionalTradeItemIdentificationListType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BUYER_ASSIGNED"/>
 *     &lt;enumeration value="SUPPLIER_ASSIGNED"/>
 *     &lt;enumeration value="INDUSTRY_ASSIGNED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AdditionalTradeItemIdentificationListType")
@XmlEnum
public enum AdditionalTradeItemIdentificationListType {

    BUYER_ASSIGNED,
    SUPPLIER_ASSIGNED,
    INDUSTRY_ASSIGNED;

    public String value() {
        return name();
    }

    public static AdditionalTradeItemIdentificationListType fromValue(String v) {
        return valueOf(v);
    }

}
