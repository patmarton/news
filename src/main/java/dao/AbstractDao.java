package dao;

import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDao<T, I> {

	private Class<T> entityClass;
	
	public AbstractDao(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}//comment

	public abstract EntityManager em();
	
	@Lock(LockType.WRITE)
	public void create(T entity){
		System.out.println("create being called from abstract dao...");
		System.out.println(entity.toString());
		em().persist(entity);
	}
	
	@Lock(LockType.WRITE)
	public T update(T entity){
		return em().merge(entity);
	}
	
	@Lock(LockType.READ)
	public T findById(I id){
		return em().find(entityClass, id);
	}
	
	@Lock(LockType.WRITE)
	public void delete(T entity){
		em().remove(em().merge(entity));
	}
	
	@Lock(LockType.WRITE)
	public void deleteById(I id){
		T entity = findById(id);
		if(entity != null)
			em().remove(entity);
	}
	
	@Lock(LockType.READ)
	public List<T> findAll(){
		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		return em().createQuery(cq).getResultList();
	}
}