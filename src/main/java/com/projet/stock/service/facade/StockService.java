/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Stock;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author KHALID
 */
public interface StockService {
    public List<Stock> findAll();
    public int save(Stock stock);
    public Stock findByReference(String reference);
    public int deleteByReference(String reference);
    public List<Stock> findByReferenceLike(String reference);
}
