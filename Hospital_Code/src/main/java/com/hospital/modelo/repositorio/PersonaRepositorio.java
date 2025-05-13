package com.hospital.modelo.repositorio;



import com.hospital.modelo.dto.PersonaDto;
import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepositorio extends CrudRepository<Persona,Integer> {

    @Query("select new com.hospital.modelo.dto.PersonaDto(p.idPersona,p.nomPersona,p.edadPersona,p.direccion,p.telefonoPersona,p.tipoPersona) from Persona p where p.activo=true")
    List<PersonaDto> findAllPersonaDto();

    @Query("select new com.hospital.modelo.dto.PersonaDto(p.idPersona,p.nomPersona,p.edadPersona,p.direccion,p.telefonoPersona,p.tipoPersona) from Persona p where p.activo=true and p.idPersona=:id")
    PersonaDto findByIdDto(@Param("id") int id);

    @Query(value = """
     SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END
    FROM persona p
    INNER JOIN persona_cita pc ON p.id_persona = pc.cod_persona
    WHERE p.id_persona = :id
    """, nativeQuery = true)
    int tieneCitas(@Param("id") int id);

    @Query("SELECT new com.hospital.modelo.dto.ServicioDto(s.codServicio, s.nomServicio, s.precioServicio, s.detallesServicio) FROM Persona p JOIN p.servicios s WHERE p.idPersona = :id")
    List<ServicioDto> serviciosPersona(@Param("id") int id);

}
