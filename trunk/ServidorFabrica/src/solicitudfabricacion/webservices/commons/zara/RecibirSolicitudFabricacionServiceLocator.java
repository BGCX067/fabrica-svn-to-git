/**
 * RecibirSolicitudFabricacionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package solicitudfabricacion.webservices.commons.zara;

public class RecibirSolicitudFabricacionServiceLocator extends org.apache.axis.client.Service implements solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacionService {

    public RecibirSolicitudFabricacionServiceLocator() {
    }


    public RecibirSolicitudFabricacionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RecibirSolicitudFabricacionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RecibirSolicitudFabricacion
    private java.lang.String RecibirSolicitudFabricacion_address = "http://localhost:8080/axis/services/RecibirSolicitudFabricacion";

    public java.lang.String getRecibirSolicitudFabricacionAddress() {
        return RecibirSolicitudFabricacion_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecibirSolicitudFabricacionWSDDServiceName = "RecibirSolicitudFabricacion";

    public java.lang.String getRecibirSolicitudFabricacionWSDDServiceName() {
        return RecibirSolicitudFabricacionWSDDServiceName;
    }

    public void setRecibirSolicitudFabricacionWSDDServiceName(java.lang.String name) {
        RecibirSolicitudFabricacionWSDDServiceName = name;
    }

    public solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion getRecibirSolicitudFabricacion() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecibirSolicitudFabricacion_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecibirSolicitudFabricacion(endpoint);
    }

    public solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion getRecibirSolicitudFabricacion(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacionSoapBindingStub _stub = new solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacionSoapBindingStub(portAddress, this);
            _stub.setPortName(getRecibirSolicitudFabricacionWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecibirSolicitudFabricacionEndpointAddress(java.lang.String address) {
        RecibirSolicitudFabricacion_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion.class.isAssignableFrom(serviceEndpointInterface)) {
                solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacionSoapBindingStub _stub = new solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacionSoapBindingStub(new java.net.URL(RecibirSolicitudFabricacion_address), this);
                _stub.setPortName(getRecibirSolicitudFabricacionWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RecibirSolicitudFabricacion".equals(inputPortName)) {
            return getRecibirSolicitudFabricacion();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:zara.commons.webservices.solicitudfabricacion", "RecibirSolicitudFabricacionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:zara.commons.webservices.solicitudfabricacion", "RecibirSolicitudFabricacion"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RecibirSolicitudFabricacion".equals(portName)) {
            setRecibirSolicitudFabricacionEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
