package com.hospital.controlador;

import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.entidad.Paciente;
import com.hospital.modelo.entidad.Servicio;
import com.hospital.modelo.servicio.IIngresosServicio;
import com.hospital.modelo.servicio.IPacienteServicio;
import com.hospital.modelo.servicio.IServicioServicio;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServicionControlador {
    @Autowired
    private IServicioServicio service;
    @Autowired
    private IPacienteServicio pacienteServicio;
    @Autowired
    private IIngresosServicio iIngresosServicio;

    @GetMapping("/gestionservicios")
    public String gestion(){
        return "GestionServicios/gestionservicios";
    }

    @GetMapping("/guardaservicio")
    public String guarda(Model modelo){
        Servicio servicio=new Servicio();
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/guardaservicio";
    }
    @PostMapping("/guardarservicio")
    public String guardar(@ModelAttribute Servicio servicio,Model modelo){
        modelo.addAttribute("servicio",servicio);
        modelo.addAttribute("mensaje","Servicio Guardado");
        service.guardar(servicio);
        return "GestionServicios/guardaservicio";
    }
    @GetMapping("/buscaservicio")
    public String busca(Model modelo){
        Servicio servicio=new Servicio();
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/buscaservicio";
    }
    @PostMapping("/buscarservicio")
    public String buscar(@RequestParam(name = "idServicio")int id,Model modelo){
        Servicio servicio=null;
        if (id>0){
            servicio=service.buscarPorId(id);
            if (servicio==null){
                modelo.addAttribute("error","El id ingresado no existe");
                return "GestionServicios/buscaservicio";
            }
        }else {
            modelo.addAttribute("error","Formato Invalido");
            return "GestionServicios/buscaservicio";
        }
        modelo.addAttribute("servicio",servicio);
        modelo.addAttribute("nombre",servicio.getNomServicio());
        modelo.addAttribute("precio",servicio.getPrecioServicio());
        modelo.addAttribute("detalles",servicio.getDetallesServicio());
        modelo.addAttribute("pacientes",servicio.getPacientes());
        return "GestionServicios/buscaservicio";
    }
    @GetMapping("/actualizaservicio")
    public String actualiza(Model modelo){
        Servicio servicio=new Servicio();
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/actualizaservicio";
    }
    @PostMapping("/actualizarservicio")
    public String actualizar(@RequestParam(name="idServicio")int id,Model modelo){
        Servicio servicio=null;
        if (id>0){
            servicio=service.buscarPorId(id);
            if (servicio==null){
                modelo.addAttribute("error","El id ingresado no existe");
                return "GestionServicios/actualizaservicio";
            }
        }else {
            modelo.addAttribute("error","Formato Invalido");
            return "GestionServicios/actualizaservicio";
        }
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/actualizarservicio";
    }
    @GetMapping("/eliminaservicio")
    public String elimina(Model modelo){
        Servicio servicio=new Servicio();
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/eliminaservicio";
    }
    @PostMapping("/eliminarservicio")
    public String eliminar(@RequestParam(name = "idServicio")int id,Model modelo){
        Servicio servicio=null;
        if (id>0){
            servicio=service.buscarPorId(id);
            if (servicio==null){
                modelo.addAttribute("error","El id ingresado no existe");
                return "GestionServicios/eliminaservicio";
            }
        }else {
            modelo.addAttribute("error","Formato Invalido");
            return "GestionServicios/eliminaservicio";
        }
        service.eliminar(id);
        modelo.addAttribute("error","Servicio Eliminado");
        return "GestionServicios/eliminaservicio";
    }

    @GetMapping("/adquirirservicio/{codigo}")
    public String adquiere(@PathVariable("codigo") int idServicio, Model modelo){
        Servicio servicio=service.buscarPorId(idServicio);
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/adquirirservicio";
    }
    @PostMapping("/adquirir")
    public String adquirir(@RequestParam(name = "idServicio")int idServicio,
                           @RequestParam("idPaciente")int idPaciente,Model modelo){
        Servicio servicio=service.buscarPorId(idServicio);
        Paciente paciente=pacienteServicio.buscarPorId(idPaciente);
        Ingresos ingreso=iIngresosServicio.buscarPorId(idPaciente);
        if (ingreso!=null){
            servicio.getPacientes().add(paciente);
            paciente.getServicios().add(servicio);
            service.guardar(servicio);
            modelo.addAttribute("mensaje","servicio adquirido");
        }else {
            modelo.addAttribute("error","El paciente no ha sido ingresado");
        }
        modelo.addAttribute("servicio",servicio);
        return "GestionServicios/adquirirservicio";
    }
}
