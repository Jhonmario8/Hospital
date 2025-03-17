
package com.hospital.modelo.repositorio;


import com.hospital.modelo.entidad.UsuarioSesion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSesionRepositorio extends CrudRepository<UsuarioSesion,String> {
   
}
