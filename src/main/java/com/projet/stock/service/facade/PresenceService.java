/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Presence;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface PresenceService {
    
  
    
    Presence findByReference(String reference);
    
    List<Presence> findAll();
    
    int save(Presence presence);
    
    
}
