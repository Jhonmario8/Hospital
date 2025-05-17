package com.hospital.modelo.servicio;


import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.entidad.Servicio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import com.hospital.modelo.repositorio.ServicioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioServicio implements  IServicioServicio{

    private  final ServicioRepositorio servicioRepositorio;

    private  final PersonaRepositorio personaRepositorio;

    public ServicioServicio(ServicioRepositorio servicioRepositorio, PersonaRepositorio personaRepositorio) {
        this.servicioRepositorio = servicioRepositorio;
        this.personaRepositorio = personaRepositorio;
    }

    @Override
    public List<ServicioDto> listarTodos(){
        return servicioRepositorio.listarServiciosDto();
    }
    @Override
    public void guardar(Servicio servicio){
        servicioRepositorio.save(servicio);
    }
    @Override
    public void adquirir(int idServicio,int idPaciente){
        Persona per=personaRepositorio.findById(idPaciente).orElse(null);
        Servicio ser=servicioRepositorio.findById(idServicio).orElse(null);
        if (per!=null && ser!=null){
            per.getServicios().add(ser);
            ser.getPersonas().add(per);
            personaRepositorio.save(per);
        }

    }
    @Override
    public double cuenta(Integer idPaciente) {
        Persona per = personaRepositorio.findById(idPaciente).orElse(null);
        double cant = 0;

        if (per != null) {
            for (Servicio servicio : new ArrayList<>(per.getServicios())) {
                cant += servicio.getPrecioServicio();
                servicio.getPersonas().remove(per);
            }
            per.getServicios().clear();

            personaRepositorio.save(per);
        }

        return cant;
    }

    @Override
    public Servicio buscarPorId(Integer id){
        return servicioRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        servicioRepositorio.deleteById(id);
    }
}
