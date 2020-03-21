/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author AKH
 */
@Entity
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String nom;
    private String telephone;
    private String fonction;
    private Double seniorityScore;
    private Double salary;
    private Double yearsExp;
    private String codeChef;
    //one month worked = +3 rewardPoints ( 1hr = 0.0125)
    private Double points;
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEmbauche;

    @ManyToOne
    private EntiteAdministrative entiteAdministrative;

    public Personnel() {
    }

    public Personnel(Long id, String code, String nom, String telephone, String fonction, Double seniorityScore, Double salary, Double yearsExp, String codeChef, Date dateEmbauche, EntiteAdministrative entiteAdministrative) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.telephone = telephone;
        this.fonction = fonction;
        this.seniorityScore = seniorityScore;
        this.salary = salary;
        this.yearsExp = yearsExp;
        this.codeChef = codeChef;
        this.dateEmbauche = dateEmbauche;
        this.entiteAdministrative = entiteAdministrative;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public EntiteAdministrative getEntiteAdministrative() {
        return entiteAdministrative;
    }

    public void setEntiteAdministrative(EntiteAdministrative entiteAdministrative) {
        this.entiteAdministrative = entiteAdministrative;
    }

    public Double getSeniorityScore() {
        return seniorityScore;
    }

    public void setSeniorityScore(Double seniorityScore) {
        this.seniorityScore = seniorityScore;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getYearsExp() {
        return yearsExp;
    }

    public void setYearsExp(Double yearsExp) {
        this.yearsExp = yearsExp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeChef() {
        return codeChef;
    }

    public void setCodeChef(String codeChef) {
        this.codeChef = codeChef;
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
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.projet.stock.bean.Personnel[ id=" + id + " ]";
    }
}
