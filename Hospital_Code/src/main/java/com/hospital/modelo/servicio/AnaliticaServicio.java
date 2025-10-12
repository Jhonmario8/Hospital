package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.EstadisticaDto;
import com.hospital.modelo.dto.SerieTemporalDto;
import com.hospital.modelo.repositorio.CitaRepositorio;
import com.hospital.modelo.repositorio.IngresosRepositorio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import com.hospital.modelo.repositorio.proyeccion.SerieTemporalProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliticaServicio implements IAnaliticaServicio {

    private final PersonaRepositorio personaRepositorio;
    private final IngresosRepositorio ingresosRepositorio;
    private final CitaRepositorio citaRepositorio;

    public AnaliticaServicio(PersonaRepositorio personaRepositorio,
                             IngresosRepositorio ingresosRepositorio,
                             CitaRepositorio citaRepositorio) {
        this.personaRepositorio = personaRepositorio;
        this.ingresosRepositorio = ingresosRepositorio;
        this.citaRepositorio = citaRepositorio;
    }

    @Override
    public List<EstadisticaDto> personasPorTipo() {
        return personaRepositorio.contarPorTipoPersona();
    }

    @Override
    public List<EstadisticaDto> ingresosPorEstado() {
        return ingresosRepositorio.contarPorEstadoHospitalizacion();
    }

    @Override
    public List<SerieTemporalDto> citasPorMes() {
        List<SerieTemporalProjection> resultados = citaRepositorio.contarCitasPorMes();
        return resultados.stream()
                .map(proyeccion -> new SerieTemporalDto(proyeccion.getPeriodo(), proyeccion.getTotal()))
                .toList();
    }
}
