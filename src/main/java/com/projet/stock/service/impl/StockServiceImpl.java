/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.Magasin;
import com.projet.stock.bean.Produit;
import com.projet.stock.bean.Stock;
import com.projet.stock.repository.StockRepository;
import com.projet.stock.service.facade.MagasinService;
import com.projet.stock.service.facade.ProduitService;
import com.projet.stock.service.facade.StockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KHALID
 */
@Service
public class StockServiceImpl implements StockService{
    @Autowired
     private StockRepository stockRepository;
     private MagasinService magasinService;
     private ProduitService produitService;

    @Override
    public Stock findByReference(String reference) {
        return stockRepository.findByReference(reference);
     }

    @Override
    public int deleteByReference(String reference) {
      return stockRepository.deleteByReference(reference);
    }

    @Override
    public List<Stock> findAll() {
       return stockRepository.findAll();
    }

    @Override
    public int save(Stock stock) {
       Stock foundedStock=findByReference(stock.getReference());
       Magasin foundedMagasin=magasinService.findByReference(stock.getMagasin().getReference());
       Produit foundedProduit=produitService.findByReference(stock.getProduit().getReference());
       if(foundedStock!=null) return -1;
       else if(foundedMagasin==null) return -2;
       else if(foundedProduit==null) return -3;
       else{
           stock.setMagasin(foundedMagasin);
           stock.setProduit(foundedProduit);
           stockRepository.save(stock);
           return 1;
       }
       
    }

    @Override
    public List<Stock> findByReferenceLike(String reference) {
       return stockRepository.findByReferenceLike(reference + "%");
    }
    
}
