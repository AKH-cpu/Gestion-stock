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
    public List<Personnel> findBySeniorityScore(Double seniorityScore) {
        return personnelRepository.findBySeniorityScore(seniorityScore);
    }

    @Override
    public List<Personnel> findBySeniorityScoreGreaterThanEqual(Double value) {
        return personnelRepository.findBySeniorityScoreGreaterThanEqual(value);
    }

    @Override
    public List<Personnel> findBySalary(Double salary) {
        return personnelRepository.findBySalary(salary);
    }

    @Override
    public List<Personnel> findByYearsExp(Double yearsExp) {
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
        } else {
            if (personnel.getFonction().equalsIgnoreCase("chef") && personnel.getCodeChef() == null) {
                //si il est un chef, il faut avoir un seul chef dans chaque entite.
                if (fEntiteAdministrative.getChef() != null) {
                    return -3;
                } else {
                    personnel.setSeniorityScore((double) 50);
                    personnel.setEntiteAdministrative(fEntiteAdministrative);
                    personnel.setEntiteAdministrative(fEntiteAdministrative);
                    fEntiteAdministrative.setChef(personnel);
                    personnelRepository.save(personnel);

                }
            } else if (!personnel.getFonction().equalsIgnoreCase("chef") && personnel.getCodeChef() == null) {
                return -4;
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
        Personnel foundedpersonnel = personnelRepository.findByCode(code);
        if (foundedpersonnel == null) {
            return -1;
        } else {
            if (foundedpersonnel.getFonction().equalsIgnoreCase("chef")) {
                EntiteAdministrative fEntiteAdministrative = entiteAdministrativeService
                        .findByNom(foundedpersonnel.getEntiteAdministrative().getNom());
                for (Personnel employe : personnelRepository.findByCodeChef(code)) {
                    employe.setCodeChef(null);
                }
                fEntiteAdministrative.setChef(null);
                personnelRepository.deleteByCode(code);
                return 1;
            } else {
                personnelRepository.deleteByCode(code);
                return 2;
            }
        }
    }

    @Override
    public int transferEmp(String codeEmp, String newEAReference) {
        Personnel foundedpersonnel = personnelRepository.findByCode(codeEmp);
        EntiteAdministrative fnewEA = entiteAdministrativeService.findByReference(newEAReference);
        if (foundedpersonnel == null) {
            return -1;
        } else if (fnewEA == null) {
            return -2;
        } else {
            EntiteAdministrative foldEA = entiteAdministrativeService
                    .findByReference(foundedpersonnel.getEntiteAdministrative().getReference());
            if (foldEA == null) {
                return -3;
            } else {
                foundedpersonnel.setEntiteAdministrative(fnewEA);
                personnelRepository.save(foundedpersonnel);
                return 1;
            }
        }
    }

    @Override
    public int addChef(String entiteAdminNom, String newChefCode, String oldChefCode) {
        Personnel foundednewChef = personnelRepository.findByCode(newChefCode);
        if (foundednewChef == null) {
            return -1;
        } else {
            Personnel foundedoldChef = personnelRepository.findByCode(oldChefCode);
            EntiteAdministrative fEntiteAdministrative = entiteAdministrativeService.findByNom(entiteAdminNom);
            if (fEntiteAdministrative == null) {
                return -3;
            } else if (foundednewChef.getEntiteAdministrative() != fEntiteAdministrative) {
                return -4;
            } else if (foundedoldChef == null) {
                personnelRepository.findByEntiteAdministrativeNom(entiteAdminNom).forEach((employee) -> {
                    employee.setCodeChef(newChefCode);
                });
                foundednewChef.setFonction("Chef");
                fEntiteAdministrative.setChef(foundednewChef);
                personnelRepository.save(foundednewChef);
                return 1;
            } else {
                fEntiteAdministrative.setChef(foundednewChef);
                personnelRepository.findByCodeChef(foundedoldChef.getCodeChef()).forEach((employees) -> {
                    employees.setCode(foundednewChef.getCode());
                });
                foundednewChef.setFonction("Chef");
                fEntiteAdministrative.setChef(foundednewChef);
                personnelRepository.save(foundednewChef);
                return 2;
            }
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
        } else {
            return 1;
        }
    }

}
