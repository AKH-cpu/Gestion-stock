/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.FamilleProduit;
import com.projet.stock.repository.FamilleProduitRepository;
import com.projet.stock.service.facade.FamilleProduitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KHALID
 */
@Service
public class FamilleProduitServiceImpl implements FamilleProduitService{

    @Autowired
     private FamilleProduitRepository familleProduitRepository;
    
    @Override
    public FamilleProduit findByLibelle(String libelle) {
        return familleProduitRepository.findByLibelle(libelle);
    }

    @Override
    public List<FamilleProduit> findAll() {
      return familleProduitRepository.findAll();
    }

    @Override
    public int save(FamilleProduit familleProduit) {
        FamilleProduit f=familleProduitRepository.findByLibelle(familleProduit.getLibelle());
        if(f!=null) return -1;
        else {
            familleProduitRepository.save(familleProduit);
            return 1;
        }
    }

    @Override
    public int deleteByLibelle(String libelle) {
        return familleProduitRepository.deleteByLibelle(libelle);
    }
    
}
