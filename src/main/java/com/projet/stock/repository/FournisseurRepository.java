/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KHALID
 */

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{
    
    public Fournisseur findByReference(String reference);
}
