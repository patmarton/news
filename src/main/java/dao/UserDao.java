package dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.User;

@Singleton
public class UserDao extends AbstractDao<User, Integer>{

	@PersistenceContext
	EntityManager em;
	
	
	public UserDao() {
		super(User.class);
	}

	@Override
	public EntityManager em() {
		return em;
	}
	


}
