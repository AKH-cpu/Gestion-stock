/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
    private String etat;

    @ManyToOne
    @JsonProperty(access = Access.WRITE_ONLY)
    private Personnel chef;

    @OneToMany(mappedBy = "expressionBesoin")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ExpressionBesoinDetail> expressionBesoinDetails;

//    @OneToOne(mappedBy = "expressionBesoin")
//    private Livraison livraison;
    @ManyToOne
    @JsonProperty(access = Access.WRITE_ONLY)
    private EntiteAdministrative entiteAdministrative;

    public ExpressionBesoin() {
    }

    public ExpressionBesoin() {
    }

    public ExpressionBesoin(Long id, String reference, Date dateExpressionBesoin, String etat, Personnel chef, EntiteAdministrative entiteAdministrative) {
        this.id = id;
        this.reference = reference;
        this.dateExpressionBesoin = dateExpressionBesoin;
        this.etat = etat;
        this.chef = chef;
        this.entiteAdministrative = entiteAdministrative;
    }
    
    

    public ExpressionBesoin(Long id, String reference, Date dateExpressionBesoin, String etat, EntiteAdministrative entiteAdministrative, Personnel chef, List<ExpressionBesoinDetail> expressionBesoinDetails, Livraison livraison) {
        this.id = id;
        this.reference = reference;
        this.dateExpressionBesoin = dateExpressionBesoin;
        this.etat = "non livre";
        this.entiteAdministrative = entiteAdministrative;
        this.chef = chef;
        this.expressionBesoinDetails = expressionBesoinDetails;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public List<ExpressionBesoinDetail> getExpressionBesoinDetails() {
        return expressionBesoinDetails;
    }

    public void setExpressionBesoinDetails(List<ExpressionBesoinDetail> expressionBesoinDetails) {
        this.expressionBesoinDetails = expressionBesoinDetails;
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

    public Personnel getChef() {
        return chef;
    }

    public void setChef(Personnel chef) {
        this.chef = chef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExpressionBesoin other = (ExpressionBesoin) obj;
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateExpressionBesoin, other.dateExpressionBesoin)) {
            return false;
        }
        if (!Objects.equals(this.chef, other.chef)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExpressionBesoin{" + "id=" + id + ", reference=" + reference + ", dateExpressionBesoin=" + dateExpressionBesoin + ", etat=" + etat + ", chef=" + chef + ", expressionBesoinDetails=" + expressionBesoinDetails + ", entiteAdministrative=" + entiteAdministrative + '}';
    }

}
