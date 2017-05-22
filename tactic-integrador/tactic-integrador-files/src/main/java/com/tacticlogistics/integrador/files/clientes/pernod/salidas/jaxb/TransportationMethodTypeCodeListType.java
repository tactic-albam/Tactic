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
 * <p>Clase Java para TransportationMethodTypeCodeListType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="TransportationMethodTypeCodeListType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AIR"/>
 *     &lt;enumeration value="AIR_CHARTER"/>
 *     &lt;enumeration value="AIR_EXPRESS"/>
 *     &lt;enumeration value="AIR_FREIGHT"/>
 *     &lt;enumeration value="BEST_WAY_SHIPPERS_OPTION"/>
 *     &lt;enumeration value="BOOK_POSTAL"/>
 *     &lt;enumeration value="BUS"/>
 *     &lt;enumeration value="CAB"/>
 *     &lt;enumeration value="CONSOLIDATION"/>
 *     &lt;enumeration value="CONTRACT_CARRIER"/>
 *     &lt;enumeration value="CUSTOMER_PICKUP"/>
 *     &lt;enumeration value="CUSTOMER_PICKUP_OR_CUSTOMERS_EXPENSE"/>
 *     &lt;enumeration value="EXPEDITED_TRUCK"/>
 *     &lt;enumeration value="GEOGRAPHIC_RECEIVING"/>
 *     &lt;enumeration value="GEOGRAPHIC_RECEIVING_SHIPPING"/>
 *     &lt;enumeration value="GEOGRAPHIC_SHIPPING"/>
 *     &lt;enumeration value="INLAND_WATERWAY"/>
 *     &lt;enumeration value="INTERMODAL_PIGGYBACK"/>
 *     &lt;enumeration value="LESS_THAN_TRUCKLOAD"/>
 *     &lt;enumeration value="MOTOR"/>
 *     &lt;enumeration value="MOTOR_COMMON_CARRIER"/>
 *     &lt;enumeration value="MOTOR_FLATBED"/>
 *     &lt;enumeration value="MOTOR_PACKAGE_CARRIER"/>
 *     &lt;enumeration value="MOTOR_TRUCKLOAD"/>
 *     &lt;enumeration value="MOTOR_VAN"/>
 *     &lt;enumeration value="MUTUALLY_DEFINED"/>
 *     &lt;enumeration value="OCEAN"/>
 *     &lt;enumeration value="PARCEL_POST"/>
 *     &lt;enumeration value="PIPELINE"/>
 *     &lt;enumeration value="POOLED_AIR"/>
 *     &lt;enumeration value="POOLED_PIGGYPACK"/>
 *     &lt;enumeration value="POOL_TO_POOL"/>
 *     &lt;enumeration value="POOLED_RAIL"/>
 *     &lt;enumeration value="POOLED_TRUCK"/>
 *     &lt;enumeration value="PRIVATE_CARRIER"/>
 *     &lt;enumeration value="PRIVATE_PARCEL_SERVICE"/>
 *     &lt;enumeration value="PRIVATE_VESSEL"/>
 *     &lt;enumeration value="RAIL"/>
 *     &lt;enumeration value="ROADRAILER"/>
 *     &lt;enumeration value="SEA_AIR"/>
 *     &lt;enumeration value="STEAMSHIP"/>
 *     &lt;enumeration value="SUPPLIER_TRUCK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransportationMethodTypeCodeListType")
@XmlEnum
public enum TransportationMethodTypeCodeListType {

    AIR,
    AIR_CHARTER,
    AIR_EXPRESS,
    AIR_FREIGHT,
    BEST_WAY_SHIPPERS_OPTION,
    BOOK_POSTAL,
    BUS,
    CAB,
    CONSOLIDATION,
    CONTRACT_CARRIER,
    CUSTOMER_PICKUP,
    CUSTOMER_PICKUP_OR_CUSTOMERS_EXPENSE,
    EXPEDITED_TRUCK,
    GEOGRAPHIC_RECEIVING,
    GEOGRAPHIC_RECEIVING_SHIPPING,
    GEOGRAPHIC_SHIPPING,
    INLAND_WATERWAY,
    INTERMODAL_PIGGYBACK,
    LESS_THAN_TRUCKLOAD,
    MOTOR,
    MOTOR_COMMON_CARRIER,
    MOTOR_FLATBED,
    MOTOR_PACKAGE_CARRIER,
    MOTOR_TRUCKLOAD,
    MOTOR_VAN,
    MUTUALLY_DEFINED,
    OCEAN,
    PARCEL_POST,
    PIPELINE,
    POOLED_AIR,
    POOLED_PIGGYPACK,
    POOL_TO_POOL,
    POOLED_RAIL,
    POOLED_TRUCK,
    PRIVATE_CARRIER,
    PRIVATE_PARCEL_SERVICE,
    PRIVATE_VESSEL,
    RAIL,
    ROADRAILER,
    SEA_AIR,
    STEAMSHIP,
    SUPPLIER_TRUCK;

    public String value() {
        return name();
    }

    public static TransportationMethodTypeCodeListType fromValue(String v) {
        return valueOf(v);
    }

}
