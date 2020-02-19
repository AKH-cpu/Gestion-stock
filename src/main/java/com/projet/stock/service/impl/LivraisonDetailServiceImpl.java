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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
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
    public LivraisonDetail findbyReference(String reference) {
        return livraisonDetailRepository.findByReference(reference);
    }

    @Override
    public int save(LivraisonDetail livraisonDetail) {
        LivraisonDetail foundedLivraisonDetail = livraisonDetailRepository.findByReference(livraisonDetail.getReference());
        Livraison livraison = livraisonService.findbyReference(livraisonDetail.getLivraison().getReference());
        Produit produit = produitService.findByReference(livraisonDetail.getProduit().getReference());
        //Magasin magasin=magasinService.findByReference(livraisonDetail.getMagasin().getReference());

        if (foundedLivraisonDetail != null) {
            return -1;
        } else {
            livraisonDetail.setLivraison(livraison);
            livraisonDetail.setProduit(produit);
            // il manque le magasin ici
            livraisonDetailRepository.save(livraisonDetail);
            return 1;
        }
    }

    @Override
    public List<LivraisonDetail> findAll() {
        return livraisonDetailRepository.findAll();
    }

}
