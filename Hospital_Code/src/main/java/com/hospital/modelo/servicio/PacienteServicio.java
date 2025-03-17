/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Paciente;
import com.hospital.modelo.repositorio.PacienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServicio implements IPacienteServicio {
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Override
    public List<Paciente> listarTodos(){
        return (List<Paciente>)pacienteRepositorio.findAll();
    }
    @Override
    public void guardar(Paciente paciente){
        pacienteRepositorio.save(paciente);
    }
    @Override
    public Paciente buscarPorId(Integer id){
     return pacienteRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        pacienteRepositorio.deleteById(id);
    }
}
