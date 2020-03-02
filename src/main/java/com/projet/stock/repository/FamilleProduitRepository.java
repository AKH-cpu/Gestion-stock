/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.FamilleProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KHALID
 */
@Repository
public interface FamilleProduitRepository extends JpaRepository<FamilleProduit,Long>{
    public FamilleProduit findByLibelle(String libelle);
    public int deleteByLibelle(String libelle);
}
