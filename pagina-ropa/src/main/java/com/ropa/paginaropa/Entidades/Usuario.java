package com.ropa.paginaropa.Entidades;


import com.ropa.paginaropa.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;


    private Date fechaAlta;
    private boolean activo;
}

