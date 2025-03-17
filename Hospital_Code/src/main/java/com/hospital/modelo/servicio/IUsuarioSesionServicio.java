
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.UsuarioSesion;


public interface IUsuarioSesionServicio {
    UsuarioSesion buscarByUsuario(String usuario); 
   void guardar(UsuarioSesion usuario);
   void eliminar(UsuarioSesion usuario);
}
