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

    @GetMapping("/listaAlAzar/{cant}")
    public List<Prenda> ListaAlAzar(@RequestParam("cant") String cant) {
        System.out.println(cant);
        List <Prenda> prendas = repositorioPrenda.findAll();
        List <Prenda> lista = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 3; i++) {
            lista.add(prendas.get(rnd.nextInt(prendas.toArray().length)));
        }
        System.out.println(lista);
        return lista;
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

