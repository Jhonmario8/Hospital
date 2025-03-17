package com.hospital.modelo.repositorio;

import com.hospital.modelo.entidad.Ingresos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresosRepositorio extends CrudRepository<Ingresos,Integer> {
}
