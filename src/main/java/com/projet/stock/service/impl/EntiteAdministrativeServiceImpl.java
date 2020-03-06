/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.service.facade.*;
import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.repository.EntiteAdministrativeRepository;
import java.util.List;
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
    public EntiteAdministrative findByReference(String reference) {
        return entiteAdministrativeRepository.findByReference(reference);
    }
  

    @Override
    public List<EntiteAdministrative> findAll() {
        return entiteAdministrativeRepository.findAll();
    }

    @Override
    public int save(EntiteAdministrative entiteAdministrative) {
        EntiteAdministrative foundedEntite = entiteAdministrativeRepository.findByReference(entiteAdministrative.getReference());
        if (foundedEntite != null){
            //entite existe deja 
            return -1;
        }else if (entiteAdministrative.getNom() == null || entiteAdministrative.getChef() == null ){
            //l'entite dois avoir un chef 
            return -2;
        }else{
            entiteAdministrativeRepository.save(entiteAdministrative);
            return 1;
        }
    }

    @Override
    public int deleteByReference(String reference) {
        EntiteAdministrative foundedEntite = entiteAdministrativeRepository.findByReference(reference);
        if (foundedEntite == null){
            //"Entite not found"
            return -1;   
        }else {
            
            entiteAdministrativeRepository.delete(foundedEntite);
            return 1;
        }
        
    }

    @Override
    public String deleteAll() {
        entiteAdministrativeRepository.deleteAll();
        return "the entities are deleted";
    }

    @Override
    public EntiteAdministrative findByNom(String nom) {
        return entiteAdministrativeRepository.findByNom(nom);
    }
    
    
    
   
    
}
