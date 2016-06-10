package com.hui.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_group")
public class Group {
	private int id;
	private String name;
	private Set<User> set = new HashSet<User>();//�����ظ���ֻ��ʹ��Set
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
	@OneToMany
	@JoinColumn(name="groupId") //���������м�����Ҷ��һ��һ�Զ�����ݿ�ģ����һ���ģ�ֻ�ж���ģ���ǲ�һ ����
	public Set<User> getSet() {
		return set;
	}
	public void setSet(Set<User> set) {
		this.set = set;
	}
	
}
