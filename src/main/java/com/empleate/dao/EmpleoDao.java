/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.empleate.dao;

import com.empleate.domain.Empleo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ignac
 */
public interface EmpleoDao extends JpaRepository<Empleo, Long>{
    
}
