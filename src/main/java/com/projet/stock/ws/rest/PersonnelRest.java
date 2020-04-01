/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.Personnel;
import com.projet.stock.service.facade.PersonnelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anoir
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("stock-api/Personnel")
public class PersonnelRest {

    @Autowired
    private PersonnelService personnelService;

    @GetMapping("/")
    public List<Personnel> findAll() {
        return personnelService.findAll();
    }

    @GetMapping("/code/{code}")
    public Personnel findByCode(@PathVariable String code) {
        return personnelService.findByCode(code);
    }

    @GetMapping("/fonction/{fonction}")
    public List<Personnel> findByFonction(@PathVariable String fonction) {
        return personnelService.findByFonction(fonction);
    }

    @PostMapping("/")
    public int save(@RequestBody Personnel personnel) {
        return personnelService.save(personnel);
    }

    @GetMapping("/seniorityScore/{seniorityScore}")
    public Personnel findBySeniorityScore(@PathVariable Double seniorityScore) {
        return personnelService.findBySeniorityScore(seniorityScore);
    }

    @GetMapping("/salary/{salary}")
    public Personnel findBySalary(@PathVariable Double salary) {
        return personnelService.findBySalary(salary);
    }

    @GetMapping("/yearsExp/{yearsExp}")
    public Personnel findByYearsExp(@PathVariable Double yearsExp) {
        return personnelService.findByYearsExp(yearsExp);
    }

    @GetMapping("/nom/{nom}")
    public List<Personnel> findByEntiteAdministrativeNom(@PathVariable String nom) {
        return personnelService.findByEntiteAdministrativeNom(nom);
    }

    @GetMapping("/codeChef/{codeChef}")
    public List<Personnel> findByCodeChef(@PathVariable String codeChef) {
        return personnelService.findByCodeChef(codeChef);
    }

    @PutMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return personnelService.deleteByCode(code);
    }

    @GetMapping("/referenceEDB/{referenceEDB}")
    public Personnel findByEDB(@PathVariable String referenceEDB) {
        return personnelService.findByEDB(referenceEDB);
    }

    @PutMapping("/codeEmp/{codeEmp}/newEAReference/{newEAReference}")
    public int transferEmp(@PathVariable String codeEmp, @PathVariable String newEAReference) {
        return personnelService.transferEmp(codeEmp, newEAReference);
    }

    @GetMapping("/value/{value}")
    public List<Personnel> findBySeniorityScoreGreaterThanEqual(@PathVariable Double value) {
        return personnelService.findBySeniorityScoreGreaterThanEqual(value);
    }

    @PutMapping("/newChefCode/{newChefCode}/oldChefCode/{oldChefCode}")
    public int addChef(@PathVariable String newChefCode,@PathVariable String oldChefCode) {
        return personnelService.addChef(newChefCode, oldChefCode);
    }

    @GetMapping("/topfive")
    public List<Personnel> topFiveCondidatesToBePromoted() {
        return personnelService.topFiveCondidatesToBePromoted();
    }

    @PutMapping("/codeEmpToupdateSeniorityScore/{codeEmp}")
    public int updateSeniorityScore(@PathVariable String codeEmp) {
        return personnelService.updateSeniorityScore(codeEmp);
    }

  

}
