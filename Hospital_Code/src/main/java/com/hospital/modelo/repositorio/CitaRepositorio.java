package com.hospital.modelo.repositorio;


import com.hospital.modelo.dto.CitaDto;
import com.hospital.modelo.entidad.Cita;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitaRepositorio extends CrudRepository<Cita,Integer> {

}
