/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.LivraisonDetail;
import com.projet.stock.service.facade.LivraisonDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("livraisonDetail-api/livraisonDetail")
public class LivraisonDetailRest {

      @Autowired
    LivraisonDetailService livraisonDetailService;

    @GetMapping("/reference/{reference}")
    public LivraisonDetail findByReference(@PathVariable String reference) {
        return livraisonDetailService.findByReference(reference);
    }

    @GetMapping("/")
    public List<LivraisonDetail> findAll() {
        return livraisonDetailService.findAll();
    }

    @GetMapping("/validate")
    public int validateLivraisonDetail(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        return livraisonDetailService.validateLivraisonDetail(livraison, livraisonDetails);
    }

    @PostMapping("/")
    public int save(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        return livraisonDetailService.save(livraison, livraisonDetails);
    }

    @GetMapping("/livraison/reference/{reference}")
    public List<LivraisonDetail> findByLivraisonReference(@PathVariable String reference) {
        return livraisonDetailService.findByLivraisonReference(reference);
    }
}
