package com.hospital.modelo.repositorio;


import com.hospital.modelo.dto.SerieTemporalDto;
import com.hospital.modelo.entidad.Cita;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepositorio extends CrudRepository<Cita,Integer> {

    @Query("SELECT new com.hospital.modelo.dto.SerieTemporalDto(FUNCTION('FORMAT', c.fechaCita, 'yyyy-MM'), COUNT(c)) " +
            "FROM Cita c GROUP BY FUNCTION('FORMAT', c.fechaCita, 'yyyy-MM') ORDER BY FUNCTION('FORMAT', c.fechaCita, 'yyyy-MM')")
    List<SerieTemporalDto> contarCitasPorMes();
}
