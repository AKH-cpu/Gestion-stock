/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.FamilleProduit;
import com.projet.stock.bean.Produit;
import com.projet.stock.service.facade.FamilleProduitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("FamilleProduit-stock/familleProduit")
public class FamilleProduitRest {
    @Autowired
    private FamilleProduitService familleProduitService;

    @GetMapping("/libelle/{libelle}")
    public FamilleProduit findByLibelle(@PathVariable String libelle) {
        return familleProduitService.findByLibelle(libelle);
    }

    @GetMapping("/")
    public List<FamilleProduit> findAll() {
        return familleProduitService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody FamilleProduit familleProduit) {
        return familleProduitService.save(familleProduit,familleProduit.getProduits());
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return familleProduitService.deleteByLibelle(libelle);
    }

   
     
}
