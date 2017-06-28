package com.karthi.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
	@Id
	
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "active")
	private String active;
 
	@Column(name = "created_date")
	private String created_date;

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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", active=" + active + ", created_date=" + created_date + "]";
	}
}
