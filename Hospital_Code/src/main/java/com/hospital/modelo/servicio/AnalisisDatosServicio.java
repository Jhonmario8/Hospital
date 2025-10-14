package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.AnalisisDatosDto;
import com.hospital.modelo.repositorio.HabitacionRepositorio;
import com.hospital.modelo.repositorio.IngresosRepositorio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AnalisisDatosServicio implements IAnalisisDatosServicio {

    private final PersonaRepositorio personaRepositorio;
    private final HabitacionRepositorio habitacionRepositorio;
    private final IngresosRepositorio ingresosRepositorio;

    public AnalisisDatosServicio(PersonaRepositorio personaRepositorio,
                                 HabitacionRepositorio habitacionRepositorio,
                                 IngresosRepositorio ingresosRepositorio) {
        this.personaRepositorio = personaRepositorio;
        this.habitacionRepositorio = habitacionRepositorio;
        this.ingresosRepositorio = ingresosRepositorio;
    }

    @Override
    public AnalisisDatosDto obtenerResumen() {
        long totalPersonas = personaRepositorio.contarPersonas();
        long totalEmpleados = personaRepositorio.contarEmpleados();
        long empleadosDisponibles = personaRepositorio.contarEmpleadosActivos();
        long empleadosNoDisponibles = personaRepositorio.contarEmpleadosInactivos();
        long totalPacientes = personaRepositorio.contarPacientes();
        long pacientesHospitalizados = ingresosRepositorio.contarPacientesHospitalizados();
        long totalHabitaciones = habitacionRepositorio.count();
        long habitacionesOcupadas = ingresosRepositorio.contarHabitacionesOcupadas();

        double porcentajePersonalDisponible = totalEmpleados == 0 ? 0 : (double) empleadosDisponibles / totalEmpleados * 100;
        double porcentajeOcupacion = totalHabitaciones == 0 ? 0 : (double) habitacionesOcupadas / totalHabitaciones * 100;

        AnalisisDatosDto dto = new AnalisisDatosDto();
        dto.setTotalPersonas(totalPersonas);
        dto.setTotalEmpleados(totalEmpleados);
        dto.setEmpleadosDisponibles(empleadosDisponibles);
        dto.setEmpleadosNoDisponibles(empleadosNoDisponibles);
        dto.setTotalPacientes(totalPacientes);
        dto.setPacientesHospitalizados(pacientesHospitalizados);
        dto.setTotalHabitaciones(totalHabitaciones);
        dto.setHabitacionesOcupadas(habitacionesOcupadas);
        dto.setPorcentajePersonalDisponible(porcentajePersonalDisponible);
        dto.setPorcentajeOcupacion(porcentajeOcupacion);
        return dto;
    }
}
