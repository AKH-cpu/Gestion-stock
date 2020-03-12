/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface EntiteAdministrativeService {

    public EntiteAdministrative findByReference(String reference);

    public List<EntiteAdministrative> findAll();

    public int save(EntiteAdministrative entiteAdministrative);

    public int deleteByReference(String reference);

    public String deleteAll();

    public EntiteAdministrative findByNom(String nom);

    public List<Magasin> findMagasinLibre(String refEntite);

    public List<Magasin> magasinBesoinsDeProduits(String refEntite);

    public int AddEmployeToMagasin(String code, String refMagasin);

    public int RemoveEmployeFromMagasin(String code, String refMagasin);
    
    public int findMagasinByReference (String reference);
    
    public Boolean isEployeExistInMagasin(String code, String refMagasin);
}
