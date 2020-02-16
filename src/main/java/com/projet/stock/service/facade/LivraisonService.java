/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Livraison;
import java.util.List;

/**
 *
 * @author AKH
 */
public interface LivraisonService {

    Livraison findbyReference(String reference);

    int save(Livraison livraison);

    List<Livraison> findAll();
}
