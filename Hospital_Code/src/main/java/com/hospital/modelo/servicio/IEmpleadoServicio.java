
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Empleado;
import org.hibernate.sql.Update;

import java.util.List;

public interface IEmpleadoServicio {
    List<Empleado> listarTodos();
    void guardar(Empleado empleado);
    Empleado buscarPorId(Integer id);
    void eliminar(Integer id);

}
