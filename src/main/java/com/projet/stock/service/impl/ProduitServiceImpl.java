/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.FamilleProduit;
import com.projet.stock.bean.Produit;
import com.projet.stock.repository.ProduitRepository;
import com.projet.stock.service.facade.FamilleProduitService;
import com.projet.stock.service.facade.ProduitService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AKH
 */
@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    FamilleProduitService familleProduitService;

    @Override
    public Produit findByReference(String reference) {
        return produitRepository.findByReference(reference);
    }

    @Transactional
    @Override
    public int deleteByReference(String reference) {
        return produitRepository.deleteByReference(reference);
    }

    @Override
    public int save(Produit produit) {
        Produit foundedProduit = findByReference(produit.getReference());
        FamilleProduit familleProduit = familleProduitService.findByLibelle(produit.getFamilleProduit().getLibelle());
        if (foundedProduit != null) {
            return -1;
        } else if (familleProduit == null) {
            return -2;

        } else {
            produit.setFamilleProduit(familleProduit);
            produitRepository.save(produit);
            return 1;
        }

    }

    @Override
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    // service qui permet de trouver tous les produits d'une famille Produits
    @Override
    public List<Produit> findByLibelleFamille(String libelle) {
        return produitRepository.findByLibelleFamille(libelle);
    }
}
