package com.ropa.ropavelazco.controladores;

import com.ropa.ropavelazco.entidades.Prenda;
import com.ropa.ropavelazco.repositorios.RepositorioPrenda;
import com.ropa.ropavelazco.servicios.ServicioPrenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/prendas")
public class ControladorPrenda {
    @Autowired
    RepositorioPrenda repositorioPrenda;

    @GetMapping("/lista")
    public List<Prenda> ListarPrendas() {
        return repositorioPrenda.findAll();
    }
    
    @PostMapping("/crear")
    Prenda crearPrenda(@RequestBody Prenda prenda) {
        return repositorioPrenda.save(prenda);
    }

    @GetMapping("/{id}")
    public Prenda mostrarPrenda(@PathVariable Long id, ModelMap modelo) {
        Optional<Prenda> resPrenda = repositorioPrenda.findById(id);
        return resPrenda.orElse(null);
    }
}

