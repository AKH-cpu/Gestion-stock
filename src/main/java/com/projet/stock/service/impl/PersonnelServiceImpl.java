/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.service.impl;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.Personnel;
import com.projet.stock.repository.PersonnelRepository;
import com.projet.stock.service.facade.EntiteAdministrativeService;
import com.projet.stock.service.facade.ExpressionBesoinService;
import com.projet.stock.service.facade.PersonnelService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author anoir
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private ExpressionBesoinService expressionBesoinService;

    @Autowired
    private EntiteAdministrativeService entiteAdministrativeService;

    @Override
    public List<Personnel> findByFonction(String fonction) {
        return personnelRepository.findByFonction(fonction);
    }

    @Override
    public Personnel findByCode(String code) {
        return personnelRepository.findByCode(code);
    }

    @Override
    public List<Personnel> findAll() {
        return personnelRepository.findAll();
    }

    @Override
    public List<Personnel> findByCodeChef(String codeChef) {
        return personnelRepository.findByCodeChef(codeChef);
    }

    @Override
    public List<Personnel> findByEntiteAdministrativeNom(String nom) {
        return personnelRepository.findByEntiteAdministrativeNom(nom);
    }

    @Override
    public Personnel findBySeniorityScore(Double seniorityScore) {
        return personnelRepository.findBySeniorityScore(seniorityScore);
    }

    @Override
    public List<Personnel> findBySeniorityScoreGreaterThanEqual(Double value) {
        return personnelRepository.findBySeniorityScoreGreaterThanEqual(value);
    }

    @Override
    public Personnel findBySalary(Double salary) {
        return personnelRepository.findBySalary(salary);
    }

    @Override
    public Personnel findByYearsExp(Double yearsExp) {
        return personnelRepository.findByYearsExp(yearsExp);
    }

    @Override
    public int save(Personnel personnel) {
        Personnel foundedPersonnel = personnelRepository.findByCode(personnel.getCode());
        EntiteAdministrative fEntiteAdministrative = entiteAdministrativeService.findByNom(personnel.getEntiteAdministrative().getNom());
        if (foundedPersonnel != null) {
            return -1;
        } else if (fEntiteAdministrative == null) {
            return -2;
        } //si il est un chef, il faut avoir un seul chef dans chaque entite.
        else if (personnel.getFonction().equals("chef") && fEntiteAdministrative.getChef() != null) {
            return -3;
        } else {
            if (personnel.getFonction().equalsIgnoreCase("chef")) {
                personnel.setCodeChef(null);
                personnel.setSeniorityScore((double) 50);
                //hadi zdtha (bartaouch) 7int makat2assosiach entite l had chef f la base de donnee
                personnel.setEntiteAdministrative(fEntiteAdministrative);
                fEntiteAdministrative.setChef(personnel);
                personnel.setEntiteAdministrative(fEntiteAdministrative);
                personnelRepository.save(personnel);
            } else {
                personnel.setSeniorityScore((double) 50);
                personnel.setEntiteAdministrative(fEntiteAdministrative);
                personnelRepository.save(personnel);
            }
            return 1;
        }
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        return personnelRepository.deleteByCode(code);
    }

    @Override
    public int transferEmp(String codeEmp, String newEAReference) {
        Personnel foundedpersonnel = personnelRepository.findByCode(codeEmp);
        EntiteAdministrative foldEA = entiteAdministrativeService.findByReference(foundedpersonnel.getEntiteAdministrative().getReference());
        EntiteAdministrative fnewEA = entiteAdministrativeService.findByReference(newEAReference);
        if (foundedpersonnel == null) {
            return -1;
        } else if (foldEA == null) {
            return -2;
        } else if (fnewEA == null) {
            return -3;
        } else {
            foundedpersonnel.setEntiteAdministrative(fnewEA);
            personnelRepository.save(foundedpersonnel);
            return 1;
        }
    }

    @Override
    public int addChef(String newChefCode, String oldChefCode) {
        Personnel foundednewChef = personnelRepository.findByCode(newChefCode);
        Personnel foundedoldChef = personnelRepository.findByCode(oldChefCode);
        EntiteAdministrative fEntiteAdministrative = entiteAdministrativeService.findByNom(foundedoldChef.getEntiteAdministrative().getNom());
        if (foundednewChef == null) {
            return -1;
        } else if (foundedoldChef == null) {
            return -2;
        } else if (fEntiteAdministrative == null) {
            return -3;
        } else {
            fEntiteAdministrative.setChef(foundednewChef);
            personnelRepository.findByCodeChef(foundedoldChef.getCodeChef()).forEach((employees) -> {
                employees.setCode(foundednewChef.getCode());
            });
            personnelRepository.save(foundednewChef);
            return 1;
        }
    }

    //EDB = Expression De Besoin
    @Override
    public Personnel findByEDB(String referenceEDB) {
        ExpressionBesoin foundedEDB = expressionBesoinService.findByReference(referenceEDB);
        if (foundedEDB == null) {
            return null;
        } else {
            return foundedEDB.getChef();
        }
    }

    @Override
    public List<Personnel> topFiveCondidatesToBePromoted() {
        return personnelRepository.topFiveCondidatesToBePromoted();
    }

    @Override
    public int updateSeniorityScore(String codeEmp) {
        Personnel foundedEmp = personnelRepository.findByCode(codeEmp);
        double newSeniorityScore = 0;
        if (foundedEmp == null) {
            return -1;
        } else {
            newSeniorityScore = foundedEmp.getPoints() * 10 + foundedEmp.getYearsExp();
            foundedEmp.setSeniorityScore(newSeniorityScore);
            personnelRepository.save(foundedEmp);
            return 1;
        }
    }

//    @Override
//    public int countNumberPerState(String codeEmp, String state) {
//        Personnel foundedEmp = personnelRepository.findByCode(codeEmp);
//        if(foundedEmp == null)
//            return -1;
//        else if(!state.equalsIgnoreCase("present") || !state.equalsIgnoreCase("absent"))
//            return -2 ;
//        else {
//           
//        }
//  }
    @Override
    public int dailyPointsUpdate(String codeEmp) {
        Personnel foundedEmp = personnelRepository.findByCode(codeEmp);
        if (foundedEmp == null) {
            return -1;
        } else  {
            return 1;
        }
    }

}
