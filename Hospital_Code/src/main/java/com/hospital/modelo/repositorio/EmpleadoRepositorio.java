package com.hospital.modelo.repositorio;

import com.hospital.modelo.entidad.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends CrudRepository<Empleado,Integer> {
}
