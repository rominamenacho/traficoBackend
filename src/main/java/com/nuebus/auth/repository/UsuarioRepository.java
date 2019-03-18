
package com.nuebus.auth.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nuebus.model.Usuario;

/**
 *
 * @author Valeria
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername( String login );
	
	@Query( "Select u from Usuario u where u.usuarioPk.empresa = :empresa")
    Page<Usuario> findAllByEmpresa( @Param( value="empresa") String empresa, Pageable pageable);
	
	
	@Query( " Select u from Usuario u where u.usuarioPk.empresa = :empresa"
			+ "								and ( CAST( u.usuarioPk.legajo as string ) LIKE  CONCAT('%',:busqueda,'%')	"
			+ "									  or  UPPER( u.nombre ) LIKE CONCAT('%',:busqueda,'%') )")
    Page<Usuario> findAllByEmpresaAndBusqueda( @Param( value="empresa") String empresa, 
    		@Param(value="busqueda") String busqueda,Pageable pageable);
	
	
	@Query( " Select u from Usuario u where ( CAST( u.usuarioPk.legajo as string ) LIKE  CONCAT('%',:busqueda,'%')	"
			+ "									  or  UPPER( u.nombre ) LIKE CONCAT('%',:busqueda,'%') )")
    Page<Usuario> findAllByBusqueda( @Param(value="busqueda") String busqueda,Pageable pageable);
	
}
