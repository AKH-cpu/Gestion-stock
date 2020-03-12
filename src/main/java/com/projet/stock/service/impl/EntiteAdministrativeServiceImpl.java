/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.service.facade.*;
import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Personnel;
import com.projet.stock.bean.Stock;
import com.projet.stock.repository.EntiteAdministrativeRepository;
import com.projet.stock.repository.MagasinRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class EntiteAdministrativeServiceImpl implements EntiteAdministrativeService {

    @Autowired
    private EntiteAdministrativeRepository entiteAdministrativeRepository;

    @Autowired
    private MagasinService magasinService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private MagasinRepository magasinRepository;

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
        if (foundedEntite != null) {
            //entite existe deja 
            return -1;
        } else if (entiteAdministrative.getNom() == null || entiteAdministrative.getChef() == null) {
            //l'entite dois avoir un chef et un nom
            return -2;
        } else {
            entiteAdministrativeRepository.save(entiteAdministrative);
            return 1;
        }
    }

    @Override
    public int deleteByReference(String reference) {
        EntiteAdministrative foundedEntite = entiteAdministrativeRepository.findByReference(reference);
        if (foundedEntite == null) {
            //"Entite not found"
            return -1;
        } else {
            //suppression de l entite trouve dapres sa reference
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

    @Override
    public List<Magasin> findMagasinLibre(String reference) {
        EntiteAdministrative foundedEntite = entiteAdministrativeRepository.findByReference(reference);
        //Creation d une liste vide dont je vais stocker les magasins vide de produits
        List<Magasin> magasinsLibre = null;
        if (foundedEntite != null) {
            //get les magasins de l entite associer
            List<Magasin> magasins = foundedEntite.getMagasins();
            for (Magasin magasin : magasins) {
                if (magasin.getNbrProduit() == 0) {

                    //ajout de la magisin dans la liste cree
                    magasinsLibre.add(magasin);

                }
            }
        }
        return magasinsLibre;
    }

    @Override
    public int AddEmployeToMagasin(String code, String refMagasin) {
        Personnel foundedEmploye = personnelService.findByCode(code);
        Magasin foundedMagasin = magasinService.findByReference(refMagasin);
        if (foundedEmploye == null) {
            //l'employe n'existe pas 
            return -1;
        } else if (findMagasinByReference(refMagasin) != 1) {
            // Magasin not found : pas de magasin avec la reference entree
            return -2;
        } else if (isEployeExistInMagasin(code, refMagasin) || isEployeExistInMagasin(code, refMagasin) == null) {
            //l' employe appartient deja a ce magasin 
            return -3;
        } else {
            List<Personnel> employes = foundedMagasin.getEmployes();
            employes.add(foundedEmploye);
            foundedMagasin.setEmployes(employes);
            magasinRepository.save(foundedMagasin);
            return 1;
        }

    }

    @Override
    public int RemoveEmployeFromMagasin(String code, String refMagasin) {
        Personnel foundedEmploye = personnelService.findByCode(code);
        Magasin foundedMagasin = magasinService.findByReference(refMagasin);
        if (foundedEmploye == null) {
            //l'employe n'existe pas 
            return -1;

        } else if (findMagasinByReference(refMagasin) != 1) {
            //le magasin not found

            return -2;
        } else if (!isEployeExistInMagasin(code, refMagasin)) {
            //l'employe n'appartient pas à ce magasin
            return -3;

        } else {
            List<Personnel> employes = foundedMagasin.getEmployes();
            employes.remove(foundedEmploye);
            foundedMagasin.setEmployes(employes);
            magasinRepository.save(foundedMagasin);
            return 1;
        }
    }

    @Override
    public Boolean isEployeExistInMagasin(String code, String refMagasin) {
        if (findMagasinByReference(refMagasin) == 1) {
            List<Personnel> employes = magasinService.findByReference(refMagasin).getEmployes();
            for (Personnel employe : employes) {
                if (employe == personnelService.findByCode(code)) {
                    //employe existe dans ce mag
                    return true;
                }
            }
            //employe n'appartient pas a ce mag
            return false;
        } else {
            //magasin not found
            return null;
        }
    }

    @Override
    public int findMagasinByReference(String reference) {
        Magasin foundeMagasin = magasinService.findByReference(reference);
        if (foundeMagasin != null) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public List<Magasin> magasinsBesoinsDeProduits(String refEntite) {
        EntiteAdministrative foundedEntite = entiteAdministrativeRepository.findByReference(refEntite);
        //creation d_une liste
        List<Magasin> magasinsNeededProducts = null;
        if (foundedEntite != null) {
            List<Magasin> magasins = foundedEntite.getMagasins();
            for (Magasin magasin : magasins) {
                List<Stock> produitsMagasin = magasin.getProduitMagasin();
                for (Stock produit : produitsMagasin) {
                    if (produit.getQuantiteMax() > produit.getQte()) {
                        if (!isMagasinAlredyNeedProduct(magasin, magasinsNeededProducts)) {
                            magasinsNeededProducts.add(magasin);
                        }
                    }
                }

            }

        }
        return magasinsNeededProducts;
    }

    //cette methode me permer de tester si magasin est deja dans la liste des magasins qui sont besoins de produits ou non
    public Boolean isMagasinAlredyNeedProduct(Magasin magasin, List<Magasin> magasinsNeedProduct) {
        for (Magasin m : magasinsNeedProduct) {
            if (m == magasin) {
                return true;
            }
        }
        return false;
    }

    @Override
    public EntiteAdministrative findByPersonnelCodeChef(String codeChef) {

        Personnel foundedChef = personnelService.findByCode(codeChef);
        if (foundedChef != null) {
            return foundedChef.getEntiteAdministrative();
        } else {
            return null;
        }

    }

}
