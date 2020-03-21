/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Personnel;
import com.projet.stock.bean.PersonnelPresenceDetail;
import com.projet.stock.repository.PersonnelPresenceDetailRepository;
import com.projet.stock.service.calcul.dateCalcul;
import com.projet.stock.service.facade.PersonnelPresenceDetailService;
import com.projet.stock.service.facade.PersonnelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class PersonnelPresenceDetailServcieImpl implements PersonnelPresenceDetailService{

    @Autowired
    private PersonnelPresenceDetailRepository personnelPresenceDetailRepository;
    @Autowired
    private PersonnelService personnelService;
//    @Autowired
//    private Presence presence;
    
    @Override
    public PersonnelPresenceDetail findByStatus(String status) {
        return personnelPresenceDetailRepository.findByStatus(status);
    }

    @Override
    public List<PersonnelPresenceDetail> findAll() {
        return personnelPresenceDetailRepository.findAll();
    }

    @Override
    public int save(PersonnelPresenceDetail personnelPresenceDetail) {
        PersonnelPresenceDetail foundedPersonnelPresenceDetail = findByReference(personnelPresenceDetail.getReference());
        Personnel foundedPersonnel = personnelService.findByCode(personnelPresenceDetail.getPersonnel().getCode());
        if(foundedPersonnelPresenceDetail == null)
            return -1;
        else if(foundedPersonnel == null)
            return -2 ;
        else if(personnelPresenceDetail.getPresence().getTimeIn().getDate() != dateCalcul.currentTime().getDate() || personnelPresenceDetail.getPresence().getTimeOut().getDate()!= dateCalcul.currentTime().getDate()){
            return -3;
        }
        else {
            personnelPresenceDetailRepository.save(personnelPresenceDetail);
            return 1;
        }
    }

    @Override
    public PersonnelPresenceDetail findByReference(String reference) {
        return personnelPresenceDetailRepository.findByReference(reference);
    }
    
}
