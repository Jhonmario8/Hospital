package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.EstadisticaDto;
import com.hospital.modelo.dto.SerieTemporalDto;
import com.hospital.modelo.repositorio.CitaRepositorio;
import com.hospital.modelo.repositorio.IngresosRepositorio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
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
        return citaRepositorio.contarCitasPorMes();
    }
}
