package org.example.aerolineas;

import org.example.config.dbconfig;
import org.example.models.ResultadoValidacion;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;


public class AerolineaController {

    private final DataSource ds = dbconfig.getDataSource();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String REST_URL = "http://localhost:3000";

    public ResultadoValidacion validar(int viajeId, int aerolineaId) throws Exception {

        double pesoTotal = obtenerPesoDesdeREST(viajeId);
        double limiteKg      = 0;
        double costoExceso   = 0;
        String nombreAerolinea = "";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT nombre, limitepeso, costokgexcedente FROM aerolineas WHERE idaerolineas = ?")) {
            ps.setInt(1, aerolineaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombreAerolinea = rs.getString("nombre");
                limiteKg        = rs.getDouble("limitepeso");
                costoExceso     = rs.getDouble("costokgexcedente");
            }
        }

        ResultadoValidacion resultado = new ResultadoValidacion();
        resultado.aerolinea = nombreAerolinea;
        resultado.pesoTotal = pesoTotal;
        resultado.limiteKg  = limiteKg;

        if (pesoTotal <= limiteKg) {
            resultado.aprobado   = true;
            resultado.mensaje    = "Tu equipaje está dentro del límite permitido.";
            resultado.kgLibres   = limiteKg - pesoTotal;
            resultado.cargoExtra = 0;
        } else {
            double exceso        = pesoTotal - limiteKg;
            resultado.aprobado   = false;
            resultado.mensaje    = "Tu equipaje excede el límite por " + exceso + " kg.";
            resultado.kgLibres   = 0;
            resultado.cargoExtra = exceso * costoExceso;
        }

        return resultado;
    }

    private double obtenerPesoDesdeREST(int idViaje) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(REST_URL + "/Viaje/" + idViaje))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println("Llamando a: " + REST_URL + "/Viaje/" + idViaje);
        System.out.println("Status REST: " + response.statusCode());
        System.out.println("Body REST: " + response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Viaje no encontrado en la API REST");
        }

        String body = response.body();
        String buscar = "\"PesoTotal\":";
        int inicio = body.indexOf(buscar) + buscar.length();

        // Saltar espacios que pueda haber después de los dos puntos
        while (inicio < body.length() && body.charAt(inicio) == ' ') {
            inicio++;
        }

        int fin = body.indexOf(",", inicio);
        if (fin == -1) fin = body.indexOf("}", inicio);

        String valorStr = body.substring(inicio, fin).trim();
        System.out.println("Valor extraído: " + valorStr);

        return Double.parseDouble(valorStr);
    }
}
