/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AKH
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Produit findByReference(String reference);

    int deleteByReference(String reference);

    @Query(value = "SELECT p.id,p.reference,p.libelle FROM produit p,famille_produit f WHERE f.id=p.famille_produit AND f.libelle=:libelle", nativeQuery = true)
    List<Produit> findByLibelleFamille(@Param("libelle") String libelle);



}
