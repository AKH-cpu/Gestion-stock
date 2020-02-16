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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AKH
 */
@RestController
@RequestMapping("livraison-api/livraison")
public class LivraisonRest {

    @Autowired
    LivraisonService livraisonService;

    @GetMapping("/reference/{reference}")
    public Livraison findbyReference(@PathVariable String reference) {
        return livraisonService.findbyReference(reference);
    }

    @PostMapping("/")
    public int save(@RequestBody Livraison livraison) {
        return livraisonService.save(livraison);
    }

    @GetMapping("/")
    public List<Livraison> findAll() {
        return livraisonService.findAll();
    }

}
