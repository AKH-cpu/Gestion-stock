/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 * @author AKH
 */
@Entity
public class ExpressionBesoin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateExpressionBesoin;

    @OneToOne
    private EntiteAdministrative entiteAdministrative;
    @OneToOne
    private Personnel personnel;

    @OneToMany(mappedBy = "expressionBesoin")
    private List<ExpressionBesoinDetail> expressionBesoinDetails;

    @OneToOne(mappedBy = "expressionBesoin")
    private Livraison livraison;

    public Long getId() {
        return id;
    }

    public List<ExpressionBesoinDetail> getExpressionBesoinDetails() {
        return expressionBesoinDetails;
    }

    public void setExpressionBesoinDetails(List<ExpressionBesoinDetail> expressionBesoinDetails) {
        this.expressionBesoinDetails = expressionBesoinDetails;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDateExpressionBesoin() {
        return dateExpressionBesoin;
    }

    public void setDateExpressionBesoin(Date dateExpressionBesoin) {
        this.dateExpressionBesoin = dateExpressionBesoin;
    }

    public EntiteAdministrative getEntiteAdministrative() {
        return entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrative entiteAdministrative) {
        this.entiteAdministrative = entiteAdministrative;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
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
        if (!(object instanceof ExpressionBesoin)) {
            return false;
        }
        ExpressionBesoin other = (ExpressionBesoin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.projet.stock.bean.ExpressionBesoin[ id=" + id + " ]";
    }

}
