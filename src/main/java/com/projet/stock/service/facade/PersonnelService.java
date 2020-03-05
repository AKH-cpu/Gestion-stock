/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.facade;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.ExpressionBesoin;
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

    Personnel findBySeniorityScore(Double seniorityScore);

    Personnel findBySeniorityScoreGreaterThanEqual(Double value);

    Personnel findBySalary(Double salary);

    Personnel findByYearsExp(Double yearsExp);

    Personnel findByEDB(ExpressionBesoin expressionBesoin);

    List<Personnel> findByEntiteAdministrativeNom(String nom);
    
    List<Personnel> findByCodeChef(String codeChef);

    int save(Personnel personnel);

    int deleteByCode(String code);

    int transferEmp(Personnel personnel,EntiteAdministrative oldEA, EntiteAdministrative newEA);

    int AddChef(Personnel newChef,Personnel oldChef, EntiteAdministrative entiteAdministrative);

}
