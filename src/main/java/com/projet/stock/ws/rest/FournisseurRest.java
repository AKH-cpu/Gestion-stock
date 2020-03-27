/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Fournisseur;
import com.projet.stock.service.facade.FournisseurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author KHALID
 */
@RestController
@RequestMapping("Fournisseur-stock/fournisseur")
public class FournisseurRest {
    
    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/reference/{reference}")
    public Fournisseur findByReference(@PathVariable String reference) {
        return fournisseurService.findByReference(reference);
    }

    @GetMapping("/")
    public List<Fournisseur> findAll() {
        return fournisseurService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.save(fournisseur);
    }

    @DeleteMapping("/reference/{reference}")
    public int deleteByReference(String reference) {
        return fournisseurService.deleteByReference(reference);
    }
    
    
}
