package com.camilobc.nerby_hospital;

/**
 * Created by Camilo on 02/05/2017.
 */

public class Lista_entrada {
//    int idImagen;
    String Direccion, Nombre, Telefono;

    public Lista_entrada(){

    }

    public Lista_entrada(String dirección, String nombre, String teléfono) {
        this.Direccion = dirección;
        this.Nombre = nombre;
        this.Telefono = teléfono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }
}
