package com.hospital.controlador;

import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.entidad.Paciente;
import com.hospital.modelo.entidad.Servicio;
import com.hospital.modelo.servicio.IIngresosServicio;
import com.hospital.modelo.servicio.IPacienteServicio;
import com.hospital.modelo.servicio.IServicioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class IngresoControlador {
    @Autowired
    private IPacienteServicio pacienteServicio;
    @Autowired
    private IIngresosServicio servicio;
    @Autowired
    private IServicioServicio servicioServicio;
    @GetMapping("/gestioningresos")
    public String gestion(){
        return "GestionIngresos/gestioningresos";
    }
    @GetMapping("/ingreso")
    public String ingreso(Model modelo){
        Ingresos ingreso=new Ingresos();
        modelo.addAttribute("ingreso",ingreso);
        return "GestionIngresos/registraringreso";
    }
    @PostMapping("/ingresar")
    public String ingresar(@ModelAttribute Ingresos ingreso,Model modelo){
        Paciente paciente=pacienteServicio.buscarPorId(ingreso.getIdPaciente());
        Ingresos ingresos=servicio.buscarPorId(ingreso.getIdPaciente());

        if (paciente!=null ) {
            modelo.addAttribute("ingreso", ingreso);
            modelo.addAttribute("mensaje", "Ingreso Realizado");
            servicio.guardar(ingreso);
            return "GestionIngresos/registraringreso";
        }else
            modelo.addAttribute("ingreso",ingreso);
        modelo.addAttribute("error","El id del paciente no existe");
        return "GestionIngresos/registraringreso";
    }
    @GetMapping("/salida")
    public String salida(Model modelo){
        Ingresos ingreso=new Ingresos();
        Paciente paciente=new Paciente();
        modelo.addAttribute("paciente",paciente);
        modelo.addAttribute("ingreso",ingreso);
        return "GestionIngresos/registrarsalida";
    }
    @PostMapping("/salir")
    public String salir(@RequestParam(name = "idPaciente")int id,Model modelo){
        Ingresos ingreso=null;
        Paciente paciente=null;
        if (id>0){
            ingreso=servicio.buscarPorId(id);
            paciente=pacienteServicio.buscarPorId(id);
            if (ingreso==null){
                modelo.addAttribute("error","El id ingresado no existe");
                modelo.addAttribute("paciente",paciente);

                return "GestionIngresos/registrarsalida";
            }
        }else{
            modelo.addAttribute("error","Formato invalido");
            return "GestionIngresos/registrarsalida";
        }
        modelo.addAttribute("ingreso",ingreso);
        modelo.addAttribute("paciente",paciente);
        double precio=0;
        for (Servicio servicio:paciente.getServicios()){
            precio+= servicio.getPrecioServicio();
        }
        List<Servicio> servicios = new ArrayList<>(paciente.getServicios());
        modelo.addAttribute("servicios",servicios);
        modelo.addAttribute("valor",precio);
        modelo.addAttribute("error","Salida Registrada");
        List<Servicio>servicioSet=servicioServicio.listarTodos();
        for (Servicio ser:servicioSet){
            ser.getPacientes().remove(paciente);
            servicioServicio.guardar(ser);
        }
        servicio.eliminar(id);
        paciente.getServicios().clear();
        pacienteServicio.guardar(paciente);
        return "GestionIngresos/registrarsalida";
    }
    @GetMapping("/hospitalizar")
    public String hospitalizar(Model modelo){
        Ingresos ingreso=new Ingresos();
        modelo.addAttribute("ingreso",ingreso);
        return "GestionIngresos/hospitalizacion";
    }
    @PostMapping("/hospitalizar")
    public String hospitalizar(@RequestParam(name = "idPaciente")int id,Model modelo,
                               @RequestParam(name = "motivo")String motivo){
        Ingresos ingreso=null;
        if (id>0){
            ingreso=servicio.buscarPorId(id);
            if (ingreso==null){
                modelo.addAttribute("error","El paciente no se encontro");
                return "GestionIngresos/hospitalizacion";
            }
        }else {
            modelo.addAttribute("error","Formato Invalido");
            return "GestionIngresos/hospitalizacion";
        }
        if (ingreso.getHospitalizado()){
            modelo.addAttribute("error","El paciente ya fue hospitalizado");
            return "GestionIngresos/hospitalizacion";
        }
        ingreso.setMotivo(motivo);
        servicio.guardar(ingreso);
        return "redirect:/habitaciones/"+ingreso.getIdPaciente();
    }
}