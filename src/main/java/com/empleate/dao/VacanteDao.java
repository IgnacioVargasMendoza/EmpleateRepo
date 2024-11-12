
package com.empleate.dao;

import com.empleate.domain.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VacanteDao extends JpaRepository<Vacante, Long>{
    
}
