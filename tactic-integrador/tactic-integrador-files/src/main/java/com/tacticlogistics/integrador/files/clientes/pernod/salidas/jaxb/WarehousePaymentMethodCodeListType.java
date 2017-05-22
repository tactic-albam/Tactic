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
 * <p>Clase Java para WarehousePaymentMethodCodeListType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="WarehousePaymentMethodCodeListType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="COLLECT"/>
 *     &lt;enumeration value="COLLECT_FREIGHT_CREDITED_BACK_TO_CUSTOMER"/>
 *     &lt;enumeration value="CUSTOMER_PICKUP_OR_BACKHAUL"/>
 *     &lt;enumeration value="DEFINED_BY_BUYER_AND_SELLER"/>
 *     &lt;enumeration value="FOB_PORT_OF_CALL"/>
 *     &lt;enumeration value="HALF_PREPAID"/>
 *     &lt;enumeration value="MIXED"/>
 *     &lt;enumeration value="PREPAID_BUT_CHARGED_TO_CUSTOMER"/>
 *     &lt;enumeration value="PREPAID_BY_SELLER"/>
 *     &lt;enumeration value="PREPAID_ONLY"/>
 *     &lt;enumeration value="THIRD_PARTY_PAY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WarehousePaymentMethodCodeListType", namespace = "urn:ean.ucc:deliver:2")
@XmlEnum
public enum WarehousePaymentMethodCodeListType {

    COLLECT,
    COLLECT_FREIGHT_CREDITED_BACK_TO_CUSTOMER,
    CUSTOMER_PICKUP_OR_BACKHAUL,
    DEFINED_BY_BUYER_AND_SELLER,
    FOB_PORT_OF_CALL,
    HALF_PREPAID,
    MIXED,
    PREPAID_BUT_CHARGED_TO_CUSTOMER,
    PREPAID_BY_SELLER,
    PREPAID_ONLY,
    THIRD_PARTY_PAY;

    public String value() {
        return name();
    }

    public static WarehousePaymentMethodCodeListType fromValue(String v) {
        return valueOf(v);
    }

}
