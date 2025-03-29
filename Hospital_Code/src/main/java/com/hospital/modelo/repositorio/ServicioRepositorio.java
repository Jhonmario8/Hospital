package com.hospital.modelo.repositorio;


import com.hospital.modelo.entidad.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepositorio extends CrudRepository<Servicio,Integer> {
}
