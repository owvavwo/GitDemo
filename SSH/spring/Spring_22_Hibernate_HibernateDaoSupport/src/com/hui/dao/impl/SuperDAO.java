package com.hui.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

@Component 
public class SuperDAO extends HibernateDaoSupport { 
	
	/*@Resource(name="hibernateTemplate")
	public void setSuperHiberanteTemplate(HibernateTemplate hibernateTemplate) {
		 super.setHibernateTemplate(hibernateTemplate);
	}*/
	@Resource(name="sessionFactory")                 //����д����ֱ����sessionFactory����ȥHibernateTemplater��ʹ��
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
