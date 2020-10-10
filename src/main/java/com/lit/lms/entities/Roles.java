package com.lit.lms.entities;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "mentormeroles")
public class Roles implements Serializable, GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String getAuthority() {
		return this.name;
	}
    
    

 
}
