/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.LivraisonDetail;
import com.projet.stock.service.facade.LivraisonDetailService;
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
@RequestMapping("livraisonDetail-api/livraisonDetail")
public class LivraisonDetailRest {

    @Autowired
    LivraisonDetailService livraisonDetailService;

    @GetMapping("/reference/{reference}")
    public LivraisonDetail findbyReference(@PathVariable String reference) {
        return livraisonDetailService.findbyReference(reference);
    }

    @PostMapping("/")
    public int save(@RequestBody LivraisonDetail livraisonDetail) {
        return livraisonDetailService.save(livraisonDetail);
    }

    @GetMapping("/")
    public List<LivraisonDetail> findAll() {
        return livraisonDetailService.findAll();
    }

}
