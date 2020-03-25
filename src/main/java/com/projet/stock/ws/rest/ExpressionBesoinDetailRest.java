/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.ws.rest;

import com.projet.stock.bean.ExpressionBesoinDetail;
import com.projet.stock.service.facade.ExpressionBesoinDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("stock-api/ExpressionBesoinDetail")
public class ExpressionBesoinDetailRest {

    @Autowired
    private ExpressionBesoinDetailService expressionBesoinDetailService;

    @GetMapping("/")
    public List<ExpressionBesoinDetail> findAll() {
        return expressionBesoinDetailService.findAll();
    }

    @GetMapping("/qte/{qte}")
    public List<ExpressionBesoinDetail> findByQte(@PathVariable Double qte) {
        return expressionBesoinDetailService.findByQte(qte);
    }

    @GetMapping("/qteLivre/{qteLivre}")
    public List<ExpressionBesoinDetail> findByQteLivre(@PathVariable Double qteLivre) {
        return expressionBesoinDetailService.findByQteLivre(qteLivre);
    }

    @GetMapping("/onHold")
    public List<ExpressionBesoinDetail> findEDBOnHold() {
        return expressionBesoinDetailService.findEDBOnHold();
    }

    @GetMapping("/reference/{reference}")
    public ExpressionBesoinDetail findByReference(@PathVariable String reference) {
        return expressionBesoinDetailService.findByReference(reference);
    }

    @PostMapping("/expressionBesoinRef/{expressionBesoinRef}/produitRef/{produitRef}")
    public int save(@PathVariable String expressionBesoinRef, @PathVariable String produitRef, @RequestBody List<ExpressionBesoinDetail> expressionBesoinDetail) {
        return expressionBesoinDetailService.save(expressionBesoinRef, produitRef, expressionBesoinDetail);
    }

    @DeleteMapping("/reference/{reference}")
    public int deleteByReference(@PathVariable String reference) {
        return expressionBesoinDetailService.deleteByReference(reference);
    }

    @DeleteMapping("/ExpressionBesoin/reference/{reference}")
    public int deleteByExpressionBesoinReference(@PathVariable String reference) {
        return expressionBesoinDetailService.deleteByExpressionBesoinReference(reference);
    }

}
