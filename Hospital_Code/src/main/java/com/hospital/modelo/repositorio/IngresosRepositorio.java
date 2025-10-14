package com.hospital.modelo.repositorio;

import com.hospital.modelo.dto.EstadisticaDto;
import com.hospital.modelo.dto.IngresoDto;
import com.hospital.modelo.entidad.Ingresos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngresosRepositorio extends CrudRepository<Ingresos,Integer> {

    @Query("select new com.hospital.modelo.dto.IngresoDto(i.idIngreso,i.persona.idPersona,i.habitacion.numHabitacion, i.acompañante,i.hospitalizado) from Ingresos i where i.persona.idPersona=:id")
    IngresoDto buscarPorPaciente(@Param("id")int id);

    @Query("SELECT new com.hospital.modelo.dto.EstadisticaDto(CASE WHEN i.hospitalizado = true THEN 'Hospitalizados' ELSE 'Ambulatorios' END, COUNT(i)) " +
            "FROM Ingresos i GROUP BY i.hospitalizado")
    List<EstadisticaDto> contarPorEstadoHospitalizacion();
}
