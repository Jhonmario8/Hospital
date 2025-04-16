package com.hospital.modelo.servicio;


import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.repositorio.IngresosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresosServicio implements IIngresosServicio {
    @Autowired
    private IngresosRepositorio ingresosRepositorio;
    @Override
    public List<Ingresos> listarTodos(){
        return (List<Ingresos>) ingresosRepositorio.findAll();
    }
    @Override
    public void guardar(Ingresos ingreso){
        ingresosRepositorio.save(ingreso);
    }
    @Override
    public Ingresos buscarPorPaciente(Integer id){
        List <Ingresos> lista=(List<Ingresos>) ingresosRepositorio.findAll();
        for (Ingresos ing:lista){
            if (ing.getPersona().getIdPersona()==id){
                return ing;
            }
        }
        return null;
    }
    @Override
    public Ingresos buscarPorId(Integer id){
        return ingresosRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        ingresosRepositorio.deleteById(id);
    }
}
