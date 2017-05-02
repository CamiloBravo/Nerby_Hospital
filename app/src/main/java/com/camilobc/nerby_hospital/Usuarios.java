package com.camilobc.nerby_hospital;

/**
 * Created by Camilo on 01/05/2017.
 */

public class Usuarios {
    String documento, nombre, telefono, correo, sexo, sangre, eps, alergias, enfermedades, acudiente, tel_acudiente;

    public Usuarios(){

    }

    public Usuarios(String documento, String nombre, String telefono, String correo, String sexo, String sangre, String eps, String alergias, String enfermedades, String acudiente, String tel_acudiente) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.sexo = sexo;
        this.sangre = sangre;
        this.eps = eps;
        this.alergias = alergias;
        this.enfermedades = enfermedades;
        this.acudiente = acudiente;
        this.tel_acudiente = tel_acudiente;
    }

    public String getTel_acudiente() {
        return tel_acudiente;
    }

    public void setTel_acudiente(String tel_acudiente) {
        this.tel_acudiente = tel_acudiente;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(String enfermedades) {
        this.enfermedades = enfermedades;
    }

    public String getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(String acudiente) {
        this.acudiente = acudiente;
    }



    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getEps() {
        return eps;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
}
