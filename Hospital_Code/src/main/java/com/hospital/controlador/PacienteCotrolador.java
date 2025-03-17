package com.hospital.controlador;

import com.hospital.modelo.entidad.Paciente;
import com.hospital.modelo.servicio.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PacienteCotrolador {

    @Autowired
    private IPacienteServicio servicio;
    @GetMapping("/gestionpacientes")
    public String gestion(){
        return "GestionPacientes/gestionpacientes";
    }

    @GetMapping("/guardapaciente")
    public String guarda(Model modelo){
        Paciente paciente=new Paciente();
        modelo.addAttribute("titulo","Formulario: Nuevo Paciente");
        modelo.addAttribute("paciente",paciente);
        return "GestionPacientes/guardapaciente";
    }
    @PostMapping("/guardarpaciente")
    public String guardar(@ModelAttribute Paciente paciente, Model modelo){
        modelo.addAttribute("titulo","Formulario: Nuevo Paciente");
        modelo.addAttribute("paciente",paciente);
        servicio.guardar(paciente);
        modelo.addAttribute("mensaje","Paciente Guardado");
        return "GestionPacientes/guardapaciente";
    }
    @GetMapping("/buscapaciente")
    public String busca(Model modelo){
       Paciente paciente=new Paciente();
        modelo.addAttribute("titulo","Formulario: Buscar Paciente");
        modelo.addAttribute("paciente",paciente);
        return "GestionPacientes/buscapaciente";
    }

    @PostMapping("/buscarpaciente")
    public String buscar(@RequestParam(name = "idPaciente")int idPaciente,Model modelo){
        modelo.addAttribute("titulo","Formulario:  Buscar Paciente");
        Paciente paciente=servicio.buscarPorId(idPaciente);
        if (paciente!=null){
            modelo.addAttribute("nombre",paciente.getNomPaciente());
            modelo.addAttribute("edad",paciente.getEdadPaciente());
            modelo.addAttribute("direccion",paciente.getDireccion());
            modelo.addAttribute("telefono",paciente.getTelefonoPaciente());
            modelo.addAttribute("eps",paciente.getEps());
            modelo.addAttribute("citas",paciente.getCitas());
            return "GestionPacientes/buscapaciente";
        }
        modelo.addAttribute("error","El paciente no se encontro");
        return "GestionPacientes/buscapaciente";
    }
    @GetMapping("/actualizapaciente")
    public String actualiza(Model modelo){
        Paciente paciente=new Paciente();
        modelo.addAttribute("titulo","Formulario: Editar Paciente");
        modelo.addAttribute("paciente",paciente);
        return "GestionPacientes/actualizapaciente";
    }

    @PostMapping("/actualizarpaciente")
    public String actualizar(@RequestParam(name = "idPaciente")int idPaciente,Model modelo){
        Paciente paciente=null;
        if (idPaciente > 0){
            paciente=servicio.buscarPorId(idPaciente);
            if (paciente==null){
                modelo.addAttribute("error","El paciente no se encontro");
                return "GestionPacientes/actualizapaciente";
            }
        }
        else {
            modelo.addAttribute("error","ERROR!!, formato  invalido");
            return "GestionPacientes/actualizapaciente";
        }
        modelo.addAttribute("titulo","Formulario: Editar Paciente");
        modelo.addAttribute("paciente",paciente);
        return "GestionPacientes/guardapaciente";
    }
    @GetMapping("/eliminapaciente")
    public String elimina(Model modelo){
        Paciente paciente=new Paciente();
        modelo.addAttribute("titulo","Formulario: Eliminar Paciente");
        modelo.addAttribute("paciente",paciente);
        return "GestionPacientes/eliminapaciente";
    }
    @PostMapping("/eliminarpaciente")
    public String eliminar(@RequestParam(name="idPaciente")int idPaciente,Model modelo){
        Paciente paciente=null;
        if (idPaciente  > 0){
            paciente=servicio.buscarPorId(idPaciente);
            if (paciente==null){
                modelo.addAttribute("error","El paciente no se encontro");
                return "GestionPacientes/eliminapaciente";
            }
        }
        else {
            modelo.addAttribute("error","ERROR!!, formato invalido");
            return "GestionPacientes/eliminapaciente";
        }
        servicio.eliminar(paciente.getIdPaciente());
        modelo.addAttribute("error","Paciente Eliminado");
        return "GestionPacientes/eliminapaciente";
    }
}
