package com.ropa.ropavelazco.controladores;

import com.ropa.ropavelazco.entidades.Usuario;
import com.ropa.ropavelazco.entidades.newUsuario;
import com.ropa.ropavelazco.excepciones.MiExcepcion;
import com.ropa.ropavelazco.repositorios.RepositorioUsuario;
import com.ropa.ropavelazco.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/")
public class ControladorPortal {
    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    RepositorioUsuario repoUsuario;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/index")
    public String index() {
        return "index.html";
    }


    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody newUsuario newUser) {
        try {
            System.out.println(newUser.getPassword());
            System.out.println(newUser.getPassword2());
            return servicioUsuario.registrarUsuario(newUser);
        }
        catch (MiExcepcion e) {
            throw new RuntimeException(e.getMessage());
        }
    }

  /*  @PostMapping("/registro")
    public String registro(@RequestParam String username, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo) {
        try {
            servicioUsuario.registrarUsuario(username, email, password, password2);
        } catch (MiExcepcion e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/index";
    }*/
}
