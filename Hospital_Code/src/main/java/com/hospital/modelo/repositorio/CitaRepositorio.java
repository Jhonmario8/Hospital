package com.hospital.modelo.repositorio;


import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.repositorio.proyeccion.SerieTemporalProjection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepositorio extends CrudRepository<Cita,Integer> {

    @Query("SELECT FUNCTION('FORMAT', c.fechaCita, 'yyyy-MM') AS periodo, COUNT(c) AS total " +
            "FROM Cita c GROUP BY FUNCTION('FORMAT', c.fechaCita, 'yyyy-MM') ORDER BY FUNCTION('FORMAT', c.fechaCita, 'yyyy-MM')")
    List<SerieTemporalProjection> contarCitasPorMes();
}
