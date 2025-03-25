/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Paciente;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicio implements IPersonaServicio {
    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Override
    public List<Paciente> listarTodos(){
        return (List<Paciente>) personaRepositorio.findAll();
    }
    @Override
    public void guardar(Paciente paciente){
        personaRepositorio.save(paciente);
    }
    @Override
    public Paciente buscarPorId(Integer id){
     return personaRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        personaRepositorio.deleteById(id);
    }
}
