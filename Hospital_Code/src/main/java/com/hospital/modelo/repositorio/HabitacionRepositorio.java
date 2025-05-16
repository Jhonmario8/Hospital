package com.hospital.modelo.repositorio;

import com.hospital.modelo.dto.HabitacionDto;
import com.hospital.modelo.entidad.Habitacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HabitacionRepositorio extends CrudRepository<Habitacion, Integer> {

    @Query("select new com.hospital.modelo.dto.HabitacionDto(h.numHabitacion,h.tipoHabitacion, h.capacidad)" +
            "from Habitacion h ")
    List<HabitacionDto> findAllDto();
    @Query("SELECT new com.hospital.modelo.dto.HabitacionDto(h.numHabitacion,h.tipoHabitacion, h.capacidad) " +
            "FROM Habitacion h " +
            "WHERE str(h.numHabitacion) LIKE %:id%")
    List<HabitacionDto> findAllByIdContaining(@Param("id") String id);

}
