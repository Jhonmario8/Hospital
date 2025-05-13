package com.hospital.modelo.dto;


import com.hospital.modelo.entidad.Habitacion;

public class IngresoDto {
    private Integer idIngreso;
    private Integer idPersona;
    private Integer idHabitacion;
    private Boolean acompañante;
    private Boolean hospitalizado;

    public IngresoDto() {
    }

    public IngresoDto(Integer idIngreso, Integer idPersona, Integer idHabitacion, Boolean acompañante, Boolean hospitalizado) {
        this.idIngreso = idIngreso;
        this.idPersona = idPersona;
        this.idHabitacion = idHabitacion;
        this.acompañante = acompañante;
        this.hospitalizado = hospitalizado;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Boolean getAcompañante() {
        return acompañante;
    }

    public void setAcompañante(Boolean acompañante) {
        this.acompañante = acompañante;
    }

    public Boolean getHospitalizado() {
        return hospitalizado;
    }

    public void setHospitalizado(Boolean hospitalizado) {
        this.hospitalizado = hospitalizado;
    }
}
