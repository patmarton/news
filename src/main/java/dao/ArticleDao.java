package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import entity.Article;

@Stateless
public class ArticleDao extends AbstractDao<Article, Integer>{

	@PersistenceContext
	EntityManager em;
	
	
	public ArticleDao() {
		super(Article.class);
	}

	@Override
	public EntityManager em() {
		return em;
	}
	
	public List<Article> findAllMain(){
		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<Article> cq = cb.createQuery(Article.class);
		Root<Article> root = cq.from(Article.class);
		ParameterExpression<String> p = cb.parameter(String.class, "true");
		cq.where( cb.equal(root.get("main_article"), p));
		return em().createQuery(cq).getResultList();
	}


}
