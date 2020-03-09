/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Produit;
import com.projet.stock.service.facade.MagasinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.stock.repository.MagasinRepository;
import com.projet.stock.service.facade.ProduitService;

/**
 *
 * @author lenovo
 */
@Service
public class MagasinServiceImpl implements MagasinService {

    @Autowired
    private MagasinRepository magasinRepository;
//    @Autowired
//    private EntiteAdministrativeService entiteAdministrativeService;
    @Autowired
    private ProduitService ProduitService;

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
        EntiteAdministrative foundedEntite = magasin.getEntiteAdministrative();

        if (foundedMagasin != null) {
            //magasin deja existe
            //"magasin alredy existed"
            return -1;
        } else if (foundedEntite == null) {
            //entite not found
            //Entite associated with this Magasin is not found
            return -2;

        } else if (foundedEntite.getChef() == null) {

            //chaque entite dois avoir un chef
            return -3;
        } else {
            magasin.setEntiteAdministrative(foundedEntite);
            magasin.getEntiteAdministrative().setChef(foundedEntite.getChef());
            magasinRepository.save(magasin);
            return 1;
        }
    }

    @Override
    public int deleteByReference(String reference) {
        Magasin foundedMagasin = magasinRepository.findByReference(reference);

        if (foundedMagasin == null) {
            //No Magasin matched with this reference
            return -1;
        } else {
            magasinRepository.delete(foundedMagasin);
            return 1;
        }

    }

    @Override
    public String deleteAll() {
        magasinRepository.deleteAll();
        return "Magasins are deleted";

    }

    @Override
    public int insertProduitToMagasin(String reference, String refMagasin) {
        Magasin foundedMagasin = magasinRepository.findByReference(refMagasin);
        List<Produit> produits = ProduitService.findAll();

        if (foundedMagasin != null) {

            while (foundedMagasin.getNbrProduit() < foundedMagasin.getNbrMAxProduit()) {

                for (Produit p : produits) {
                    if (p.getReference().equals(reference)) {

                        foundedMagasin.getProduitsMagasin().add(p);
                        foundedMagasin.setNbrMAxProduit(foundedMagasin.getNbrProduit() + 1);
                        return 1;

                    }

                }

            }

        }
        return -1;
    }

    @Override
    public int deleteProduitFromMagasin(String reference, String refMagasin) {
        Magasin foundedMagasin = magasinRepository.findByReference(refMagasin);
        if (foundedMagasin != null) {
            while (foundedMagasin.getNbrProduit() > 0) {

                for (Produit p : foundedMagasin.getProduitsMagasin()) {
                    if (p.getReference().equals(reference)) {

                        foundedMagasin.getProduitsMagasin().remove(p);
                        foundedMagasin.setNbrMAxProduit(foundedMagasin.getNbrProduit() - 1);

                        return 1;

                    }

                }

            }

        }
        return -1;
    }

}
