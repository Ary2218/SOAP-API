package org.example.models;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultadoValidacion {
        public boolean aprobado;
        public String  mensaje;
        public String  aerolinea;
        public double  pesoTotal;
        public double  limiteKg;
        public double  kgLibres;
        public double  cargoExtra;
    }

