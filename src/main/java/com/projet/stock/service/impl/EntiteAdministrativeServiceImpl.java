/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.service.facade.*;
import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.repository.EntiteAdministrativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class EntiteAdministrativeServiceImpl implements EntiteAdministrativeService{

    @Autowired
    private EntiteAdministrativeRepository entiteAdministrativeRepository;
    @Override
    public EntiteAdministrative findByNom(String nom) {
        return entiteAdministrativeRepository.findByNom(nom);
    }
    
   
    
}
