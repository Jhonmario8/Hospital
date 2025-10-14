package com.hospital.modelo.dto;

public class AnalisisDatosDto {

    private long totalPersonas;
    private long totalEmpleados;
    private long empleadosDisponibles;
    private long empleadosNoDisponibles;
    private long totalPacientes;
    private long pacientesHospitalizados;
    private long totalHabitaciones;
    private long habitacionesOcupadas;
    private double porcentajePersonalDisponible;
    private double porcentajeOcupacion;

    public AnalisisDatosDto() {
    }

    public AnalisisDatosDto(long totalPersonas, long totalEmpleados, long empleadosDisponibles, long empleadosNoDisponibles,
                             long totalPacientes, long pacientesHospitalizados, long totalHabitaciones, long habitacionesOcupadas,
                             double porcentajePersonalDisponible, double porcentajeOcupacion) {
        this.totalPersonas = totalPersonas;
        this.totalEmpleados = totalEmpleados;
        this.empleadosDisponibles = empleadosDisponibles;
        this.empleadosNoDisponibles = empleadosNoDisponibles;
        this.totalPacientes = totalPacientes;
        this.pacientesHospitalizados = pacientesHospitalizados;
        this.totalHabitaciones = totalHabitaciones;
        this.habitacionesOcupadas = habitacionesOcupadas;
        this.porcentajePersonalDisponible = porcentajePersonalDisponible;
        this.porcentajeOcupacion = porcentajeOcupacion;
    }

    public long getTotalPersonas() {
        return totalPersonas;
    }

    public void setTotalPersonas(long totalPersonas) {
        this.totalPersonas = totalPersonas;
    }

    public long getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(long totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    public long getEmpleadosDisponibles() {
        return empleadosDisponibles;
    }

    public void setEmpleadosDisponibles(long empleadosDisponibles) {
        this.empleadosDisponibles = empleadosDisponibles;
    }

    public long getEmpleadosNoDisponibles() {
        return empleadosNoDisponibles;
    }

    public void setEmpleadosNoDisponibles(long empleadosNoDisponibles) {
        this.empleadosNoDisponibles = empleadosNoDisponibles;
    }

    public long getTotalPacientes() {
        return totalPacientes;
    }

    public void setTotalPacientes(long totalPacientes) {
        this.totalPacientes = totalPacientes;
    }

    public long getPacientesHospitalizados() {
        return pacientesHospitalizados;
    }

    public void setPacientesHospitalizados(long pacientesHospitalizados) {
        this.pacientesHospitalizados = pacientesHospitalizados;
    }

    public long getTotalHabitaciones() {
        return totalHabitaciones;
    }

    public void setTotalHabitaciones(long totalHabitaciones) {
        this.totalHabitaciones = totalHabitaciones;
    }

    public long getHabitacionesOcupadas() {
        return habitacionesOcupadas;
    }

    public void setHabitacionesOcupadas(long habitacionesOcupadas) {
        this.habitacionesOcupadas = habitacionesOcupadas;
    }

    public double getPorcentajePersonalDisponible() {
        return porcentajePersonalDisponible;
    }

    public void setPorcentajePersonalDisponible(double porcentajePersonalDisponible) {
        this.porcentajePersonalDisponible = porcentajePersonalDisponible;
    }

    public double getPorcentajeOcupacion() {
        return porcentajeOcupacion;
    }

    public void setPorcentajeOcupacion(double porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion;
    }
}
