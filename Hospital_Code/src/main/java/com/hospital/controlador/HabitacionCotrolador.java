package com.hospital.controlador;

import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.servicio.IHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HabitacionCotrolador {
    @Autowired
    private IHabitacionServicio servicio;

    @GetMapping("/gestionhabitaciones")
    public String gestion() {
        return "GestionHabitaciones/gestionhabitaciones";
    }

    @GetMapping("/guardahabitacion")
    public String guarda(Model modelo) {
        Habitacion habitacion=new Habitacion();
        modelo.addAttribute("titulo","Formulario: Guardar Habitacion");
        modelo.addAttribute("habitacion",habitacion);
        return "GestionHabitaciones/guardahabitacion";
    }

    @PostMapping("/guardarhabitacion")
    public String guardar(@ModelAttribute Habitacion habitacion, Model modelo){
        modelo.addAttribute("titulo","Formulario: Nueva Habitacion");
        modelo.addAttribute("habitacion",habitacion);

        servicio.guardar(habitacion);
        modelo.addAttribute("mensaje","Habitacion Guardada\n" +
                "Numero de la habitacion: "+habitacion.getNumHabitacion());
        return "GestionHabitaciones/guardahabitacion";
    }

    @GetMapping("/buscahabitacion")
    public String busca(Model modelo){
        Habitacion habitacion=new Habitacion();
        modelo.addAttribute("titulo","Formulario: Buscar Habitacion");
        modelo.addAttribute("habitacion",habitacion);
        return "GestionHabitaciones/buscahabitacion";
    }

    @PostMapping("/buscarhabitacion")
    public String buscar(@RequestParam(name = "numHabitacion")int numHabitacion,Model modelo){
        modelo.addAttribute("titulo","Formulario: Buscar Habitacion");
        Habitacion habitacion=servicio.buscarPorId(numHabitacion);
        if (habitacion!=null){
            modelo.addAttribute("tipo",habitacion.getTipoHabitacion());
            modelo.addAttribute("ocupacion",habitacion.getOcupacion());
            return "GestionHabitaciones/buscahabitacion";
        }
        else {
            modelo.addAttribute("error","El id ingresado no existe");
            return "GestionHabitaciones/buscahabitacion";
        }
    }

    @GetMapping("/actualizahabitacion")
    public String actualiza(Model modelo){
        Habitacion habitacion=new Habitacion();
        modelo.addAttribute("titulo","Formulario: Actualizar Habitacion");
        modelo.addAttribute("habitacion",habitacion);
        return "GestionHabitaciones/actualizahabitacion";
    }

    @PostMapping("/actualizarhabitacion")
    public String actualizar(@RequestParam(name = "numHabitacion")int numHabitacion,Model modelo){
        Habitacion habitacion=null;
        if (numHabitacion > 0){
            habitacion=servicio.buscarPorId(numHabitacion);
            if (habitacion==null){
                modelo.addAttribute("error","El id ingresado no existe");
                return "GestionHabitaciones/actualizahabitacion";
            }
        }
        else{
            modelo.addAttribute("error","ERROR!!, formato invalido");
        }
        modelo.addAttribute("titulo","Editar Habitacion");
        modelo.addAttribute("habitacion",habitacion);
        return "GestionHabitaciones/actualizarhabitacion";
    }
    @GetMapping("/eliminahabitacion")
    public String elimina(Model modelo){
        Habitacion habitacion=new Habitacion();
        modelo.addAttribute("titulo","Formulario: Eliminar Habitacion");
        modelo.addAttribute("habitacion",habitacion);
        return "GestionHabitaciones/eliminahabitacion";
    }
    @PostMapping("eliminarhabitacion")
    public String eliminar(@RequestParam(name="numHabitacion")int numHabitacion,Model modelo){
        Habitacion habitacion = null;
        if (numHabitacion > 0) {
            habitacion = servicio.buscarPorId(numHabitacion);
            if (habitacion == null) {
                modelo.addAttribute("error", "El id ingresado no existe");
                return "GestionHabitaciones/eliminahabitacion";
            }
        } else {
            modelo.addAttribute("error", "Error: formato invalido");
            return "GestionHabitaciones/eliminahabitacion";
        }
        servicio.eliminar(habitacion.getNumHabitacion());
        modelo.addAttribute("error", "Habitacion Eliminada");
        return "GestionHabitaciones/eliminahabitacion";
    }
}
