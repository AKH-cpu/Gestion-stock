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

    List<Personnel> findBySeniorityScore(Double seniorityScore);

    List<Personnel> findBySeniorityScoreGreaterThanEqual(Double value);

    List<Personnel> findBySalary(Double salary);

    List<Personnel> findByYearsExp(Double yearsExp);

    //EDB = Expression De Besoin
    Personnel findByEDB(String referenceEDB);

    List<Personnel> findByEntiteAdministrativeNom(String nom);

    List<Personnel> findByCodeChef(String codeChef);

    int save(Personnel personnel);

    int deleteByCode(String code);

    int transferEmp(String codeEmp, String newEAReference);

    int addChef( String entiteAdminNom, String newChefCode, String oldChefCode);

    List<Personnel> topFiveCondidatesToBePromoted();

    int updateSeniorityScore(String codeEmp);

    // true if present, false if absent
    int dailyPointsUpdate(String codeEmp);
    
//    int countNumberPerState(String codeEmp, String state);
    
}
