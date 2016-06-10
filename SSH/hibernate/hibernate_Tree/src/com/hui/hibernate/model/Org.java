package com.hui.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Org {
	private int id;
	private String name;
	private Set<Org> children = new HashSet<Org>();
	private Org parent;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=CascadeType.ALL ,mappedBy="parent"    //cascade 设置all后，存储时只存根结点就能存下整棵树
	,fetch=FetchType.EAGER                                      //设置后，取根结点的同时可取出整棵树，适用于树小的情况
	) 
	public Set<Org> getChildren() {
		return children;
	}
	public void setChildren(Set<Org> children) {
		this.children = children;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Org getParent() {
		return parent;
	}
	public void setParent(Org parent) {
		this.parent = parent;
	}
}
