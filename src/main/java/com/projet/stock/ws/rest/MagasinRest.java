/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Stock;
import com.projet.stock.service.facade.MagasinService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("gestion-stock-v1/Magasin")
public class MagasinRest {
    
    @Autowired
    MagasinService magasinService;
    
    @GetMapping("/reference/{reference}")
    public Magasin findByReference(@PathVariable String reference){
        return magasinService.findByReference(reference);
    }
    
    @GetMapping("/findAll")
    public List<Magasin> findAll(){
        return magasinService.findAll();
    }
    
    
    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        return magasinService.deleteAll();
    }
    
    @PostMapping("/")
    public int save (@RequestBody Magasin magasin){
        return magasinService.save(magasin);
    }
    
    @DeleteMapping("/delete/{reference}")
    public int deleteByReference(@PathVariable String reference){
        return magasinService.deleteByReference(reference);
    }
    
    
//    @PutMapping("/insertToMagasin/produit/{reference}/magasin/{refMagasin}")
//    public int insertProduitToMagasin(@PathVariable String reference,@PathVariable String refMagasin){
//        return magasinService.insertProduitToMagasin(reference, refMagasin);
//    }
//    
//    @PutMapping("/deleteFromMagasin/produit/{reference}/magasin/{refMagasin}")
//    public int deleteProduitFromMagasin(@PathVariable String reference,@PathVariable String refMagasin){
//        return magasinService.deleteProduitFromMagasin(reference, refMagasin);
//    }
    
    @PutMapping("/addProducts/refStock/{refStock}/refProd/{refProd}/refMag/{refMag}/quantiteMaxProdInMag/{quantiteMaxProdInMag}/quantite/{quantite}")
    public int addProduitsToMagasin(@PathVariable String refStock,@PathVariable String refProd,@PathVariable String refMag,@PathVariable double quantiteMaxProdInMag ,@PathVariable double quantite){
        return magasinService.addProduitsToMagasin(refStock, refProd, refMag, quantiteMaxProdInMag, quantite);
    }
    
 
    @GetMapping("/findStock/refMag/{refMagasin}/refProd/{refProduit}")
    public Stock findStockByRefMagasinAndRefProduit(@PathVariable String refMagasin,@PathVariable String refProduit){
        return magasinService.findStockByRefMagasinAndRefProduit(refMagasin, refProduit);
    }
    
    @PutMapping("/isProduitInMagasin/{refMagasin}/refProd/{refProduit}")
    public boolean isProduitInMagasin(@PathVariable String refMagasin,@PathVariable String refProduit){
        return magasinService.isProduitInMagasin(refMagasin, refProduit);
    }
    
    @PutMapping("/updateQuantiteProdInStock/refStock/{refStock}/refMagasin/{refMag}/refProduit/{refProd}/nvQuantite/{nvQuantite}")
    public int updateQuantiteProdInStock(@PathVariable String refStock,@PathVariable String refMag,@PathVariable String refProd,@PathVariable double nvQuantite){
        return magasinService.updateQuantiteProdInStock(refStock, refMag, refProd, nvQuantite);
    }
    
    @PutMapping("/removeProductsFromMagasin/refMag/{refMag}/refProd/{refProd}/quantiteToRemove/{quantiteToRemove}")
    public int removeProduitsfromMagasin(@PathVariable String refProd,@PathVariable String refMag,@PathVariable double quantiteToRemove){
        return magasinService.removeProduitsfromMagasin(refProd, refMag, quantiteToRemove);
    }
    
}
