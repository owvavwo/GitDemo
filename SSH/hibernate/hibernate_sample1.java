package com.hui.hibernate.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="_Teacher")
public class Teacher {
	private int id;
	private String name;
	private String title;
	private String secret;
	private Date date;
	private Level level;   //ö��
	
	@Id
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Transient             //����������ݿ�
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	@Temporal(TemporalType.DATE)  //���ó�ֻ��ʾ���ڣ�Ĭ��ΪDATETIME��
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Enumerated(EnumType.STRING)   //ö�����͵�ע�⣬EnumType.ORDINAL�����ö�ٶ�Ӧ������±�
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}
