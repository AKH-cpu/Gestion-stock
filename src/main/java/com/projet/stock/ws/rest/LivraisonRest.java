/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Livraison;
import com.projet.stock.service.facade.LivraisonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @author AKH
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("livraison-api/livraison")
public class LivraisonRest {

       @Autowired
    LivraisonService livraisonService;

    @GetMapping("/reference/{reference}")
    public Livraison findByReference(@PathVariable String reference) {
        return livraisonService.findByReference(reference);
    }

    @PostMapping("/")
    public int save(@RequestBody Livraison livraison) {
        return livraisonService.save(livraison, livraison.getLivraisonDetails());
    }

    @GetMapping("/")
    public List<Livraison> findAll() {
        return livraisonService.findAll();
    }

    @DeleteMapping("/reference/{reference}")
    public int deleteByReference(@PathVariable String reference) {
        return livraisonService.deleteByReference(reference);
    }
}
