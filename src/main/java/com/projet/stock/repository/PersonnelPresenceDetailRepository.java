/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.PersonnelPresenceDetail;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author anoir
 */
@Repository
public interface PersonnelPresenceDetailRepository extends JpaRepository<PersonnelPresenceDetail, Long>{
    
    PersonnelPresenceDetail findByStatus(String status);
    
    PersonnelPresenceDetail findByReference(String reference);
    
//    void deleteByReference (String reference); 
    
    
}
