package com.hospital.modelo.dto;

public class EstadisticaDto {

    private String etiqueta;
    private long valor;

    public EstadisticaDto(String etiqueta, long valor) {
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public EstadisticaDto() {
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
}
