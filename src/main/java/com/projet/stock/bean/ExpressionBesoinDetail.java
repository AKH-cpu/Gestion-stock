/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author AKH
 */
@Entity
public class ExpressionBesoinDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double qte;
    private Double qteLivre;

    
    @ManyToOne
    private ExpressionBesoin expressionBesoin;

    @ManyToOne
    private Produit produit;

    public ExpressionBesoin getExpressionBesoin() {
        return expressionBesoin;
    }

    public void setExpressionBesoin(ExpressionBesoin expressionBesoin) {
        this.expressionBesoin = expressionBesoin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getQteLivre() {
        return qteLivre;
    }

    public void setQteLivre(Double qteLivre) {
        this.qteLivre = qteLivre;
    }

//    public ExpressionBesoin getExpressionBesoin() {
//        return expressionBesoin;
//    }
//
//    public void setExpressionBesoin(ExpressionBesoin expressionBesoin) {
//        this.expressionBesoin = expressionBesoin;
//    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExpressionBesoinDetail)) {
            return false;
        }
        ExpressionBesoinDetail other = (ExpressionBesoinDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.projet.stock.bean.ExpressionBesoinDetail[ id=" + id + " ]";
    }

}
