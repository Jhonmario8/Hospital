/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.modelo.servicio;


import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.repositorio.PersonaRepositorio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicio implements IPersonaServicio {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Override
    public List<Persona> listarTodos(){
        return (List<Persona>) personaRepositorio.findAll();
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
        return empleados;
    }
    @Override
    public List<Persona> listarPacientes(){
        List<Persona> pacientes=new ArrayList<>();
        for (Persona per: personaRepositorio.findAll()){
            if (!per.isTipoPersona()){
                pacientes.add(per);
            }
        }
        return pacientes;
    }

    @Override
    public Persona buscarPorId(Integer id){
     return personaRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        personaRepositorio.deleteById(id);
    }
}
