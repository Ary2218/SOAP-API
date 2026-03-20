package org.example.aerolineas;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.models.ResultadoValidacion;
import java.sql.SQLException;

@WebService(serviceName = "AerolineaService")
public class AerolineaService {

    private final AerolineaController controller = new AerolineaController();

    @WebMethod
    public ResultadoValidacion validarPeso(
            @WebParam(name = "viajeId")     int viajeId,
            @WebParam(name = "aerolineaId") int aerolineaId
    ) throws SQLException {
        return controller.validar(viajeId, aerolineaId);


    }
}