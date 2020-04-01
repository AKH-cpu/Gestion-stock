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
import javax.transaction.Transactional;
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
    @Autowired
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
        /*else if(!validateProduits(produits)) return -2;*/
        else {
            familleProduit.setProduits(produits);
            familleProduitRepository.save(familleProduit);
            produitService.save(familleProduit,familleProduit.getProduits());
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteByLibelle(String libelle) {
        int res1=produitService.deleteByFamilleProduitLibelle(libelle);
        int res2= familleProduitRepository.deleteByLibelle(libelle);
        return res1+res2;
    }
    
    /*public boolean validateProduits(List<Produit> produits){
        List<Produit> res=new ArrayList<>();
           for (Produit produit : produits) {
             Produit foundedProduit=produitService.findByReference(produit.getReference());
             if(foundedProduit!=null) res.add(produit);
        }
          return res.size()==produits.size();
    }*/
    
}
