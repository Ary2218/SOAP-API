package org.example.aerolineas;

import org.example.config.dbconfig;
import org.example.models.ResultadoValidacion;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AerolineaController {

    private final DataSource ds = dbconfig.getDataSource();

    public ResultadoValidacion validar(int viajeId, int aerolineaId) throws SQLException {

        double pesoTotal = 0;
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT PesoTotal FROM viajes WHERE idViaje = ?")) {
            ps.setInt(1, viajeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pesoTotal = rs.getDouble("PesoTotal");
            }
        }

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
}