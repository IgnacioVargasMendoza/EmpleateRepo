
package com.empleate.service;

import com.empleate.domain.FeriaEmpleo;
import java.util.List;

/**
 *
 * @author Ignac
 */
public interface FeriaEmpleoService {

    List<FeriaEmpleo> listarFerias();

    FeriaEmpleo encontrarFeria(Long idFeria);

    void guardar(FeriaEmpleo feriaEmpleo);

    void eliminar(Long idFeria);
}
