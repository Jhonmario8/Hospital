package com.hospital.modelo.dto;

public class PersonaDto {

    private int idPersona;
    private String nomPersona;
    private int edadPersona;
    private String direccion;
    private String telefonoPersona;
    private boolean tipoPersona;

    public PersonaDto() {
    }

    public PersonaDto(int idPersona, String nomPersona, int edadPersona, String direccion, String telefonoPersona, boolean tipoPersona) {
        this.idPersona = idPersona;
        this.nomPersona = nomPersona;
        this.edadPersona = edadPersona;
        this.direccion = direccion;
        this.telefonoPersona = telefonoPersona;
        this.tipoPersona = tipoPersona;
    }

    public PersonaDto(int idPersona, String nomPersona, int edadPersona, String telefonoPersona) {
        this.idPersona = idPersona;
        this.nomPersona = nomPersona;
        this.edadPersona = edadPersona;
        this.telefonoPersona = telefonoPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNomPersona() {
        return nomPersona;
    }

    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }

    public int getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(int edadPersona) {
        this.edadPersona = edadPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public boolean isTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(boolean tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
