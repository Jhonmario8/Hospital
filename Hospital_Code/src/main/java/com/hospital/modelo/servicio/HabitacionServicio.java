package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.repositorio.HabitacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionServicio implements IHabitacionServicio {

    @Autowired
    private HabitacionRepositorio habitacionRepositorio;

    @Override
    public List<Habitacion> listarTodos() {
        return (List<Habitacion>) habitacionRepositorio.findAll();
    }

    @Override
    public void guardar(Habitacion habitacion) {
        habitacionRepositorio.save(habitacion);
    }

    @Override
    public Habitacion buscarPorId(Integer id) {
        return habitacionRepositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        habitacionRepositorio.deleteById(id);
    }

   
}