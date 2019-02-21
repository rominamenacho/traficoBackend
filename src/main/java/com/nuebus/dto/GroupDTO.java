package com.nuebus.dto;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Valeria
 */
public class GroupDTO {
   
    private Long id; 
    private String groupName;    
    private Date created;   
    private Date modified;   
    private Set<RoleDTO> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	} 
    
    
}
