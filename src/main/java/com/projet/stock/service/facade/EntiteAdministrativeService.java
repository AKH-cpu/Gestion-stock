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
    public int save (EntiteAdministrative entiteAdministrative);
//    public List<Magasin> find
    
}
