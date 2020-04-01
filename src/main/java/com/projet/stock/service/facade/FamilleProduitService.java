/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.FamilleProduit;
import com.projet.stock.bean.Produit;

import java.util.List;

/**
 * @author KHALID
 */
public interface FamilleProduitService {
    public FamilleProduit findByLibelle(String libelle);
    public List<FamilleProduit> findAll();
    public int save(FamilleProduit familleProduit,List<Produit> produits);
    public int deleteByLibelle(String libelle);
}
