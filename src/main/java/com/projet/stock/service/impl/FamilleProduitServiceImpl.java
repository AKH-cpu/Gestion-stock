/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.FamilleProduit;
import com.projet.stock.bean.Produit;
import com.projet.stock.repository.FamilleProduitRepository;
import com.projet.stock.service.facade.FamilleProduitService;
import com.projet.stock.service.facade.ProduitService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KHALID
 */
@Service
public class FamilleProduitServiceImpl implements FamilleProduitService{

    @Autowired
     private FamilleProduitRepository familleProduitRepository;
     private ProduitService produitService;
     
    @Override
    public FamilleProduit findByLibelle(String libelle) {
        return familleProduitRepository.findByLibelle(libelle);
    }

    @Override
    public List<FamilleProduit> findAll() {
      return familleProduitRepository.findAll();
    }

    @Override
    public int save(FamilleProduit familleProduit,List<Produit> produits) {
        FamilleProduit f=familleProduitRepository.findByLibelle(familleProduit.getLibelle());
        if(f!=null) return -1;
        else if(!validateProduits(familleProduit, produits)) return -2;
        else {
            familleProduit.setProduits(produits);
            familleProduitRepository.save(familleProduit);
            return 1;
        }
    }

    @Override
    public int deleteByLibelle(String libelle) {
        return familleProduitRepository.deleteByLibelle(libelle);
    }
    
    public boolean validateProduits(FamilleProduit familleProduit, List<Produit> produits){
        List<Produit> res=new ArrayList<>();
           for (Produit produit : produits) {
             Produit foundedProduit=produitService.findByReference(produit.getReference());
             if(foundedProduit!=null) res.add(produit);
        }
          return res.size()==familleProduit.getProduits().size();
    }
    
}
