package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.ArticuloDto;
import com.hospital.modelo.dto.HabitacionDto;
import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.repositorio.HabitacionRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionServicio implements IHabitacionServicio {


    private final HabitacionRepositorio habitacionRepositorio;

    public HabitacionServicio(HabitacionRepositorio habitacionRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
    }

    @Override
    public List<HabitacionDto> listarTodos() {
        return habitacionRepositorio.findAllDto();
    }
    @Override
    public List<HabitacionDto> findByIdContaining(String id){
        return habitacionRepositorio.findAllByIdContaining(id);
    }

    @Override
    public Habitacion guardar(Habitacion habitacion) {
       return habitacionRepositorio.save(habitacion);
    }

    @Override
    public Habitacion buscarPorId(Integer id) {
        return habitacionRepositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        habitacionRepositorio.deleteById(id);
    }

   @Override
    public List<ArticuloDto> findArticulosById(Integer id){
        return habitacionRepositorio.findArticulosById(id);
   }
}