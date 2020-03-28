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

    List<Produit> findByFamilleProduitLibelle(String libelle);

    @Query(value = "SELECT * FROM produit p WHERE p.prix_unitairettc>= :prixMin AND p.prix_unitairettc= :prixMax ", nativeQuery = true)
    List<Produit> findByPrixBetween(@Param("prixMin") double prixMin, @Param("prixMax") double prixMax);

}
