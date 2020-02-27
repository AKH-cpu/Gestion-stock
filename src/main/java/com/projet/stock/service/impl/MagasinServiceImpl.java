/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import com.projet.stock.repository.MagasinDao;
import com.projet.stock.service.facade.EntiteAdministrativeService;
import com.projet.stock.service.facade.MagasinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class MagasinServiceImpl implements MagasinService {

    @Autowired
    MagasinDao magasinDao;
    @Autowired
    EntiteAdministrativeService entiteAdService;

    @Override
    public Magasin findByReference(String refernce) {
        return magasinDao.findByRefernce(refernce);

    }

    @Override
    public List<Magasin> findAll() {
        return magasinDao.findAll();
    }

    @Override
    public int save(Magasin magasin) {
        Magasin foundedMagasin = magasinDao.findByRefernce(magasin.getRefarence());
        EntiteAdministrative foundedEntite = entiteAdService.findbyNom(magasin.getEntiteAdministrative().getNom());

        if (foundedMagasin != null) {
            //magasin deja existe
            return -1;
        } else if (foundedEntite == null) {
            //entite not found
            return -2;
        } else {
            magasinDao.save(magasin);
            return 1;
        }
    }

}
