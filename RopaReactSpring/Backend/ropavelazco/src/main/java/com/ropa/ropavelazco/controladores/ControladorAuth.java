package com.ropa.ropavelazco.controladores;

import com.ropa.ropavelazco.entidades.LoginRequest;
import com.ropa.ropavelazco.entidades.Usuario;
import com.ropa.ropavelazco.repositorios.RepositorioUsuario;
import com.ropa.ropavelazco.servicios.ServicioUsuario;
import com.ropa.ropavelazco.token.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping
public class ControladorAuth {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @PostMapping("/logincheck")
    public ResponseEntity<Map<String, Object>> loginCheck(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails usuario = servicioUsuario.loadUserByUsername(loginRequest.getEmail());

            Usuario user = repositorioUsuario.findByNombreOrEmail(usuario.getUsername()).orElse(null);
            // Generar el token JWT
            String token = jwtUtils.generateToken(loginRequest.getEmail(),user.getRol());

            // Crear la respuesta con el token
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("mensaje", "Inicio de sesión exitoso");

            // Devolver la respuesta como ResponseEntity
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
