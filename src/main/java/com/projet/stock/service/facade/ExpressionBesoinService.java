/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.ExpressionBesoin;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface ExpressionBesoinService {

    ExpressionBesoin findByReference(String reference);

    ExpressionBesoin findBydateExpressionBesoin(Date dateExpressionBesoin);

    int save(ExpressionBesoin expressionBesoin);

    int deleteByReference(String Reference);

    public List<ExpressionBesoin> findAll();

    public List<ExpressionBesoin> findByChef(String codeEmp);

}
