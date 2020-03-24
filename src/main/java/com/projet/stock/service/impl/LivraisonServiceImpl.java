/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.Fournisseur;
import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.LivraisonDetail;
import com.projet.stock.repository.LivraisonRepository;
import com.projet.stock.service.facade.ExpressionBesoinService;
import com.projet.stock.service.facade.FournisseurService;
import com.projet.stock.service.facade.LivraisonDetailService;
import com.projet.stock.service.facade.LivraisonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AKH
 */
@Service
public class LivraisonServiceImpl implements LivraisonService {

      @Autowired
    LivraisonRepository livraisonRepository;

    @Autowired
    FournisseurService fournisseurService;

    @Autowired
    LivraisonDetailService livraisonDetailService;

    @Autowired
    ExpressionBesoinService expressionBesoinService;

    @Override
    public Livraison findByReference(String reference) {
        return livraisonRepository.findByReference(reference);
    }

    @Override
    public List<Livraison> findAll() {
        return livraisonRepository.findAll();
    }

    @Override
    public int save(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        Livraison foundedLivraison = livraisonRepository.findByReference(livraison.getReference());
       Fournisseur fournisseur = fournisseurService.findByReference(livraison.getFournisseur().getReference());
        ExpressionBesoin expressionBesoin = expressionBesoinService.findByReference(livraison.getExpressionBesoin().getReference());
        int valide=livraisonDetailService.validateLivraisonDetail(livraison, livraisonDetails);
        if (foundedLivraison != null) {
            return -1;
      } else if (fournisseur == null) {
            return -2;
        } else if (expressionBesoin == null) {
            return -3;
        } else if (valide!=8) {
            return valide;
        } else {
           livraison.setFournisseur(fournisseur);
            livraison.setExpressionBesoin(expressionBesoin);
           calculerTotale(livraison, livraisonDetails);
           livraisonRepository.save(livraison);
            livraisonDetailService.save(livraison, livraisonDetails);

            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteByReference(String reference) {
        int resDetail = livraisonDetailService.deleteByLivraisonReference(reference);
        int resLivraison = livraisonRepository.deleteByReference(reference);
        return resDetail * resLivraison;
    }

    private void calculerTotale(Livraison livraison, List<LivraisonDetail> livraisonDetails) {
        double totale = 0;
        for (LivraisonDetail livraisonDetail : livraisonDetails) {
            totale += livraisonDetail.getProduit().getPrixUnitaireTTC() * livraisonDetail.getQte();
        }
        livraison.setTotal(totale);
    }


}
