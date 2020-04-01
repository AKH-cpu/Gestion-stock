/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.ExpressionBesoinDetail;
import com.projet.stock.bean.Personnel;
import com.projet.stock.repository.ExpressionBesoinRepository;
import com.projet.stock.service.facade.EntiteAdministrativeService;
import com.projet.stock.service.facade.ExpressionBesoinDetailService;
import com.projet.stock.service.facade.ExpressionBesoinService;
import com.projet.stock.service.facade.PersonnelService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private PersonnelService personnelService;
    @Autowired
    private ExpressionBesoinDetailService expressionBesoinDetailService;
    @Autowired
    private EntiteAdministrativeService entiteAdministrativeService;

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
        int exp = expressionBesoinDetailService.setProduitToEDB(expressionBesoin.getExpressionBesoinDetails());
        ExpressionBesoin foundedexpressionBesoin = expressionBesoinRepository.findByReference(expressionBesoin.getReference());
        EntiteAdministrative foundedEntAdm = entiteAdministrativeService.findByReference(expressionBesoin.getEntiteAdministrative().getReference());
        Personnel foundedChef = personnelService.findByCode(expressionBesoin.getChef().getCode());

        if (foundedexpressionBesoin != null) {
            return -1;
        } else if (foundedEntAdm == null) {
            return -2;
        } else if (foundedChef == null) {
            return -3;
        } else if (exp == -1) {
            return -4;
        } else {
            expressionBesoin.setDateExpressionBesoin(new Date());
            expressionBesoin.setEntiteAdministrative(foundedEntAdm);
            expressionBesoin.setChef(foundedChef);
            expressionBesoinRepository.save(expressionBesoin);
            expressionBesoinDetailService.save(expressionBesoin.getReference(), expressionBesoin.getExpressionBesoinDetails());
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteByReference(String reference) {
        ExpressionBesoin foundedexpressionBesoin1 = expressionBesoinRepository.findByReference(reference);

        if (foundedexpressionBesoin1 == null) {
            return -1;
        } else {
            int res = expressionBesoinDetailService.deleteByExpressionBesoinReference(reference);
            int res1 = expressionBesoinRepository.deleteByReference(reference);
            return res + res1;
        }
    }

    @Override
    public List<ExpressionBesoin> findAll() {
        return expressionBesoinRepository.findAll();
    }

//    @Override
//    @Transactional
//    public int deleteByReference(String Reference) {
//        ExpressionBesoin foundedexpressionBesoin1 = expressionBesoinRepository.findByReference(Reference);
//
//        if (foundedexpressionBesoin1 == null) {
//            return -1;
//        } else {
//            expressionBesoinRepository.deleteByReference(Reference);
//            return 1;
//        }
//    }
    @Override
    public List<ExpressionBesoin> findByChef(String codeEmp) {
        Personnel foundedPersonnel = personnelService.findByCode(codeEmp);

        if (foundedPersonnel == null) {
            return null;
        } else {
            List<ExpressionBesoin> expressionBesoins = new ArrayList<>();
            for (ExpressionBesoin expressionBesoin : expressionBesoinRepository.findAll()) {
                if (expressionBesoin.getChef().getCode().equals(codeEmp)) {

                    expressionBesoins.add(expressionBesoin);
                }
            }
            return expressionBesoins;
        }
    }

}
