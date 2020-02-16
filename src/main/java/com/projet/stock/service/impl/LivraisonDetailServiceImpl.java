/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.LivraisonDetail;
import com.projet.stock.repository.LivraisonDetailRepository;
import com.projet.stock.service.facade.LivraisonDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AKH
 */
@Service
public class LivraisonDetailServiceImpl implements LivraisonDetailService{
    
    @Autowired
    LivraisonDetailRepository livraisonDetailRepository;
    
    @Override
    public LivraisonDetail findbyReference(String reference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int save(LivraisonDetail livraisonDetail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LivraisonDetail> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
