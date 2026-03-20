package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.aerolineas.AerolineaService;

public class Main {
    public static void main(String[] args) {
        String url = "http://localhost:8081/aerolineas";
        Endpoint.publish(url, new AerolineaService());
        System.out.println("Servicio SOAP corriendo.");
        System.out.println("WSDL en: " + url + "?wsdl");
    }
}
