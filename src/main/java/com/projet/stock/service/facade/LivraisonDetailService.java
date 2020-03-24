/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.LivraisonDetail;

import java.util.List;

/**
 * @author AKH
 */
public interface LivraisonDetailService {

    LivraisonDetail findByReference(String reference);

    List<LivraisonDetail> findAll();

    int validateLivraisonDetail(Livraison livraison, List<LivraisonDetail> livraisonDetails);

    int save(Livraison livraison, List<LivraisonDetail> livraisonDetails);

    List<LivraisonDetail> findByLivraisonReference(String reference);

    int deleteByLivraisonReference(String reference);

}
