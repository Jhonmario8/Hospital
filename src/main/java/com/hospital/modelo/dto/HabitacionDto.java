package com.hospital.modelo.dto;

public class HabitacionDto {
    private int numHabitacion;
    private String tipoHabitacion;
    private int capacidad;

    public HabitacionDto() {
    }

    public HabitacionDto(int numHabitacion, String tipoHabitacion, int capacidad) {
        this.numHabitacion = numHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.capacidad = capacidad;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
