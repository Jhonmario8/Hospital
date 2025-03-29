package com.hospital.modelo.repositorio;

import com.hospital.modelo.entidad.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface HabitacionRepositorio extends CrudRepository<Habitacion, Integer> {

}
