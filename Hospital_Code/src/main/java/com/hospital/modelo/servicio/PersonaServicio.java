/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.modelo.servicio;


import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.repositorio.PersonaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicio implements IPersonaServicio {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Override
    public List<Persona> listarTodos(){
        List<Persona> personas=(List<Persona>) personaRepositorio.findAll();
        return personas.stream().filter(Persona::isActivo).toList();
    }
    @Override
    public void guardar(Persona persona){
        personaRepositorio.save(persona);
    }
    @Override
    public List<Persona> listarEmpleados(){
        List<Persona> empleados=new ArrayList<>();
        for (Persona per: personaRepositorio.findAll()){
            if (per.isTipoPersona()){
                empleados.add(per);
            }
        }
        return empleados.stream().filter(Persona::isActivo).toList();
    }
    @Override
    public List<Persona> listarPacientes(){
        List<Persona> pacientes=new ArrayList<>();
        for (Persona per: personaRepositorio.findAll()){
            if (!per.isTipoPersona()){
                pacientes.add(per);
            }
        }
        return pacientes.stream().filter(Persona::isActivo).toList();
    }

    @Override
    public Persona buscarPorId(Integer id){
        return personaRepositorio.findById(id).filter(Persona::isActivo).orElse(null);
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
    public Persona buscarInactivo(Integer id){
        return personaRepositorio.findById(id).filter(persona -> !persona.isActivo()).orElse(null);
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
}