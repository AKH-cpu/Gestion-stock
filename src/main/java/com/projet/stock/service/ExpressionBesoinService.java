/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service;

import com.projet.stock.bean.Produit;
import java.util.Date;

/**
 *
 * @author anoir
 */
public interface ExpressionBesoinService {

    public String findByReference(String reference);

    public Date findBydateExpressionBesoin(Date dateExpressionBesoin);

    public int save(Produit produit);

}
