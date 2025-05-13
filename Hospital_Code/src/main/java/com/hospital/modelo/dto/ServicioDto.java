package com.hospital.modelo.dto;

public class ServicioDto {
    private int codServicio ;
    private String nomServicio;
    private double precioServicio;
    private String detallesServicio;

    public int getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(int codServicio) {
        this.codServicio = codServicio;
    }

    public String getNomServicio() {
        return nomServicio;
    }

    public void setNomServicio(String nomServicio) {
        this.nomServicio = nomServicio;
    }

    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    public String getDetallesServicio() {
        return detallesServicio;
    }

    public void setDetallesServicio(String detallesServicio) {
        this.detallesServicio = detallesServicio;
    }
}
