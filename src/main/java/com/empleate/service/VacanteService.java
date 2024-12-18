package com.empleate.service;

import com.empleate.domain.Vacante;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ignac
 */
public interface VacanteService {

    List<Vacante> listarVacantes();

    Vacante encontrarVacante(Long idVacante);

    void guardar(Vacante vacante);

    void eliminar(Long idVacante);
}
