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
    public List<Personnel> findAll() {
        return personnelRepository.findAll();
    }

    @Override
    public List<Personnel> findByFonction(String fonction) {
        return personnelRepository.findByFonction(fonction);
    }

    @Override
    public Personnel findByCode(String code) {
        return personnelRepository.findByCode(code);
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
    public Personnel findBySeniorityScoreGreaterThanEqual(Double value) {
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
                personnelRepository.save(foundedPersonnel);
            } else {
                personnelRepository.save(foundedPersonnel);
            }
            return 1;
        }
    }

    @Override
    public Personnel findByEDB(ExpressionBesoin expressionBesoin) {
        ExpressionBesoin foundedEDB = expressionBesoinService.findByReference(expressionBesoin.getReference());
        if (foundedEDB == null) {
            return null;
        } else {
            return foundedEDB.getPersonnel();
        }

    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        return personnelRepository.deleteByCode(code);
    }

    @Override
    public int transferEmp(Personnel personnel, EntiteAdministrative oldEA, EntiteAdministrative newEA) {
        Personnel foundedpersonnel = personnelRepository.findByCode(personnel.getCode());
        EntiteAdministrative foldEA = entiteAdministrativeService.findByNom(oldEA.getNom());
        EntiteAdministrative fnewEA = entiteAdministrativeService.findByNom(newEA.getNom());
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
    public int AddChef(Personnel newChef, Personnel oldChef, EntiteAdministrative entiteAdministrative) {
        Personnel foundednewChef = personnelRepository.findByCode(newChef.getCode());
        Personnel foundedoldChef = personnelRepository.findByCode(oldChef.getCode());
        EntiteAdministrative fEntiteAdministrative = entiteAdministrativeService.findByNom(entiteAdministrative.getNom());
        if (foundednewChef == null) {
            return -1;
        } else if (foundedoldChef == null) {
            return -2;
        } else if (fEntiteAdministrative == null) {
            return -3;
        } else {
            fEntiteAdministrative.setChef(newChef);
            for (Personnel employees : personnelRepository.findByCodeChef(oldChef.getCodeChef())) {
                employees.setCode(newChef.getCode());
            }
            personnelRepository.save(newChef);
            return 1;
        }
    }

}
