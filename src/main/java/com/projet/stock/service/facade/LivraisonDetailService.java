/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.LivraisonDetail;
import java.util.List;

/**
 *
 * @author AKH
 */
public interface LivraisonDetailService {

    LivraisonDetail findbyReference(String reference);

    int save(LivraisonDetail livraisonDetail);

    List<LivraisonDetail> findAll();

}
