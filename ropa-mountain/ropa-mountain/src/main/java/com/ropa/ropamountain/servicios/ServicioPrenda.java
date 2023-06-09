package com.ropa.ropamountain.servicios;

import com.ropa.ropamountain.entidades.Prenda;
import com.ropa.ropamountain.repositorios.RepositorioPrenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioPrenda {
    @Autowired
    RepositorioPrenda repositorioPrenda;

    public List<Prenda> listarPrendas() {
        System.out.println("hola");
        return repositorioPrenda.findAll();
    }

    public Prenda buscarPorId(Long id){
        Optional<Prenda> resPrenda = repositorioPrenda.findById(id);
        return resPrenda.orElse(null);
    }
}
