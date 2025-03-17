package com.hospital.modelo.repositorio;

import com.hospital.modelo.entidad.Articulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepositorio extends CrudRepository<Articulo,Integer> {
}
