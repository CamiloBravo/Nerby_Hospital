package com.camilobc.nerby_hospital;

/**
 * Created by Usuario on 05/06/2017.
 */

public class Ubicacion {
    String lat, longitud;
    public Ubicacion(){}

    public Ubicacion(String lat, String longitud) {
        this.lat = lat;
        this.longitud = longitud;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
