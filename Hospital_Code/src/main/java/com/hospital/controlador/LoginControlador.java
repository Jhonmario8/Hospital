package com.hospital.controlador;

import com.hospital.modelo.entidad.UsuarioSesion;
import com.hospital.modelo.servicio.IUsuarioSesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControlador {

    @Autowired
    private IUsuarioSesionServicio servicio;

    @GetMapping("/")
    public String login(Model modelo){
        UsuarioSesion usuario = new UsuarioSesion();
        modelo.addAttribute("titulo", "Formulario: Nuevo Usuario");
        modelo.addAttribute("usuario", usuario);
        return "InicioSesion/InicioSesion";
    }

    @PostMapping("/iniciar")
    public String logear(@RequestParam(name="user") String usuario,
                         @RequestParam(name="contraseña") String contraseña, Model modelo){
        UsuarioSesion userDb = servicio.buscarByUsuario(usuario);
        if (userDb != null && userDb.getContraseña().equals(contraseña)) {
            return "index";
        } else {
            modelo.addAttribute("error", "El usuario o contraseña son incorrectos");
            return "InicioSesion/InicioSesion";
        }
    }


    @GetMapping("/registrarse")
    public String registrarse(Model modelo){
        UsuarioSesion usuario=new UsuarioSesion();
        modelo.addAttribute("titulo","Formulario: Nuevo Usuario");
        modelo.addAttribute("usuario",usuario);
        return "InicioSesion/registrarse";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute UsuarioSesion usuario, Model modelo){
        modelo.addAttribute("titulo","Formulario: Nuevo Usuario");
        modelo.addAttribute("usuario",usuario);
        UsuarioSesion userDb= servicio.buscarByUsuario(usuario.getUsuario());
        if (userDb==null) {
            servicio.guardar(usuario);
            return  "redirect:/";
        }
        else{
            modelo.addAttribute("error","El usuario ya esta registrado");
            return "InicioSesion/registrarse";
        }
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/QuienesSomos")
    public String QuienesSomos(){

        return "QuienesSomos";
    }
}
