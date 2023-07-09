package com.ropa.ropavelazco.servicios;

import com.ropa.ropavelazco.entidades.Prenda;
import com.ropa.ropavelazco.repositorios.RepositorioPrenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPrenda {
    @Autowired
    RepositorioPrenda repositorioPrenda;

    public List<Prenda> listarPrendas() {
        return repositorioPrenda.findAll();
    }

    public Prenda buscarPorId(Long id){
        Optional<Prenda> resPrenda = repositorioPrenda.findById(id);
        return resPrenda.orElse(null);
    }
    public Prenda crearPrenda(Prenda prenda){
        return repositorioPrenda.save(prenda);
    }
}
