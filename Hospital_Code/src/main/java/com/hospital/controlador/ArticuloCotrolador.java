package com.hospital.controlador;

import com.hospital.modelo.entidad.Articulo;
import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.servicio.IArticuloServicio;
import com.hospital.modelo.servicio.IHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class ArticuloCotrolador {
    @Autowired
    private IArticuloServicio servicio;
    @Autowired
    private IHabitacionServicio habitacionServicio;

    @GetMapping("/gestionarticulos")
    public String gestion() {
        return "GestionArticulos/gestionarticulos";
    }

    @GetMapping("/guardaarticulo")
    public String guarda(Model modelo) {
        Articulo articulo = new Articulo();
        modelo.addAttribute("articulo", articulo);
        return "GestionArticulos/guardaarticulo";
    }

    @PostMapping("/guardararticulo")
    public String guardar(@ModelAttribute Articulo articulo) {
        servicio.guardar(articulo);
        return "redirect:/asignahabitaciones?articuloId=" + articulo.getIdArticulo();
    }

    @GetMapping("/asignahabitaciones")
    public String asignaHabitaciones(@RequestParam("articuloId") int articuloId, Model modelo) {
        Articulo articulo = servicio.buscarPorId(articuloId);
        modelo.addAttribute("titulo", "Asignar Artículos a Habitaciones");
        modelo.addAttribute("articulo", articulo);
        return "GestionArticulos/asignahabitaciones";
    }

    @PostMapping("/asignahabitaciones")
    public String guardarHabitaciones(@RequestParam("articuloId") int articuloId,
                                      @RequestParam("habitacionId") int habitacionId,
                                      @RequestParam(name = "cantidad") int cant,
                                      Model modelo) {
        Articulo articulo = servicio.buscarPorId(articuloId);
        Habitacion habitacion = habitacionServicio.buscarPorId(habitacionId);

        if (habitacion == null) {
            modelo.addAttribute("error", "La habitación no existe.");
        } else if (articulo.getCantidad() < cant) {
            modelo.addAttribute("error", "No hay unidades suficientes para asignar.");
        } else {
            articulo.getHabitaciones().add(habitacion);
            habitacion.getArticulos().add(articulo);
            articulo.setCantidad(articulo.getCantidad() - cant);
            servicio.guardar(articulo);
            modelo.addAttribute("mensaje", "Artículo asignado correctamente.");
        }

        modelo.addAttribute("articulo", articulo);
        return "GestionArticulos/asignahabitaciones";
    }

    @GetMapping("/buscaarticulo")
    public String busca(Model modelo) {
        Articulo articulo = new Articulo();
        modelo.addAttribute("articulo", articulo);
        return "GestionArticulos/buscaarticulo";
    }

    @PostMapping("/buscararticulo")
    public String buscar(@RequestParam(name = "idArticulo") int idArticulo, Model modelo) {
        Articulo articulo = null;
        if (idArticulo > 0) {
            articulo = servicio.buscarPorId(idArticulo);
            if (articulo == null) {
                modelo.addAttribute("error", "El id ingresado no existe");
                return "GestionArticulos/buscaarticulo";
            }
        } else {
            modelo.addAttribute("error", "Formato invalido");
        }
        modelo.addAttribute("articulo", articulo);
        modelo.addAttribute("nombre", articulo.getNomArticulo());
        modelo.addAttribute("cantidad", articulo.getCantidad());
        modelo.addAttribute("descripcion", articulo.getDescripcion());
        modelo.addAttribute("habitaciones", articulo.getHabitaciones());
        return "GestionArticulos/buscaarticulo";
    }

    @GetMapping("/actualizaarticulo")
    public String actualiza(Model modelo) {
        Articulo articulo = new Articulo();
        modelo.addAttribute("articulo", articulo);
        return "GestionArticulos/actualizaarticulo";
    }

    @PostMapping("/actualizararticulo")
    public String actualizar(@RequestParam(name = "idArticulo") int idArticulo, Model modelo) {
        Articulo articulo = null;
        if (idArticulo > 0) {
            articulo = servicio.buscarPorId(idArticulo);
            if (articulo == null) {
                modelo.addAttribute("error", "El id ingresado no existe");
                return "GestionArticulos/actualizaarticulo";
            }
        } else {
            modelo.addAttribute("error", "Formato invalido");
            return "GestionArticulos/actualizaarticulo";
        }
        modelo.addAttribute("articulo", articulo);

        return "GestionArticulos/actualizararticulo";
    }
    @PostMapping("/actuali")
    public String guard(@ModelAttribute Articulo articulo, Model modelo) {
        Articulo articuloExistente = servicio.buscarPorId(articulo.getIdArticulo());
        if (articuloExistente != null) {
            articulo.setHabitacion(articuloExistente.getHabitaciones());
        }
        servicio.guardar(articulo);
        modelo.addAttribute("articulo", articulo);
        return "redirect:/asignahabitaciones?articuloId=" + articulo.getIdArticulo();
    }
    @GetMapping("/eliminaarticulo")
    public String elimina(Model modelo){
        Articulo articulo=new Articulo();
        modelo.addAttribute("articulo",articulo);
        return "GestionArticulos/eliminaarticulo";
    }
    @PostMapping("/eliminararticulo")
    public String eliminar(@RequestParam(name = "idArticulo")int id,Model modelo){
        Articulo articulo=null;
        if (id>0){
            articulo=servicio.buscarPorId(id);
            if (articulo==null){
                modelo.addAttribute("error","El id ingresado no existe");
                return "GestionArticulos/eliminaarticulo";
            }
        }else{
            modelo.addAttribute("error","Formato invalido");
            return "GestionArticulos/eliminaarticulo";
        }
        servicio.eliminar(id);
        modelo.addAttribute("error","Articulo eliminado");
        return "GestionArticulos/eliminaarticulo";
    }
}