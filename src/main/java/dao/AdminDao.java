package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Admin;

@Stateless
public class AdminDao extends AbstractDao<Admin, Integer>{

	@PersistenceContext
	EntityManager em;
	
	
	public AdminDao() {
		super(Admin.class);
	}

	@Override
	public EntityManager em() {
		return em;
	}
	


}
