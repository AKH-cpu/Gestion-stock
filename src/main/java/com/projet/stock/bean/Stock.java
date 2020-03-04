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
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private Double qte;
    private Double qteDiff;

    @ManyToOne
    private Magasin magasin;
    @ManyToOne
    private Produit produit;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getQteDiff() {
        return qteDiff;
    }

    public void setQteDiff(Double qteDiff) {
        this.qteDiff = qteDiff;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Stock() {
    }

    public Stock(Long id, String reference, Double qte, Double qteDiff, Magasin magasin, Produit produit) {
        this.id = id;
        this.reference = reference;
        this.qte = qte;
        this.qteDiff = qteDiff;
        this.magasin = magasin;
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", reference=" + reference + ", qte=" + qte + ", qteDiff=" + qteDiff + ", magasin=" + magasin + ", produit=" + produit + '}';
    }

    
}
