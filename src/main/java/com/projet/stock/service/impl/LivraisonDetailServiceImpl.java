/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.LivraisonDetail;
import com.projet.stock.bean.Produit;
import com.projet.stock.repository.LivraisonDetailRepository;
import com.projet.stock.service.facade.LivraisonDetailService;
import com.projet.stock.service.facade.LivraisonService;
import com.projet.stock.service.facade.ProduitService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AKH
 */
@Service
public class LivraisonDetailServiceImpl implements LivraisonDetailService {

    @Autowired
    LivraisonDetailRepository livraisonDetailRepository;

    @Autowired
    LivraisonService livraisonService;

    @Autowired
    ProduitService produitService;

    @Override
    public LivraisonDetail findByReference(String reference) {
        return livraisonDetailRepository.findByReference(reference);
    }

    @Override
    public List<LivraisonDetail> findAll() {
        return livraisonDetailRepository.findAll();
    }

    @Override
    public boolean validateLivraisonDetail(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        List<LivraisonDetail> valideProduits = livraisonDetails.stream().filter(
                vp -> produitService.findByReference(vp.getProduit().getReference()) != null).collect(Collectors.toList()
        );
        return valideProduits.size() == livraisonDetails.size();
    }

    @Override
    public int save(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        if (validateLivraisonDetail(livraison, livraisonDetails)) {
            livraisonDetails.forEach(l -> {
                l.setLivraison(livraison);
                Produit produit = produitService.findByReference(l.getProduit().getReference());
                l.setProduit(produit);
                livraisonDetailRepository.save(l);
            });
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public List<LivraisonDetail> findByLivraisonReference(String reference) {
        return livraisonDetailRepository.findByLivraisonReference(reference);
    }

    @Override
    public int deleteByLivraisonReference(String reference) {
        return 0;
    }

}
