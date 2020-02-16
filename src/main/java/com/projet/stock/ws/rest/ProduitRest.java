/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Produit;
import com.projet.stock.service.facade.ProduitService;
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
@RequestMapping("produit-api/produit")
public class ProduitRest {

    @Autowired
    ProduitService produitService;

    @GetMapping("/reference")
    public Produit findByReference(@PathVariable String reference) {
        return produitService.findByReference(reference);
    }

    @PostMapping("/")
    public int save(@RequestBody Produit produit) {
        return produitService.save(produit);
    }

    @GetMapping("/")
    public List<Produit> findAll() {
        return produitService.findAll();
    }

}
