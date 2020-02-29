/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Magasin;
import com.projet.stock.service.facade.MagasinService;
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
 * @author lenovo
 */
@RestController
@RequestMapping("Magasin-api/Magasin")
public class MagasinRest {
    
    @Autowired
    MagasinService magasinService;
    
    @GetMapping("/reference/{reference}")
    public Magasin findByReference(@PathVariable String reference){
        return magasinService.findByReference(reference);
    }
    
    @GetMapping("/findAll")
    public List<Magasin> findAll(){
        return magasinService.findAll();
    }
    
    @PostMapping("/")
    public int save (@RequestBody Magasin magasin){
        return magasinService.save(magasin);
    }
}
