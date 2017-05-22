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
 * <p>Clase Java para AdditionalPartyIdentificationListType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="AdditionalPartyIdentificationListType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BUYER_ASSIGNED_IDENTIFIER_FOR_A_PARTY"/>
 *     &lt;enumeration value="DEA_DRUG_ENFORCEMENT_AGENCY"/>
 *     &lt;enumeration value="DUNS"/>
 *     &lt;enumeration value="DUNS_PLUS_FOUR"/>
 *     &lt;enumeration value="HIN_CANADIAN_HEALTHCARE_IDENTIFICATION_NUMBER"/>
 *     &lt;enumeration value="SCAC"/>
 *     &lt;enumeration value="SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY"/>
 *     &lt;enumeration value="TD_LINK_TRADE_DIMENSIONS"/>
 *     &lt;enumeration value="UCC_COMMUNICATION_IDENTIFICATION"/>
 *     &lt;enumeration value="UN_LOCATION_CODE"/>
 *     &lt;enumeration value="USDA_ESTABLISHMENT_NUMBER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AdditionalPartyIdentificationListType")
@XmlEnum
public enum AdditionalPartyIdentificationListType {

    BUYER_ASSIGNED_IDENTIFIER_FOR_A_PARTY,
    DEA_DRUG_ENFORCEMENT_AGENCY,
    DUNS,
    DUNS_PLUS_FOUR,
    HIN_CANADIAN_HEALTHCARE_IDENTIFICATION_NUMBER,
    SCAC,
    SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY,
    TD_LINK_TRADE_DIMENSIONS,
    UCC_COMMUNICATION_IDENTIFICATION,
    UN_LOCATION_CODE,
    USDA_ESTABLISHMENT_NUMBER;

    public String value() {
        return name();
    }

    public static AdditionalPartyIdentificationListType fromValue(String v) {
        return valueOf(v);
    }

}
