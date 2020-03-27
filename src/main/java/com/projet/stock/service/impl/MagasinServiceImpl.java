/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Produit;
import com.projet.stock.bean.Stock;
import com.projet.stock.service.facade.MagasinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.stock.repository.MagasinRepository;
import com.projet.stock.repository.StockRepository;
import com.projet.stock.service.facade.EntiteAdministrativeService;
import com.projet.stock.service.facade.ProduitService;
import com.projet.stock.service.facade.StockService;
import java.util.ArrayList;

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

    @Autowired
    private EntiteAdministrativeService entiteAdministrativeService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

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
//            magasin.getEntiteAdministrative().setChef(foundedEntite.getChef());
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

//    @Override
//    public int insertProduitToMagasin(String reference, String refMagasin) {
//        Magasin foundedMagasin = magasinRepository.findByReference(refMagasin);
//        List<Produit> produits = ProduitService.findAll();
//
//        if (foundedMagasin != null) {
//
//            while (foundedMagasin.getNbrProduit() < foundedMagasin.getNbrMAxProduit()) {
//
//                for (Produit p : produits) {
//                    if (p.getReference().equals(reference)) {
//
//                        Stock s = new Stock(Long.MIN_VALUE, foundedMagasin, p);
//                        foundedMagasin.getProduitsMagasin().add(s);
//                        foundedMagasin.setNbrMAxProduit(foundedMagasin.getNbrProduit() + 1);
//                        return 1;
//
//                    }
//
//                }
//
//            }
//
//        }
//        return -1;
//    }
//    @Override
//    public int deleteProduitFromMagasin(String reference, String refMagasin) {
//        Magasin foundedMagasin = magasinRepository.findByReference(refMagasin);
//        if (foundedMagasin != null) {
//            while (foundedMagasin.getNbrProduit() > 0) {
//
//                for (Stock s : foundedMagasin.getProduitsMagasin()) {
//                    if (s.getProduit().getReference().equals(reference)) {
//
//                        foundedMagasin.getProduitsMagasin().remove(s);
//                        foundedMagasin.setNbrMAxProduit(foundedMagasin.getNbrProduit() - 1);
//
//                        return 1;
//
//                    }
//
//                }
//
//            }
//
//        }
//        return -1;
//    }
    @Override
    public int addProduitsToMagasin(String refStock, String refProd, String refMag, double quantiteMaxProdInMag, double quantite) {
        Magasin foundedMagasin = magasinRepository.findByReference(refMag);
        Produit foundedProduit = ProduitService.findByReference(refProd);
        Stock foundedStock = stockService.findByReference(refStock);
        Stock loadStock = findStockByRefMagasinAndRefProduit(refMag, refProd);

        if (foundedMagasin == null) {
            return -1;
        } else if (foundedProduit == null) {
            return -2;
        } else if (foundedMagasin.getNbrMAxProduit() < (foundedMagasin.getNbrProduit() + quantite)) {
            return -4;
        } else if (foundedStock != null && foundedStock.getMagasin().getReference().equals(refMag) && foundedStock.getProduit().getReference().equals(refProd)) {

            foundedStock.setQte(foundedStock.getQte() + quantite);
            foundedMagasin.setNbrProduit(foundedMagasin.getNbrProduit() + quantite);
            foundedStock.setMagasin(foundedMagasin);
            foundedStock.setProduit(foundedProduit);
            stockRepository.save(foundedStock);
            return 11;

//        } else if (foundeStock != null) {
//            return -5;
        } else {

            if (loadStock != null) {
                loadStock.setQte(loadStock.getQte() + quantite);
                foundedMagasin.setNbrProduit(foundedMagasin.getNbrProduit() + quantite);
                loadStock.setMagasin(foundedMagasin);
                loadStock.setProduit(foundedProduit);
                stockRepository.save(loadStock);
            } else {

                foundedMagasin.setNbrProduit(foundedMagasin.getNbrProduit() + quantite);
                Stock stock = new Stock(Long.MIN_VALUE, refStock, quantite, quantiteMaxProdInMag, foundedMagasin, foundedProduit);

                stock.setMagasin(foundedMagasin);
                stock.setProduit(foundedProduit);
                stockService.save(stock);
                //            List<Stock> magasinsProduits =  foundedMagasin.getProduitsMagasin();
//            magasinsProduits.add(stock);

            }

            return 1;
        }

    }

    @Override
    public int removeProduitsfromMagasin(String refProd, String refMag, double quantiteToRemove) {
        Magasin foundedMagasin = magasinRepository.findByReference(refMag);
        Produit foundedProduit = ProduitService.findByReference(refProd);
        Stock foundedStock = findStockByRefMagasinAndRefProduit(refMag, refProd);
        
        if (foundedMagasin == null) {
            return -1;
        } else if (foundedProduit == null) {
            return -2;
        } else if (foundedStock == null) {
            return -3; 
        } else if (foundedStock.getQte() < quantiteToRemove) {
            return -4;
        }else {
            
            foundedStock.setQte(foundedStock.getQte() - quantiteToRemove);
            foundedMagasin.setNbrProduit(foundedMagasin.getNbrProduit() - quantiteToRemove);
            foundedStock.setMagasin(foundedMagasin);
            foundedStock.setProduit(foundedProduit);
            stockRepository.save(foundedStock);
            return 1;
        }
        
        
    }

    @Override
    public boolean isProduitInMagasin(String refMagasin, String refProduit) {
        return findStockByRefMagasinAndRefProduit(refMagasin, refProduit) != null;
    }

    @Override
    public Stock findStockByRefMagasinAndRefProduit(String refMagasin, String refProduit) {

        List<Stock> stokes = stockService.findAll();

        for (Stock stoke : stokes) {
            if (stoke.getMagasin().getReference().equals(refMagasin) && stoke.getProduit().getReference().equals(refProduit)) {

                return stoke;

            }

        }
        return null;
    }

    @Override
    public int updateQuantiteProdInStock(String refStock, String refMag, String refProd, double nvQuantite) {
        Stock foundedStock = stockService.findByReference(refStock);
        Magasin foundedMagasin = magasinRepository.findByReference(refMag);
        Produit foundedProduit = ProduitService.findByReference(refProd);
        if (foundedMagasin == null) {
            return -1;
        } else if (foundedProduit == null) {
            return -2;
        } else if (foundedStock == null) {
            return -3;

        } else if (foundedStock.getMagasin().getReference().equals(refMag) && foundedStock.getProduit().getReference().equals(refProd)) {

            foundedStock.setQte(nvQuantite);
            foundedStock.setMagasin(foundedMagasin);
            foundedStock.setProduit(foundedProduit);
            stockRepository.save(foundedStock);

            return 1;
        } else {
            //ce stock existe ms avec un autre produit ou un autre Magasin
            return -1111;
        }

    }

    @Override
    public List<Stock> findStocksByMagasin(String refMagasin) {
        Magasin foundedMagasin = magasinRepository.findByReference(refMagasin);
        if(foundedMagasin == null){
            System.out.println("Magasin not found");
            return null;
        }else {
            return foundedMagasin.getStokes();
        }
    }

    
}
