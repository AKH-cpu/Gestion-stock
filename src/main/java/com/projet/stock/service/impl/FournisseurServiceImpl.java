/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Fournisseur;
import com.projet.stock.repository.FournisseurRepository;
import com.projet.stock.service.facade.FournisseurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KHALID
 */
@Service
public class FournisseurServiceImpl implements FournisseurService{

    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Override
    public Fournisseur findByReference(String reference) {
         return fournisseurRepository.findByReference(reference);
    }

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    @Override
    public int save(Fournisseur fournisseur) {
        
       Fournisseur f=fournisseurRepository.findByReference(fournisseur.getReference());
        
       if(f!=null) return -1;
       else{
           fournisseurRepository.save(fournisseur);
           return 1;
       }
    }

    @Override
    public int deleteByReference(String reference) {
      return fournisseurRepository.deleteByReference(reference);
    }
    
}
