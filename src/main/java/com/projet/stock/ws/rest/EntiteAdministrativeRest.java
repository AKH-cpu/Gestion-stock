/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.EntiteAdministrative;
import com.projet.stock.bean.Magasin;
import com.projet.stock.service.facade.EntiteAdministrativeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
@RequestMapping("gestion-stock-v1/EntiteAdministrative")
public class EntiteAdministrativeRest {
    
    @Autowired
    EntiteAdministrativeService entiteAdministrativeService;
    
    @GetMapping("/reference/{reference}")
    public EntiteAdministrative findByReference(@PathVariable String reference){
        return entiteAdministrativeService.findByReference(reference);
    }
    
    
    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        return entiteAdministrativeService.deleteAll();
    }
    
    @GetMapping("/findAll")
    public List<EntiteAdministrative> findAll(){
        return entiteAdministrativeService.findAll();
    }
    
    @PostMapping("/")
    public int save (@RequestBody EntiteAdministrative entiteAdministrative){
        return entiteAdministrativeService.save(entiteAdministrative);
    }
    
    @DeleteMapping("/delete/{reference}")
    public int deleteByReference(@PathVariable String reference){
        return entiteAdministrativeService.deleteByReference(reference);
    }
    
    @GetMapping("/findMagasinLibre/refEntite/{refEntite}")
    public List<Magasin> findMagasinLibre(@PathVariable String refEntite){
        return entiteAdministrativeService.findMagasinLibre(refEntite);
    }

    @GetMapping("/findMagasinBesoinsDeProduits/refEntite/{refEntite}")
    public List<Magasin> magasinsBesoinsDeProduits(@PathVariable String refEntite){
        return entiteAdministrativeService.magasinsBesoinsDeProduits(refEntite);
    }

    @PutMapping("/AddEmployeToMagasin/code/{code}/refMagasin/{refMagasin}")
    public int AddEmployeToMagasin(@PathVariable String code,@PathVariable String refMagasin){
        return entiteAdministrativeService.AddEmployeToMagasin(code, refMagasin);
    }

    @PutMapping("/RemoveEmployeFromMagasin/code/{code}/refMagasin/{refMagasin}")
    public int RemoveEmployeFromMagasin(@PathVariable String code,@PathVariable String refMagasin){
        return entiteAdministrativeService.RemoveEmployeFromMagasin(code, refMagasin);
    }

    @GetMapping("/ifMagasinFoundedByReference/reference/{reference}")
    public int findMagasinByReference(@PathVariable String reference){
        return entiteAdministrativeService.findMagasinByReference(reference);
    }

    @GetMapping("/isEmployeExiste/codeEmploye/{code}/refMagasin/{refMagasin}")
    public Boolean isEployeExistInMagasin(@PathVariable String code,@PathVariable String refMagasin){
        return entiteAdministrativeService.isEployeExistInMagasin(code, refMagasin);
    }

    @GetMapping("/findEntiteByChef/codeChef/{codeChef}")
    public EntiteAdministrative findByPersonnelCodeChef(@PathVariable String codeChef){
        return entiteAdministrativeService.findByPersonnelCodeChef(codeChef);
    }
    
    
}
