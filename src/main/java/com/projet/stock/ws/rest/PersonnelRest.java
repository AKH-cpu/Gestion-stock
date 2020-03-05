/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.ExpressionBesoin;
import com.projet.stock.bean.Personnel;
import com.projet.stock.service.facade.PersonnelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/personnel/{personnel}")
    public int save(@RequestBody Personnel personnel) {
        return personnelService.save(personnel);
    }

    @GetMapping("/seniorityScore/{seniorityScore}")
    public Personnel findBySeniorityScore(@PathVariable Double seniorityScore) {
        return personnelService.findBySeniorityScore(seniorityScore);
    }

    @GetMapping("/value/{value}")
    public Personnel findBySeniorityScoreGreaterThanEqual(@PathVariable Double value) {
        return personnelService.findBySeniorityScoreGreaterThanEqual(value);
    }

    @GetMapping("/salary/{salary}")
    public Personnel findBySalary(@PathVariable Double salary) {
        return personnelService.findBySalary(salary);
    }

    @GetMapping("/yearsExp/{yearsExp}")
    public Personnel findByYearsExp(@PathVariable Double yearsExp) {
        return personnelService.findByYearsExp(yearsExp);
    }

    @GetMapping("/expressionBesoin/{expressionBesoin}")
    public Personnel findByEDB(@PathVariable ExpressionBesoin expressionBesoin) {
        return personnelService.findByEDB(expressionBesoin);
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

    @PutMapping("/personnel/{personnel}/oldEA/{oldEA}/newEA/{newEA}")
    public int transferEmp(@PathVariable Personnel personnel,@PathVariable EntiteAdministrative oldEA,@PathVariable EntiteAdministrative newEA) {
        return personnelService.transferEmp(personnel, oldEA, newEA);
    }

    @PutMapping("/newChef/{newChef}/oldChef/{oldChef}/entiteAdministrative/{entiteAdministrative}")
    public int AddChef(@PathVariable Personnel newChef,@PathVariable Personnel oldChef,@PathVariable EntiteAdministrative entiteAdministrative) {
        return personnelService.AddChef(newChef, oldChef, entiteAdministrative);
    }
    
    
}
