/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.ExpressionBesoinDetail;
import com.projet.stock.bean.Produit;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface ExpressionBesoinDetailService {

    List<ExpressionBesoinDetail> findAll();

    List<ExpressionBesoinDetail> findByQte(Double qte);

    List<ExpressionBesoinDetail> findByQteLivre(Double qteLivre);

    public ExpressionBesoinDetail findByReference(String reference);

    List<ExpressionBesoinDetail> findEDBOnHold();

    int save(String expressionBesoinRef, String produitRef, List<ExpressionBesoinDetail> expressionBesoinDetail);

    int deleteByReference(String reference);

    int deleteByExpressionBesoinReference(String reference);

    // infos sur produit en besoin
}
