package com.jsu.lei.comment;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

public class BaseDao <T> implements IBaseDao<T>{
	protected Logger logger=Logger.getLogger(this.getClass());
	@PersistenceContext protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	protected Class<T> clazz=(Class<T>)((ParameterizedType) this.getClass() .getGenericSuperclass()).getActualTypeArguments()[0];
	
	public void save(T t){
		em.persist(t);
	}
	
	public void update(T t){
		em.flush();
		em.merge(t);
	}
	
	public T findById(Integer id){
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(){
		return em.createQuery(" from "+clazz.getSimpleName()).getResultList();
	}

	@Override
	public void delete(Integer id) {
		T t=findById(id);
		em.remove(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(String property, Object val) {
		String table=clazz.getSimpleName();
		return em.createQuery(" from "+table+" where "+property +" = :property")
				.setParameter("property", val)
				.getResultList();
	}
	 
}
