package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.EstadisticaDto;
import com.hospital.modelo.dto.SerieTemporalDto;

import java.util.List;

public interface IAnaliticaServicio {

    List<EstadisticaDto> personasPorTipo();

    List<EstadisticaDto> ingresosPorEstado();

    List<SerieTemporalDto> citasPorMes();
}
