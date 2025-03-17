package com.hospital.controlador;

import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Empleado;
import com.hospital.modelo.entidad.Paciente;
import com.hospital.modelo.servicio.ICitaServicio;
import com.hospital.modelo.servicio.IEmpleadoServicio;
import com.hospital.modelo.servicio.IPacienteServicio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CitaCotrolador {
    @Autowired
    private ICitaServicio servicio;
    @Autowired
    private IEmpleadoServicio empleadoServicio;
    @Autowired
    private IPacienteServicio pacienteServicio;
    @GetMapping("/gestioncitas")
    public String gestion() {
        return "GestionCitas/gestioncitas";
    }

    @GetMapping("/guardacita")
    public String guarda(Model modelo) {
        Cita cita = new Cita();
        modelo.addAttribute("titulo", "Formulario: Guardar Cita");
        modelo.addAttribute("cita", cita);
        return "GestionCitas/guardacita";
    }

    @PostMapping("/guardarcita")
    public String guardarCita(@ModelAttribute Cita cita,
                              @RequestParam(name = "idPaciente") int idPaciente,Model  modelo) {
        
       Paciente paciente=pacienteServicio.buscarPorId(idPaciente);
        if (paciente==null) {
         modelo.addAttribute("error","El id del paciente no existe");
         return "GestionCitas/guardacita";
        }
        cita.setPaciente(pacienteServicio.buscarPorId(idPaciente));
        servicio.guardar(cita);
        return "redirect:/asignaempleados?citaId=" + cita.getIdCita();
    }

    @GetMapping("/asignaempleados")
    public String asignaEmpleados(@RequestParam("citaId") int citaId, Model modelo) {
        Cita cita = servicio.buscarPorId(citaId);
        modelo.addAttribute("titulo", "Asignar Empleados a Cita");
        modelo.addAttribute("cita", cita);
        return "GestionCitas/asignaempleados";
    }


    @PostMapping("/asignaempleados")
    public String guardarEmpleados(@RequestParam("citaId") int citaId,
                                   @RequestParam("idEmpleado") int idEmpleado,
                                   Model modelo) {
        Cita cita = servicio.buscarPorId(citaId);
        Empleado empleado = empleadoServicio.buscarPorId(idEmpleado);

        if (empleado != null) {
            cita.getEmpleados().add(empleado);
            empleado.getCitas().add(cita);
            servicio.guardar(cita);
            modelo.addAttribute("mensaje", "Empleado asignado correctamente.");
        } else {
            modelo.addAttribute("error", "El empleado no existe.");
        }
        modelo.addAttribute("cita", cita);
        return "GestionCitas/asignaempleados";
    }

    @GetMapping("/buscacita")
    public String busca(Model modelo) {
        Cita cita = new Cita();

        modelo.addAttribute("titulo", "Formulario: Buscar Cita");
        modelo.addAttribute("cita", cita);

        return "GestionCitas/buscacita";
    }

    @PostMapping("/buscarcita")
    public String buscar(@RequestParam(name = "idCita") int idCita, Model modelo) {
        Cita cita = null;
        modelo.addAttribute("titulo", "Formulario: Buscar Cita");
        if (idCita > 0) {
            cita = servicio.buscarPorId(idCita);
            if (cita == null) {
                modelo.addAttribute("error", "El id no existe");
                return "GestionCitas/buscacita";
            }
        } else {
            modelo.addAttribute("error", "Formato invalido");
        }
        modelo.addAttribute("cita", cita);
        modelo.addAttribute("fecha", cita.getFechaCita());
        modelo.addAttribute("hora", cita.getHoraCita());
        modelo.addAttribute("paciente", cita.getPaciente().getNomPaciente());
        modelo.addAttribute("empleados", cita.getEmpleados());
        return "GestionCitas/buscacita";
    }

    @GetMapping("/actualizacita")
    public String actualiza(Model modelo) {
        Cita cita = new Cita();
        modelo.addAttribute("cita", cita);
 
        return "GestionCitas/actualizacita";
    }

    @PostMapping("/actualizarcita")
    public String actualizar(@RequestParam(name = "idCita") int idCita, Model modelo) {
            Cita cita=null;
            if (idCita>0){
                cita=servicio.buscarPorId(idCita);
                if (cita==null){
                    modelo.addAttribute("error","El id ingresado no existe");
                }
            }
            else {
                modelo.addAttribute("error","ERROR, Formato invalido");
            }
            modelo.addAttribute("cita",cita);
            modelo.addAttribute("empleados",cita.getEmpleados());
        return "GestionCitas/actualizarcitas";
    }
    @GetMapping("/eliminacita")
    public String elimina(Model modelo){
        Cita cita=new Cita();
        modelo.addAttribute("cita",cita);
        return "GestionCitas/eliminacita";
    }
    @PostMapping("/eliminarcita")
    public String eliminar(@RequestParam(name = "idCita")int idCita, Model modelo){
        Cita cita=null;
        if (idCita>0){
            cita=servicio.buscarPorId(idCita);
            modelo.addAttribute("error","Cita eliminada");
            if (cita==null){
                modelo.addAttribute("error","El id ingresado no existe");
            }
        }else{
            modelo.addAttribute("error","Formato invalido");
        }
        servicio.eliminar(idCita);

        return "GestionCitas/eliminacita";
    }
}
