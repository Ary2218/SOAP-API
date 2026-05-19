package org.example.aerolineas;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import org.example.models.ResultadoValidacion;
import org.example.models.AerolineaDTO;
import java.util.List;

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

    @WebMethod
    @RequestWrapper(localName = "LeerAerolineas", targetNamespace = "http://aerolineas.example.org/")
    public List<AerolineaDTO> LeerAerolineas() throws Exception {
        return controller.LeerAerolineas();
    }

    @WebMethod
    @RequestWrapper(localName = "LeerAerolinea", targetNamespace = "http://aerolineas.example.org/")
    public AerolineaDTO LeerAerolinea(
            @WebParam(name = "idAerolinea", targetNamespace = "http://aerolineas.example.org/") int idAerolinea)
        throws Exception {
        return controller.LeerAerolinea(idAerolinea);
    }

    @WebMethod
    @RequestWrapper(localName = "BorrarAerolinea", targetNamespace = "http://aerolineas.example.org/")
    public void BorrarAerolinea(
            @WebParam(name = "idAerolinea", targetNamespace = "http://aerolineas.example.org/") int idAerolinea)
        throws Exception {
        controller.BorrarAerolinea(idAerolinea);
    }

    @WebMethod
    @RequestWrapper(localName = "ActualizarNombreAerolinea", targetNamespace = "http://aerolineas.example.org/")
    public void ActualizarNombreAerolinea(
            @WebParam(name = "idAerolinea", targetNamespace = "http://aerolineas.example.org/") int idAerolinea,
            @WebParam(name = "Nombre", targetNamespace = "http://aerolineas.example.org/") String Nombre)
        throws Exception {
        controller.ActualizarNombreAerolinea(idAerolinea, Nombre);
    }

    @WebMethod
    @RequestWrapper(localName = "ActualizarLimiteAerolinea", targetNamespace = "http://aerolineas.example.org/")
    public void ActualizarLimiteAerolinea(
            @WebParam(name = "idAerolinea", targetNamespace = "http://aerolineas.example.org/") int idAerolinea,
            @WebParam(name = "Limite", targetNamespace = "http://aerolineas.example.org/") double Limite)
        throws Exception {
        controller.ActualizarLimiteAerolinea(idAerolinea, Limite);
    }

    @WebMethod
    @RequestWrapper(localName = "ActualizarExcedenteAerolinea", targetNamespace = "http://aerolineas.example.org/")
    public void ActualizarExcedenteAerolinea(
            @WebParam(name = "idAerolinea", targetNamespace = "http://aerolineas.example.org/") int idAerolinea,
            @WebParam(name = "excedente", targetNamespace = "http://aerolineas.example.org/") double excedente)
        throws Exception {
        controller.ActualizarExcedenteAerolinea(idAerolinea, excedente);
    }

    @WebMethod
    @RequestWrapper(localName = "ActualizarCompleto", targetNamespace = "http://aerolineas.example.org/")
    public void ActualizarCompleto(
            @WebParam(name = "idAeroLinea", targetNamespace = "http://aerolineas.example.org/") int idAeroLinea,
            @WebParam(name = "Nombre", targetNamespace = "http://aerolineas.example.org/") String Nombre,
            @WebParam(name = "limitePeso", targetNamespace = "http://aerolineas.example.org/") double limitePeso,
            @WebParam(name = "costoExcedente", targetNamespace = "http://aerolineas.example.org/") double costoExcedente)
        throws Exception {
        controller.ActualizarCompleto(idAeroLinea, Nombre, limitePeso, costoExcedente);
    }


}