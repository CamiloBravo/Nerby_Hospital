package com.camilobc.nerby_hospital;

/**
 * Created by Camilo on 15/05/2017.
 */

public class Correo {
    String correo, nombre, telefono, documento, sexo, sangre, alergias, enfermedades, acudiente, tel_acudiente;

    public Correo(){

    }
    public Correo(String correo, String nombre, String telefono, String documento, String sexo, String sangre, String alergias, String enfermedades, String acudiente, String tel_acudiente) {
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.documento = documento;
        this.sexo = sexo;
        this.sangre = sangre;
        this.alergias = alergias;
        this.enfermedades = enfermedades;
        this.acudiente = acudiente;
        this.tel_acudiente = tel_acudiente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
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

    public String getTel_acudiente() {
        return tel_acudiente;
    }

    public void setTel_acudiente(String tel_acudiente) {
        this.tel_acudiente = tel_acudiente;
    }
}
