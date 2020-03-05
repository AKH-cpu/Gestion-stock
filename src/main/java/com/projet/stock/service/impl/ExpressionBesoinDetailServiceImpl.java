/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.ExpressionBesoinDetail;
import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.Produit;
import com.projet.stock.repository.ExpressionBesoinDetailRepository;
import com.projet.stock.repository.ExpressionBesoinRepository;
import com.projet.stock.service.facade.ExpressionBesoinDetailService;
import com.projet.stock.service.facade.LivraisonService;
import com.projet.stock.service.facade.ProduitService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class ExpressionBesoinDetailServiceImpl implements ExpressionBesoinDetailService{

    @Autowired
    private ExpressionBesoinDetailRepository expressionBesoinDetailRepository ;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private LivraisonService livraisonService ;
    
    @Override
    public List<ExpressionBesoinDetail> findByQte(Double qte) {
        return expressionBesoinDetailRepository.findByQte(qte);
    }

    @Override
    public List<ExpressionBesoinDetail> findByQteLivre(Double qteLivre) {
        return expressionBesoinDetailRepository.findByQteLivre(qteLivre);
    }

    @Override
    public int save(ExpressionBesoinDetail expressionBesoinDetail) {
        Produit foundedproduct = produitService.findByReference(expressionBesoinDetail.getProduit().getReference());
        Livraison foundedlivraison = livraisonService.findByReference(expressionBesoinDetail.getExpressionBesoin().getLivraison().getReference());
        
        if(foundedproduct == null)
            return -1 ;
        else if(foundedlivraison != null)
            return -2 ;
        else {
            expressionBesoinDetailRepository.save(expressionBesoinDetail);
            return 1;
        }
        
    }

    @Override
    public List<ExpressionBesoinDetail> findEDBOnHold() {
       // Produit foundedproduct = produitService.findByReference(expressionBesoinDetail.getProduit().getReference());
        List<ExpressionBesoinDetail> EDBOnHold = new ArrayList<>();
        for (ExpressionBesoinDetail expressionBesoinDetails : expressionBesoinDetailRepository.findAll()) {
            if(!produitService.findAll().contains(expressionBesoinDetails.getProduit())){
                EDBOnHold.add(expressionBesoinDetails);
            }
        }
        return EDBOnHold;
    }

    @Override
    public List<ExpressionBesoinDetail> findAll() {
        return expressionBesoinDetailRepository.findAll();
    }
    
    
    
   
    
}
