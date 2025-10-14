package com.hospital.modelo.repositorio;

import com.hospital.modelo.dto.IngresoDto;
import com.hospital.modelo.entidad.Ingresos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresosRepositorio extends CrudRepository<Ingresos,Integer> {

    @Query("select new com.hospital.modelo.dto.IngresoDto(i.idIngreso,i.persona.idPersona,i.habitacion.numHabitacion, i.acompañante,i.hospitalizado) from Ingresos i where i.persona.idPersona=:id")
    IngresoDto buscarPorPaciente(@Param("id")int id);

    @Query("select count(i) from Ingresos i where i.hospitalizado = true")
    long contarPacientesHospitalizados();

    @Query("select count(distinct i.habitacion.numHabitacion) from Ingresos i where i.hospitalizado = true and i.habitacion is not null")
    long contarHabitacionesOcupadas();
}
