/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.PersonnelPresenceDetail;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface PersonnelPresenceDetailService {
    
    
    PersonnelPresenceDetail findByStatus(String status);
    
    PersonnelPresenceDetail findByReference(String reference);
    
    List<PersonnelPresenceDetail> findAll();
    
    int save(PersonnelPresenceDetail personnelPresenceDetail);
    
    
}
