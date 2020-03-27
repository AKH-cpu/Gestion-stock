/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.Stock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KHALID
 */
@Repository
public interface StockRepository  extends JpaRepository<Stock,Long>{
    public Stock findByReference(String reference);
    public int deleteByReference(String reference);
    @Query("SELECT s FROM Stock s WHERE s.reference LIKE :ref")
    public List<Stock> findByReferenceLike(@Param(value = "ref")String reference);
    
}
