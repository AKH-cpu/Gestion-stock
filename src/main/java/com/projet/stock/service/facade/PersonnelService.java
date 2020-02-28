/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.Personnel;
import java.util.List;

/**
 *
 * @author anoir
 */
public interface PersonnelService {

    List<Personnel> findAll();

    Personnel findByCode(String code);
    
    List<Personnel> findByFonction(String fonction);
    
    int save(Personnel personnel);

}
