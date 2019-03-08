package com.nuebus.auth.service.impl;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nuebus.auth.repository.GroupRepository;
import com.nuebus.auth.service.GroupService;
import com.nuebus.dto.GroupDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.model.Group;
import com.nuebus.model.Role;



@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService{
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Override
    public Group getById(long id){
    	
    	Group group = groupRepository.findById(id).orElse(null);
    	if ( group == null ) {
    		throw new ResourceNotFoundException(id,"El grupo no se encuentra"); 
    	}
        return group;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void save( GroupDTO groupDTO ){        
        Group grupo = new Group();
        grupo.setEmpresa( groupDTO.getEmpresa() );
        grupo.setGroupName(groupDTO.getGroupName());
        grupo.setCreated(new java.util.Date());
        grupo.setModified(new java.util.Date());
        groupRepository.save(grupo);
    }
    

    @Override
    @Transactional(readOnly = false)
    public void update(Long id, GroupDTO groupDTO) {
        Group grupo = getById(id);
        grupo.setGroupName( groupDTO.getGroupName() );
        grupo.setModified(new java.util.Date());
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
       Group grupo = getById(id );
       groupRepository.delete(grupo);        
    }

	@Override
	public Page<Group> findAll(Pageable pageable) {
		return groupRepository.findAll( pageable );
	}
	
	@Override
	public Page<Group> findAllByEmpresa(String empresa, Pageable pageable) {
		return groupRepository.findAllByEmpresa(empresa, pageable);
	}

	
	@Override
	@Transactional(readOnly = false)
	public boolean grantOrRevokePermission(long groupId , String permission){
		
		Group grupo= getById( groupId );	 
	 
	    Set<Role> s= grupo.getRoles();
	    // El grupo puede no tener ningun permiso, si o si hay que agregar.
	    if( s!=null && !s.isEmpty() ){
	    	
	    	Role rol = grupo.getRoles()
	    							   .stream()
	    							   .filter( role -> role.getAuthority().equals( permission ) )
	    							   .findFirst()
	    							   .orElse( null );
	    	
	    	if( rol != null ){
	              this.removerPermiso( grupo, rol );
	              return false;
	        }else{
	              this.agregarPermiso( grupo, permission );
	              return true;
	        }    	
	    	
	    }else{
            grupo.getRoles().clear();
            this.agregarPermiso( grupo , permission);
            return true;
	    }
	    
    }
	    
    private void agregarPermiso(Group g,String permission){       
        Role permisoNuevo = new Role();
        permisoNuevo.setGroup(g);
        permisoNuevo.setAuthority(permission);
        g.getRoles().add(permisoNuevo);
        groupRepository.save(g);
    }
    
    private void removerPermiso(Group g, Role role ){
        // Si tiene el permiso, lo tengo que sacar.
        g.getRoles().remove( role );
        groupRepository.save(g);
    }

	@Override
	public Page<Group> findFetchWithRoles(Pageable pageable) { 
		return groupRepository.findFetchWithRoles(pageable);
	}

	

    
}
