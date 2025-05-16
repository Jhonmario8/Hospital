/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.modelo.servicio;


import com.hospital.modelo.dto.PersonaDto;
import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicio implements IPersonaServicio {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    
    @Override
    public List<PersonaDto> listarTodos(){
        return personaRepositorio.findAllPersonaDto();
    }
    @Override
    public void guardar(Persona persona){
        personaRepositorio.save(persona);
    }
    @Override
    public List<PersonaDto> listarEmpleados(){
        return personaRepositorio.findAllPersonaDto().stream().filter(PersonaDto::isTipoPersona).toList();
    }
    @Override
    public List<PersonaDto> listarPacientes(){
        return personaRepositorio.findAllPersonaDto().stream().filter(personaDto -> !personaDto.isTipoPersona()).toList();
    }
    @Override
    public List<PersonaDto> findByIdContaining(String id){
        return personaRepositorio.findAllByIdPersonaContaining(id);
    }

    @Override
    public PersonaDto buscarPorId(Integer id){
        return personaRepositorio.findByIdDto(id);
    }
    @Override
    public void eliminar(Integer id){
        Optional<Persona> personaOpt=personaRepositorio.findById(id);
        if (personaOpt.isPresent()){
            Persona persona = personaOpt.get();
            persona.setActivo(false);
            personaRepositorio.save(persona);
        }
    }
    @Override
    public ResponseEntity<?> buscarInactivo(Integer id){
        Persona per= personaRepositorio.findById(id).filter(persona -> !persona.isActivo()).orElse(null);
        if (per!=null){
            return ResponseEntity.ok(per);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La persona no se encontro");
    }
    @Override
    public void activar(Integer id){
        Optional<Persona> personaOpt=personaRepositorio.findById(id);
        if (personaOpt.isPresent()){
            Persona per=personaOpt.get();
            per.setActivo(true);
            personaRepositorio.save(per);
        }
    }
    @Override
    public void actualizar(Persona persona){
        Optional<Persona> perOpt=personaRepositorio.findById(persona.getIdPersona()).filter(Persona::isActivo);
        if (perOpt.isPresent()) {
            Persona per=perOpt.get();
            per.setNomPersona(persona.getNomPersona());
            per.setDireccion(persona.getDireccion());
            per.setTelefonoPersona(persona.getTelefonoPersona());
            per.setEdadPersona(persona.getEdadPersona());
            per.setTipoPersona(persona.isTipoPersona());
            personaRepositorio.save(per);
        }
    }
    @Override
    public boolean tieneCita(Integer id){
        int response= personaRepositorio.tieneCitas(id);
        return response==1;
    }

    @Override
    public List<ServicioDto> serviciosPersona(Integer id){
        return personaRepositorio.serviciosPersona(id);
    }
}