package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Editor;

@Stateless
public class EditorDao extends AbstractDao<Editor, Integer>{

	@PersistenceContext
	EntityManager em;
	
	
	public EditorDao() {
		super(Editor.class);
	}

	@Override
	public EntityManager em() {
		return em;
	}
	


}
