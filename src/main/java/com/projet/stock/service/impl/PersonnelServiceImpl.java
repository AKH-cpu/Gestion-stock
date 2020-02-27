/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Personnel;
import com.projet.stock.repository.PersonnelRepository;
import com.projet.stock.service.facade.PersonnelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public List<Personnel> findAll() {
        return personnelRepository.findAll();
    }

    @Override
    public List<Personnel> findByFonction(String fonction) {
        return personnelRepository.findByFonction(fonction);
    }

    @Override
    public Personnel findByCode(String code) {
        return personnelRepository.findByCode(code);
    }

    @Override
    public int save(Personnel personnel) {
        Personnel foundedPersonnel = personnelRepository.findByCode(personnel.getCode());
        if (foundedPersonnel != null) {
            return -1;
        } //si il est un chef, il faut avoir un seul chef dans chaque fonction.
        else if (personnelRepository.findByFonction("chef").contains(foundedPersonnel)) {
            return -2;
        } else {
            personnelRepository.save(foundedPersonnel);
            return 1;
        }
    }
}
