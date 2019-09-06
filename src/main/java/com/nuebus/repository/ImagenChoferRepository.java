package com.nuebus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nuebus.model.ImagenChofer;



public interface ImagenChoferRepository extends JpaRepository< ImagenChofer, Long> {
	
	ImagenChofer findByIdAndEmpresa( Long id, String empresa );

}
