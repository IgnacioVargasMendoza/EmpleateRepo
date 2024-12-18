/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.empleate.service;

import com.empleate.domain.Empleo;
import java.util.List;

/**
 *
 * @author Ignac
 */
public interface EmpleoService {

    List<Empleo> listarEmpleos();

    void guardar(Empleo empleo);

}
