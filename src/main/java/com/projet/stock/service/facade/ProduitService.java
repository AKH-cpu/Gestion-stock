/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.FamilleProduit;
import com.projet.stock.bean.Produit;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author AKH
 */
public interface ProduitService {

    Produit findByReference(String reference);

    int deleteByReference(String reference);

    int save(Produit produit);

    List<Produit> findAll();

    List<Produit> findByFamilleProduitLibelle(String libelle);

    int update(String reference, Produit produit);

    //Rechercher un article par intervalle de prix de vente.
    List<Produit> findByPrixBetween(double prixMin, double prixMax);

 
    int deleteByFamilleProduitLibelle(String Libelle);
    int save(FamilleProduit familleProduit,List<Produit> produits);



}
