/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.Fournisseur;
import com.projet.stock.bean.Livraison;
import com.projet.stock.repository.LivraisonRepository;
import com.projet.stock.service.facade.FournisseurService;
import com.projet.stock.service.facade.LivraisonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AKH
 */
@Service
public class LivraisonServiceImpl implements LivraisonService {

    @Autowired
    LivraisonRepository livraisonRepository;

    @Autowired
    FournisseurService fournisseurService;

    @Override
    public Livraison findbyReference(String reference) {
        return livraisonRepository.findByReference(reference);
    }

    @Override
    public int save(Livraison livraison) {
        Livraison foundedLivraison = findbyReference(livraison.getReference());
        Fournisseur fournisseur = fournisseurService.findByReference(livraison.getFournisseur().getReference());


        if (foundedLivraison != null) {
            return -1;
        } else if (fournisseur == null) {
            return -2;
        }else if(livraison.getExpressionBesoin()==null) {
            return -3;
        } else {
            // manque d'expressionBesoin
            livraison.setFournisseur(fournisseur);
            livraisonRepository.save(livraison);
            return 1;
        }
    }

    @Override
    public List<Livraison> findAll() {
        return livraisonRepository.findAll();
    }

}
