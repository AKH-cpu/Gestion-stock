/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Stock;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface MagasinService {
    
    public Magasin findByReference(String reference);
    public List<Magasin> findAll();
    public int save (Magasin magasin);
    public int deleteByReference(String reference);
    public String deleteAll();
//    public int insertProduitToMagasin (String reference, String refMagasin);
//    public int deleteProduitFromMagasin (String reference, String refMagasin);
    public int addProduitsToMagasin (String refStock, String refProd, String refMag,double quntiteMaxProdInMag, double quantite);
    public int removeProduitsfromMagasin (String refProd, String refMag, double quantite);
    
    public boolean isProduitInMagasin(String refMagasin, String refProduit);
    
    public Stock findStockByRefMagasinAndRefProduit(String refMagasin, String refProduit);
    
    public int updateQuantiteProdInStock(String refStock, String refMag, String refProd, double nvQuantite);
    
    
}
