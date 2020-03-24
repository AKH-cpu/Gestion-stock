/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Livraison;
import com.projet.stock.bean.LivraisonDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author AKH
 */
public interface LivraisonService {

    Livraison findByReference(String reference);

    List<Livraison> findAll();

    int save(Livraison livraison, List<LivraisonDetail> livraisonDetails);

    int deleteByReference(String reference);
}
