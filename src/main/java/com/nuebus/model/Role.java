package com.nuebus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

//@Table(name = "authorities", uniqueConstraints= {@UniqueConstraint(columnNames= {"user_id", "authority"})})

@Entity
@Table(name = "groups_authorities")
public class Role implements Serializable {
	
	@GenericGenerator(
            name = "roleSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "role_seq")
                ,
                    @Parameter(name = "initial_value", value = "1")
                ,
                    @Parameter(name = "increment_size", value = "1")
            }
    )

	@Id
	@GeneratedValue(generator = "roleSequenceGenerator")
	private Long id;
	
	@Size( max = 60 )
	private String authority;
	
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", authority=" + authority + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
