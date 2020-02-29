/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Magasin;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface MagasinService {
    
    public Magasin findByReference(String reference);
    public List<Magasin> findAll();
    public int save (Magasin magasin);
    
    //public Magasin deleteByReference(String reference);
    
    
}
