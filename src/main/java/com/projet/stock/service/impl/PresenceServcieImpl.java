/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Personnel;
import com.projet.stock.bean.Presence;
import com.projet.stock.repository.PresenceRepository;
import com.projet.stock.repository.PresenceRepository;
import com.projet.stock.service.calcul.dateCalcul;
import com.projet.stock.service.facade.PresenceService;
import com.projet.stock.service.facade.PersonnelService;
import com.projet.stock.service.facade.PresenceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class PresenceServcieImpl implements PresenceService{

    @Autowired
    private PresenceRepository presenceRepository;
    
  
    @Override
    public List<Presence> findAll() {
        return presenceRepository .findAll();
    }


    @Override
    public Presence findByReference(String reference) {
        return presenceRepository.findByReference(reference);
    }

    @Override
    public int save(Presence presence) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
