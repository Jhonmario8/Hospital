package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.repositorio.CitaRepositorio;
import com.hospital.modelo.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CitaServicio implements ICitaServicio {
    @Autowired
    private CitaRepositorio citaRepositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;
    @Override
    public List<Cita> listarTodos(){
        return (List<Cita>)citaRepositorio.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cita cita,int idPersona) {
        Persona per=personaRepositorio.findById(idPersona).orElse(null);
        if (per!=null){
            citaRepositorio.save(cita);

            per.getCitas().add(cita);
            cita.getPersonas().add(per);

            personaRepositorio.save(per);
            citaRepositorio.save(cita);
        }

    }
    @Override
    public void actualizar(Cita cita){
        citaRepositorio.save(cita);
    }

    @Override
    public Cita buscarPorId(Integer id){
        return citaRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        citaRepositorio.deleteById(id);
    }

}
