/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.ExpressionBesoinDetail;
import com.projet.stock.bean.Produit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anoir
 */
@Repository
public interface ExpressionBesoinDetailRepository extends JpaRepository<ExpressionBesoinDetail, Long> {

    public List<ExpressionBesoinDetail> findByQte(Double qte);

    public List<ExpressionBesoinDetail> findByQteLivre(Double qteLivre);

}
