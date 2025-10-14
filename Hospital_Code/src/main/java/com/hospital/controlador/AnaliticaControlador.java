package com.hospital.controlador;

import com.hospital.modelo.dto.EstadisticaDto;
import com.hospital.modelo.dto.SerieTemporalDto;
import com.hospital.modelo.servicio.IAnaliticaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/datos")
public class AnaliticaControlador {

    private final IAnaliticaServicio analiticaServicio;

    public AnaliticaControlador(IAnaliticaServicio analiticaServicio) {
        this.analiticaServicio = analiticaServicio;
    }

    @GetMapping("/personas-tipo")
    public List<EstadisticaDto> personasPorTipo() {
        return analiticaServicio.personasPorTipo();
    }

    @GetMapping("/ingresos-estado")
    public List<EstadisticaDto> ingresosPorEstado() {
        return analiticaServicio.ingresosPorEstado();
    }

    @GetMapping("/citas-mensuales")
    public List<SerieTemporalDto> citasPorMes() {
        return analiticaServicio.citasPorMes();
    }
}
