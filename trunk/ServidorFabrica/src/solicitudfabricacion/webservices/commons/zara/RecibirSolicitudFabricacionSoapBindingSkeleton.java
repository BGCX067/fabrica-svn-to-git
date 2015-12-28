/**
 * RecibirSolicitudFabricacionSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package solicitudfabricacion.webservices.commons.zara;

public class RecibirSolicitudFabricacionSoapBindingSkeleton implements solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion, org.apache.axis.wsdl.Skeleton {
    private solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("recibirSolicitudFabricacion", _params, new javax.xml.namespace.QName("", "recibirSolicitudFabricacionReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:zara.commons.webservices.solicitudfabricacion", "recibirSolicitudFabricacion"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("recibirSolicitudFabricacion") == null) {
            _myOperations.put("recibirSolicitudFabricacion", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("recibirSolicitudFabricacion")).add(_oper);
    }

    public RecibirSolicitudFabricacionSoapBindingSkeleton() {
        this.impl = new solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacionSoapBindingImpl();
    }

    public RecibirSolicitudFabricacionSoapBindingSkeleton(solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion impl) {
        this.impl = impl;
    }
    public boolean recibirSolicitudFabricacion(java.lang.String in0) throws java.rmi.RemoteException
    {
        boolean ret = impl.recibirSolicitudFabricacion(in0);
        return ret;
    }

}
