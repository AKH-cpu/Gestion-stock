/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.stock.repository;

import com.projet.stock.bean.Personnel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anoir
 */
@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    Personnel findByCode(String code);

    Personnel findBySeniorityScore(Double seniorityScore);

    List<Personnel> findBySeniorityScoreGreaterThanEqual(double value);

    Personnel findBySalary(Double salary);

    Personnel findByYearsExp(Double yearsExp);

    List<Personnel> findByCodeChef(String codeChef);

    List<Personnel> findByEntiteAdministrativeNom(String nom);

    List<Personnel> findByFonction(String fonction);

    int deleteByCode(String code);

    @Query(value= "SELECT * FROM personnel WHERE seniority_score > 100 ORDER BY seniority_score LIMIT 5 ", nativeQuery = true)
    List<Personnel> topFiveCondidatesToBePromoted();

}
