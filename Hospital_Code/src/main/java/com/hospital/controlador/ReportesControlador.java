package com.hospital.controlador;

import com.hospital.modelo.entidad.*;
import com.hospital.modelo.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportesControlador {
@Autowired
    private IIngresosServicio ingresosServicio;
@Autowired
    private IPacienteServicio pacienteServicio;
@Autowired
    private IEmpleadoServicio empleadoServicio;
@Autowired
    private IArticuloServicio articuloServicio;
@Autowired
    private IHabitacionServicio habitacionServicio;
@Autowired
    private ICitaServicio citaServicio;

@GetMapping("/reportes")
public String reportes(){
    return "Reportes/reportes";
}

    @GetMapping("/reportepacientes")
    public String pacientes(Model modelo){
        List<Ingresos> pacientes = ingresosServicio.listarTodos();
        modelo.addAttribute("pacientes", pacientes);
        modelo.addAttribute("servicio", pacienteServicio);
        return "Reportes/reportepacientes";
    }
    @GetMapping("/reporteempleados")
    public String empleados(Model modelo){
    List<Empleado>empleados=empleadoServicio.listarTodos();
    modelo.addAttribute("empleados",empleados);
    return "Reportes/reporteempleados";
    }
    @GetMapping("/reportearticulos")
    public String articulos(Model modelo){
    List<Articulo> articulos=articuloServicio.listarTodos();
    modelo.addAttribute("articulos",articulos);
    return "Reportes/reportearticulos";
    }
    @GetMapping("/reportehabitaciones")
    public String habitaciones(Model modelo){
    List<Habitacion>habitaciones=habitacionServicio.listarTodos();
    modelo.addAttribute("habitaciones",habitaciones);
    return "Reportes/reportehabitaciones";
    }
    @GetMapping("/reportecitas")
    public String citas(Model modelo){
    List<Cita>citas=citaServicio.listarTodos();
    modelo.addAttribute("citas",citas);

    return "Reportes/reportecitas";
    }
}
