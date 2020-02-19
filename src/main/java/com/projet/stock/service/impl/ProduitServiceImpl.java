/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Produit;
import com.projet.stock.repository.ProduitRepository;
import com.projet.stock.service.facade.ProduitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AKH
 */
@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public Produit findByReference(String reference) {
        return produitRepository.findByReference(reference);
    }

    @Override
    public int save(Produit produit) {
        Produit foundedProduit = findByReference(produit.getReference());
        if (foundedProduit != null) {
            return -1;
        } else {
            produitRepository.save(produit);
            return 1;
        }

    }

    @Override
    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

}
