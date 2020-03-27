/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lenovo
 */
@Repository
public interface MagasinRepository extends JpaRepository<Magasin, Long> {

    public Magasin findByReference(String reference);

//    @Query(value = "delete from magasin where 	reference = reference", nativeQuery = true)
//    public int deleteByReference(String reference);
    
}
