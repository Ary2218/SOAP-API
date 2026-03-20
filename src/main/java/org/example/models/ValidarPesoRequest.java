package org.example.models;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidarPesoRequest {
    @XmlElement(required = true)
    public int idViaje;
    @XmlElement(required = true)
    public int idaerolineas;
}