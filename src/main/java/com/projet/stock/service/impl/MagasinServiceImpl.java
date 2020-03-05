/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import com.projet.stock.service.facade.EntiteAdministrativeService;
import com.projet.stock.service.facade.MagasinService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.stock.repository.MagasinRepository;

/**
 * @author lenovo
 */
@Service
public class MagasinServiceImpl implements MagasinService {

    @Autowired
    private MagasinRepository magasinRepository;
    @Autowired
    private EntiteAdministrativeService entiteAdministrativeService;

    @Override
    public Magasin findByReference(String refernce) {
        return magasinRepository.findByReference(refernce);

    }

    @Override
    public List<Magasin> findAll() {
        return magasinRepository.findAll();
    }

    @Override
    public int save(Magasin magasin) {
        Magasin foundedMagasin = magasinRepository.findByReference(magasin.getReference());
        EntiteAdministrative foundedEntite = entiteAdministrativeService.findByReference(magasin.getEntiteAdministrative().getReference());

        if (foundedMagasin != null) {
            //magasin deja existe
            return -1;
        } else if (foundedEntite == null) {
            //entite not found
            return -2;
        } else {
            magasinRepository.save(magasin);
            return 1;
        }
    }
}
