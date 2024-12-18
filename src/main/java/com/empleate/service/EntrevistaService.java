
package com.empleate.service;

import com.empleate.domain.Entrevista;
import java.util.List;

/**
 *
 * @author Ignac
 */
public interface EntrevistaService {
    List<Entrevista> listarEntrevistas();

    Entrevista encontrarEntrevista(Long idEntrevista);

    void guardar(Entrevista entrevista);

    void eliminar(Long idEntrevista);;
}
