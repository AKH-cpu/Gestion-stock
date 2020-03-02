/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Fournisseur;
import java.util.List;

/**
 *
 * @author KHALID
 */
public interface FournisseurService {
    public Fournisseur findByReference(String reference);
    public List<Fournisseur> findAll();
    public int save(Fournisseur fournisseur);
    public int deleteByReference(String reference);
}
