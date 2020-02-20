/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Produit;
import com.projet.stock.repository.ExpressionBesoinRepository;
import com.projet.stock.service.ExpressionBesoinService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class ExpressionBesoinServiceImpl implements ExpressionBesoinService{
    
    @Autowired 
    private ExpressionBesoinRepository expressionBesoinRepository;
    

    @Override
    public String findByReference(String reference) {
        return expressionBesoinRepository.findByReference(reference);
    }

    @Override
    public Date findBydateExpressionBesoin(Date dateExpressionBesoin) {
        return expressionBesoinRepository.findBydateExpressionBesoin(dateExpressionBesoin);
    }

    @Override
    public int save(Produit produit) {
       return 1;
    }
    
}
