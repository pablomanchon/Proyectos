package com.ropa.ropamountain.controladores;

import com.ropa.ropamountain.entidades.Prenda;
import com.ropa.ropamountain.servicios.ServicioPrenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/")
public class ControladorPrenda {
    @Autowired
    ServicioPrenda servicioPrenda;

    @GetMapping("/")
    public String ListarPrendas(ModelMap modelo) {
        List<Prenda> prendas = servicioPrenda.listarPrendas();
        modelo.put("prendas", prendas);
        return "lista-prendas.html";
    }

    @GetMapping("/Prenda/{id}")
    public String mostrarPrenda(@PathVariable Long id, ModelMap modelo) {
        Prenda prenda = servicioPrenda.buscarPorId(id);
        modelo.put("prenda", prenda);
        return "prenda.html";
    }
}
