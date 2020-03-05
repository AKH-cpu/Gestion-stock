/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.repository.ExpressionBesoinRepository;
import com.projet.stock.service.facade.ExpressionBesoinService;
import com.projet.stock.service.facade.ProduitService;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class ExpressionBesoinServiceImpl implements ExpressionBesoinService {

    @Autowired
    private ExpressionBesoinRepository expressionBesoinRepository;
    @Autowired
    private ProduitService produitService;

    @Override
    public ExpressionBesoin findByReference(String reference) {
        return expressionBesoinRepository.findByReference(reference);
    }

    @Override
    public ExpressionBesoin findBydateExpressionBesoin(Date dateExpressionBesoin) {
        return expressionBesoinRepository.findBydateExpressionBesoin(dateExpressionBesoin);
    }

    @Override
    public int save(ExpressionBesoin expressionBesoin) {
        ExpressionBesoin foundedexpressionBesoin1 = expressionBesoinRepository.findByReference(expressionBesoin.getReference());
        if (foundedexpressionBesoin1 != null) {
            return -1;
        } else {
            expressionBesoin.setDateExpressionBesoin(new Date());
            expressionBesoinRepository.save(foundedexpressionBesoin1);
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteByReference(String Reference) {
        ExpressionBesoin foundedexpressionBesoin1 = expressionBesoinRepository.findByReference(Reference);

        if (foundedexpressionBesoin1 == null) {
            return -1;
        } else {
            expressionBesoinRepository.deleteByReference(Reference);
            return 1;
        }
    }

}
