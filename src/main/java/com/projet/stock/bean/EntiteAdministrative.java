/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * @author AKH
 */
@Entity
public class EntiteAdministrative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private String nom;

    @OneToMany(mappedBy = "entiteAdministrative")
    private List<ExpressionBesoin> expressionBesoins;

    @OneToMany(mappedBy = "entiteAdministrative")
    private List<Magasin> magasins;


    @OneToMany(mappedBy = "entiteAdministrative")
    private List<Personnel> employes;

    @OneToOne
    private Personnel chef;

    public List<Personnel> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Personnel> employes) {
        this.employes = employes;
    }

    public List<ExpressionBesoin> getExpressionBesoins() {
        return expressionBesoins;
    }

    public void setExpressionBesoins(List<ExpressionBesoin> expressionBesoins) {
        this.expressionBesoins = expressionBesoins;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public Personnel getChef() {
        return chef;
    }

    public void setChef(Personnel chef) {
        this.chef = chef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
        if (!(object instanceof EntiteAdministrative)) {
            return false;
        }
        EntiteAdministrative other = (EntiteAdministrative) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.projet.stock.bean.EntiteAdministrative[ id=" + id + " ]";
    }

}
