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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
