/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.LivraisonDetail;
import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Produit;
import com.projet.stock.repository.LivraisonDetailRepository;
import com.projet.stock.service.facade.LivraisonDetailService;
import com.projet.stock.service.facade.LivraisonService;
import com.projet.stock.service.facade.MagasinService;
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
    
    @Autowired
    MagasinService magasinService;

    @Override
    public LivraisonDetail findByReference(String reference) {
        return livraisonDetailRepository.findByReference(reference);
    }

    @Override
    public List<LivraisonDetail> findAll() {
        return livraisonDetailRepository.findAll();
    }

    @Override
    public int validateLivraisonDetail(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
      if(livraisonDetails == null){
           return  -5;
       }
        List<LivraisonDetail> valideProduits = livraisonDetails.stream().filter(
                vp ->vp.getProduit()!=null && produitService.findByReference(vp.getProduit().getReference()) != null).collect(Collectors.toList()
        );
         List<LivraisonDetail> valideMagasins = livraisonDetails.stream().filter(
                 vm -> vm.getMagasin() != null && magasinService.findByReference(vm.getMagasin().getReference()) != null).collect(Collectors.toList()
        );
        valideMagasins.forEach(c-> System.out.println(c.getMagasin()));
        if( valideProduits.size() != livraisonDetails.size()){
            return -6;
        }else if(valideMagasins.size() != livraisonDetails.size()){
            return -7;
        }else {

            return 8;
        }
    }

    @Override
    public int save(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        if (validateLivraisonDetail(livraison, livraisonDetails)==8){
            livraisonDetails.forEach(l -> {
                Produit produit = produitService.findByReference(l.getProduit().getReference());
                Magasin  magasin=magasinService.findByReference(l.getMagasin().getReference());
                l.setLivraison(livraison);
                l.setProduit(produit);
                l.setMagasin(magasin);
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
        return livraisonDetailRepository.deleteByLivraisonReference(reference);
    }

}
