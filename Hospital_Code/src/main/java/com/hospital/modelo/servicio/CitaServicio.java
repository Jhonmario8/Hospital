package com.hospital.modelo.servicio;


import com.hospital.modelo.entidad.Cita;

import com.hospital.modelo.repositorio.CitaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CitaServicio implements ICitaServicio {
    @Autowired
    private CitaRepositorio citaRepositorio;
    @Override
    public List<Cita> listarTodos(){
        return (List<Cita>)citaRepositorio.findAll();
    }
    @Override
    @Transactional
    public void guardar(Cita cita) {
        citaRepositorio.save(cita);
    }

    @Override
    public Cita buscarPorId(Integer id){
        return citaRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        citaRepositorio.deleteById(id);    }

}
