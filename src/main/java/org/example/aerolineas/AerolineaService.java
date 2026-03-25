package org.example.aerolineas;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import org.example.models.ResultadoValidacion;

@WebService(
        serviceName = "AerolineaService",
        targetNamespace = "http://aerolineas.example.org/"
)
public class AerolineaService {

    private final AerolineaController controller = new AerolineaController();

    @WebMethod
    @RequestWrapper(localName = "validarPeso", targetNamespace = "http://aerolineas.example.org/")
    public ResultadoValidacion validarPeso(
            @WebParam(name = "idViaje",      targetNamespace = "http://aerolineas.example.org/") int idViaje,
            @WebParam(name = "idaerolineas", targetNamespace = "http://aerolineas.example.org/") int idaerolineas
    ) throws Exception {
        System.out.println("idViaje recibido: " + idViaje);
        System.out.println("idaerolineas recibido: " + idaerolineas);
        return controller.validar(idViaje, idaerolineas);
    }

    @WebMethod
    @RequestWrapper(localName = "CrearAerolinea", targetNamespace = "http://aerolineas.example.org/")
    public void CrearAerolinea(
            @WebParam(name = "Nombre",targetNamespace = "http://aerolineas.example.org/" ) String Nombre,
            @WebParam(name = "limitePeso",targetNamespace = "http://aerolineas.example.org/" ) double limitePeso,
            @WebParam(name = "costoExcedente",targetNamespace = "http://aerolineas.example.org/" ) double costoExcedente)
        throws Exception{
        controller.CrearAerolinea(Nombre, limitePeso, costoExcedente);
    }


}