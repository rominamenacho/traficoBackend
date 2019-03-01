package com.nuebus.auth.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nuebus.dto.GroupDTO;
import com.nuebus.model.Group;

public interface GroupService {
    
	public Group getById(long id);
	public void save(GroupDTO groupDTO);
    public void update( Long id,  GroupDTO groupDTO );
    public void delete( Long id);
    public Page<Group> findAll(   Pageable pageable );    
    public boolean grantOrRevokePermission( long groupId, String permisoBuscar );
    
    public Page<Group> findFetchWithRoles( Pageable pageable );
    
          
}
