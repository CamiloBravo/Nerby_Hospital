package com.camilobc.nerby_hospital;

/**
 * Created by Camilo on 02/06/2017.
 */

public class DatosHospi {
    String direccion, nombre, telefono;
    int idImagen;

    DatosHospi(){

    }

    public DatosHospi(String direccion, String nombre, String telefono, int idImagen) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.idImagen = idImagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
