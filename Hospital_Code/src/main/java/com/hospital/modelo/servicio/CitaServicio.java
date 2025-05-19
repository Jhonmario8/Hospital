package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.CitaDto;
import com.hospital.modelo.dto.PersonaDto;
import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.repositorio.CitaRepositorio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitaServicio implements ICitaServicio {

    private final CitaRepositorio citaRepositorio;


    private final PersonaRepositorio personaRepositorio;

    public CitaServicio(CitaRepositorio citaRepositorio, PersonaRepositorio personaRepositorio) {
        this.citaRepositorio = citaRepositorio;
        this.personaRepositorio = personaRepositorio;
    }

    @Override
    public List<CitaDto> listarTodos(){
        List<CitaDto> citas=new ArrayList<>();
       var respuesta=citaRepositorio.findAll();
        for (Cita cita : respuesta) {
            CitaDto citaDto=new CitaDto();
            citaDto.setIdCita(cita.getIdCita());
            citaDto.setFechaCita(cita.getFechaCita());
            citaDto.setHoraCita(cita.getHoraCita());
            citaDto.setMotivo(cita.getMotivo());
            citaDto.setPersonas(mapPersonaToDto(cita.getPersonas()));
            citas.add(citaDto);
        }
        return citas;
    }
    public List<PersonaDto> mapPersonaToDto(List<Persona> personas){
        List<PersonaDto> personasDto =new ArrayList<>();
        for (Persona persona : personas) {
            PersonaDto personaDto=new PersonaDto();
            personaDto.setIdPersona(persona.getIdPersona());
            personaDto.setNomPersona(persona.getNomPersona());
            personaDto.setEdadPersona(persona.getEdadPersona());
            personaDto.setTelefonoPersona(persona.getTelefonoPersona());
            personaDto.setTipoPersona(persona.isTipoPersona());
            personaDto.setDireccion(personaDto.getDireccion());
            personasDto.add(personaDto);
        }
        return personasDto;
    }

    @Override
    @Transactional
    public void guardar(Cita cita,int idPersona) {
        Persona per=personaRepositorio.findById(idPersona).orElse(null);
        if (per!=null){
            citaRepositorio.save(cita);
            per.getCitas().add(cita);
            cita.getPersonas().add(per);
            personaRepositorio.save(per);
            citaRepositorio.save(cita);
        }
    }
    @Override
    public void actualizar(Cita cita){
        citaRepositorio.save(cita);
    }

    @Override
    public Cita buscarPorId(Integer id){
        return citaRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        citaRepositorio.deleteById(id);
    }

}
