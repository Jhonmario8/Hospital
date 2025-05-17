package com.hospital.modelo.servicio;


import com.hospital.modelo.dto.IngresoDto;
import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.repositorio.HabitacionRepositorio;
import com.hospital.modelo.repositorio.IngresosRepositorio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngresosServicio implements IIngresosServicio {

    private final IngresosRepositorio ingresosRepositorio;

    private final HabitacionRepositorio habitacionRepositorio;

    private final PersonaRepositorio personaRepositorio;

    public IngresosServicio(IngresosRepositorio ingresosRepositorio, HabitacionRepositorio habitacionRepositorio, PersonaRepositorio personaRepositorio) {
        this.ingresosRepositorio = ingresosRepositorio;
        this.habitacionRepositorio = habitacionRepositorio;
        this.personaRepositorio = personaRepositorio;
    }

    @Override
    public List<IngresoDto> listarTodos(){
        List<IngresoDto> ingresoDtos=new ArrayList<>();
        var ingresos= ingresosRepositorio.findAll();
        for (Ingresos ingreso : ingresos) {
            IngresoDto ingresoDto=new IngresoDto();
            ingresoDto.setIdIngreso(ingreso.getIdIngreso());
            ingresoDto.setHospitalizado(ingreso.getHospitalizado());
            ingresoDto.setAcompañante(ingreso.getAcompañante());
            ingresoDto.setIdPersona(ingreso.getPersona().getIdPersona());
            ingresoDto.setIdHabitacion(ingreso.getHabitacion().getNumHabitacion());
            ingresoDtos.add(ingresoDto);
        }
        return ingresoDtos;
    }
    @Override
    public void guardar(IngresoDto ingresoDto){
        Ingresos ingresos=new Ingresos();
        ingresos.setHospitalizado(ingresoDto.getHospitalizado());
        Optional<Persona> byId = personaRepositorio.findById(ingresoDto.getIdPersona());
        if (byId.isPresent()) {
            ingresos.setPersona(byId.get());
        }
        ingresos.setAcompañante(ingresoDto.getAcompañante());
        ingresosRepositorio.save(ingresos);
    }
    @Override
    public IngresoDto buscarPorPaciente(Integer id){
            return ingresosRepositorio.buscarPorPaciente(id);
    }
    @Override
    public void asignar(Integer idIngreso,Integer idHabitacion){
        Habitacion hab=habitacionRepositorio.findById(idHabitacion).orElse(null);
        Ingresos ing=ingresosRepositorio.findById(idIngreso).orElse(null);
        if (hab!=null && ing!=null){
            hab.getIngresos().add(ing);
            ing.setHabitacion(hab);
            ingresosRepositorio.save(ing);
        }
    }
    @Override
    public Ingresos buscarPorId(Integer id){
        return ingresosRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        ingresosRepositorio.deleteById(id);
    }
    @Override
    public void actualizar(IngresoDto ingresoDto){
        Optional<Ingresos> byId = ingresosRepositorio.findById(ingresoDto.getIdIngreso());
        if (byId.isPresent()){
            Ingresos ingresos=byId.get();
            Optional<Persona> perOpt = personaRepositorio.findById(ingresoDto.getIdPersona());
            if (perOpt.isPresent()) {
                ingresos.setPersona(perOpt.get());
            }
            ingresos.setAcompañante(ingresoDto.getAcompañante());
            ingresos.setHospitalizado(ingresoDto.getHospitalizado());
            ingresosRepositorio.save(ingresos);
        }
    }
}
