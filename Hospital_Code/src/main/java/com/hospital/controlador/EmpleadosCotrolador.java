package com.hospital.controlador;

import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Empleado;
import com.hospital.modelo.servicio.IEmpleadoServicio;
import org.hibernate.mapping.Set;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpleadosCotrolador {
    @Autowired
    private IEmpleadoServicio servicio;

    @GetMapping("/gestionempleados")
    public String gestion() {
        return "GestionEmpleados/gestionempleados";
    }

    @GetMapping("/guardaempleado")
    public String guarda(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("titulo", "Formulario: Nuevo Empleado");
        modelo.addAttribute("empleado", empleado);
        return "GestionEmpleados/guardarempleado";
    }

    @PostMapping("/guardarempleado")
    public String guardar(@ModelAttribute Empleado empleado, Model modelo) {
        modelo.addAttribute("titulo", "Formulario: Nuevo Empleado");
        modelo.addAttribute("empleado", empleado);
        servicio.guardar(empleado);
        modelo.addAttribute("mensaje", "Empleado Registrado");
        return "GestionEmpleados/guardarempleado";
    }

    @GetMapping("/buscaempleado")
    public String busca(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("titulo", "Formulario: Buscar Empleado");
        modelo.addAttribute("empleado", empleado);
        return "GestionEmpleados/buscarempleado";
    }

    @PostMapping("/buscarempleado")
    public String buscar(@RequestParam(name = "idEmpleado") int idEmpleado, Model modelo) {
        modelo.addAttribute("titulo", "Formulario: Buscar Empleado");
        Empleado empleado = servicio.buscarPorId(idEmpleado);
        if (empleado != null) {
            modelo.addAttribute("nombre", empleado.getNomEmpleado());
            modelo.addAttribute("apellido", empleado.getApeEmpleado());
            modelo.addAttribute("edad", empleado.getEdadEmpleado());
            modelo.addAttribute("telefono", empleado.getTelefonoEmpleado());
            modelo.addAttribute("fecha", empleado.getFechaEmpleado());
            modelo.addAttribute("citas",empleado.getCitas());
            return "GestionEmpleados/buscarempleado";
        } else {
            modelo.addAttribute("error", "El empleado no se encontro");
            return "GestionEmpleados/buscarempleado";
        }
    }

    @GetMapping("/actualizaempleado")
    public String actualiza(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("titulo", "Formulario: Actualizar Empleado");
        modelo.addAttribute("empleado", empleado);
        return "GestionEmpleados/actualizarempleado";
    }

    @PostMapping("/actualizarempleado")
    public String actualizar(@RequestParam(name = "idEmpleado") int idEmpleado, Model modelo) {
        Empleado empleado = null;
        if (idEmpleado > 0) {
            empleado = servicio.buscarPorId(idEmpleado);
            if (empleado == null) {
                modelo.addAttribute("error", "El id ingresado no existe");
                return "GestionEmpleados/actualizarempleado";
            }
        } else {
            modelo.addAttribute("error", "Error: formato invalido");
            return "GestionEmpleados/actualizarempleado";
        }
        modelo.addAttribute("titulo", "Formulario: Editar Empleado");
        modelo.addAttribute("empleado", empleado);
        return "GestionEmpleados/guardarempleado";
    }

    @GetMapping("/eliminaempleado")
    public String elimina(Model modelo) {
        Empleado empleado = new Empleado();
        modelo.addAttribute("titulo", "Formulario: Eliminar Empleado");
        modelo.addAttribute("empleado", empleado);
        return "GestionEmpleados/eliminarempleado";
    }

    @PostMapping("/eliminarempleado")
    public String eliminar(@RequestParam(name = "idEmpleado") int idEmpleado, Model modelo) {
        Empleado empleado = null;
        if (idEmpleado > 0) {
            empleado = servicio.buscarPorId(idEmpleado);
            if (empleado == null) {
                modelo.addAttribute("error", "El id ingresado no existe");
                return "GestionEmpleados/eliminarempleado";
            }
        } else {
            modelo.addAttribute("error", "Error: formato invalido");
            return "GestionEmpleados/eliminarempleado";
        }
        servicio.eliminar(empleado.getIdEmpleado());
        modelo.addAttribute("error", "Empleado Eliminado");
        return "GestionEmpleados/eliminarempleado";
    }
}