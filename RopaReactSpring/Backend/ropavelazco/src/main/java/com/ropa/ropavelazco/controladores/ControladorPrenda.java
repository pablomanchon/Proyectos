package com.ropa.ropavelazco.controladores;

import com.ropa.ropavelazco.entidades.Prenda;
import com.ropa.ropavelazco.repositorios.RepositorioPrenda;
import com.ropa.ropavelazco.servicios.ServicioPrenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/prendas")
public class ControladorPrenda {
    @Autowired
    RepositorioPrenda repositorioPrenda;
    @Autowired
    ServicioPrenda servicioPrenda;

    @GetMapping("/lista")
    public List<Prenda> listarPrendas() {
        return repositorioPrenda.findAll();
    }

    @GetMapping("/twoRandom")
    List<Prenda> randomPrendas() {
        List<Prenda> prendas = repositorioPrenda.findAll();
        Collections.shuffle(prendas, new Random());
        return prendas.subList(0, 2);
    }

    @GetMapping("/buscarPorTermino")
    public List<Prenda> buscarPorTermino(@RequestParam("term") String termino){
        return servicioPrenda.buscarPorTermino(termino);
    }
    @PostMapping("/crear")
    Prenda crearPrenda(@RequestBody Prenda prenda) {
        return repositorioPrenda.save(prenda);
    }

    @GetMapping("/{id}")
    public Prenda mostrarPrenda(@PathVariable Long id, ModelMap modelo) {
        return repositorioPrenda.findById(id).orElse(null);
    }
}

