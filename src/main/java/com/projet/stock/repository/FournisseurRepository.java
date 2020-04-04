/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.Fournisseur;
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
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{
    
    public Fournisseur findByReference(String reference);
    public int deleteByReference(String reference);
    @Query("SELECT f FROM Fournisseur f WHERE f.reference LIKE :ref")
    public List<Fournisseur> findByReferenceLike(@Param(value = "ref")String reference);
}
