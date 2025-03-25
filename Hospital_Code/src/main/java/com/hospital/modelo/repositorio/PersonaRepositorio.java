package com.hospital.modelo.repositorio;


import com.hospital.modelo.entidad.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends CrudRepository<Paciente,Integer> {
}
