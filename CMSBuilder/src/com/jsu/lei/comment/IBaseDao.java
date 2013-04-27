package com.jsu.lei.comment;

import java.util.List;

public interface IBaseDao<T>{
	public void save(T t);
	public void update(T t);
	public T findById(Integer id);
	public List<T> listAll();
	public void delete(Integer id);
	public List<T> findByProperty(String property,Object val);
}
