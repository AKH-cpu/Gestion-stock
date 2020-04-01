/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.ExpressionBesoinDetail;
import com.projet.stock.bean.Produit;
import com.projet.stock.repository.ExpressionBesoinDetailRepository;
import com.projet.stock.service.facade.ExpressionBesoinDetailService;
import com.projet.stock.service.facade.ExpressionBesoinService;
import com.projet.stock.service.facade.ProduitService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class ExpressionBesoinDetailServiceImpl implements ExpressionBesoinDetailService {

    @Autowired
    private ExpressionBesoinDetailRepository expressionBesoinDetailRepository;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private ExpressionBesoinService expressionBesoinService;

    @Override
    public List<ExpressionBesoinDetail> findByQte(Double qte) {
        return expressionBesoinDetailRepository.findByQte(qte);
    }

    @Override
    public List<ExpressionBesoinDetail> findByQteLivre(Double qteLivre) {
        return expressionBesoinDetailRepository.findByQteLivre(qteLivre);
    }

    @Override
    public ExpressionBesoinDetail findByReference(String reference) {
        return expressionBesoinDetailRepository.findByReference(reference);
    }

    @Override
    public int save(String expressionBesoinRef, List<ExpressionBesoinDetail> expressionBesoinDetail) {
        ExpressionBesoin foundedExpressionBesoin = expressionBesoinService.findByReference(expressionBesoinRef);
        if(setProduitToEDB(expressionBesoinDetail) == -1){
            return -1;
        }
        if (foundedExpressionBesoin == null) {
            return -2;
        } else {
            for (ExpressionBesoinDetail expressionBesoinDetails : expressionBesoinDetail) {
                ExpressionBesoinDetail foundedExpressionBesoinDetail = expressionBesoinDetailRepository.findByReference(expressionBesoinDetails.getReference());
                if (foundedExpressionBesoinDetail != null) {
                    return -3;
                } else {
                    expressionBesoinDetails.setExpressionBesoin(foundedExpressionBesoin);
                    expressionBesoinDetailRepository.save(expressionBesoinDetails);
                }
            }
            return 1;
        }

    }

    @Override
    public int setProduitToEDB(List<ExpressionBesoinDetail> expressionBesoinDetail) {
        if(expressionBesoinDetail == null && expressionBesoinDetail.isEmpty()){
        return -2 ;
        }
        for (ExpressionBesoinDetail expressionBesoinDetails : expressionBesoinDetail) {
            Produit foundedproduct = produitService.findByReference(expressionBesoinDetails.getProduit().getReference());
            if (foundedproduct == null) {
                return -1;
            } else {
                expressionBesoinDetails.setProduit(foundedproduct);
            }
        }
        return 1;
        
    }

    @Override
    public List<ExpressionBesoinDetail> findEDBOnHold() {
        // Produit foundedproduct = produitService.findByReference(expressionBesoinDetail.getProduit().getReference());
        List<ExpressionBesoinDetail> EDBOnHold = new ArrayList<>();
        for (ExpressionBesoinDetail expressionBesoinDetails : expressionBesoinDetailRepository.findAll()) {
            if (!produitService.findAll().contains(expressionBesoinDetails.getProduit())) {
                EDBOnHold.add(expressionBesoinDetails);
            }
        }
        return EDBOnHold;
    }

    @Override
    public List<ExpressionBesoinDetail> findAll() {
        return expressionBesoinDetailRepository.findAll();
    }

    @Override
    @Transactional
    public int deleteByReference(String Reference
    ) {
        ExpressionBesoinDetail foundedexpressionBesoin1Detail = expressionBesoinDetailRepository.findByReference(Reference);

        if (foundedexpressionBesoin1Detail == null) {
            return -1;
        } else {
            expressionBesoinDetailRepository.deleteByReference(Reference);
            return 1;
        }
    }

    @Override
    public int deleteByExpressionBesoinReference(String reference) {
        return expressionBesoinDetailRepository.deleteByExpressionBesoinReference(reference);
    }

}
