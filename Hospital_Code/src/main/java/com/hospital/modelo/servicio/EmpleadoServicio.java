package com.hospital.modelo.servicio;


import com.hospital.modelo.entidad.Empleado;
import com.hospital.modelo.repositorio.EmpleadoRepositorio;
import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServicio implements IEmpleadoServicio{
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    @Override
    public List<Empleado> listarTodos(){
        return (List<Empleado>)empleadoRepositorio.findAll();
    }
    @Override
    public void guardar(Empleado empleado){
        empleadoRepositorio.save(empleado);
    }
    @Override
    public Empleado buscarPorId(Integer id){
     return empleadoRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        empleadoRepositorio.deleteById(id);
    }

}
