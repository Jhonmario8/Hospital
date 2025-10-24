package com.hospital.modelo.repositorio;


import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Servicio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepositorio extends CrudRepository<Servicio,Integer> {
    @Query("select new com.hospital.modelo.dto.ServicioDto(s.codServicio,s.nomServicio,s.precioServicio,s.detallesServicio) from Servicio s")
    List <ServicioDto> listarServiciosDto();
}
