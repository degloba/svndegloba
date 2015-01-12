package infrastructure.seedwork;

import java.io.Serializable;
import java.util.List;

import domain.seedwork.*;
import domain.support.CriteriaQuery;
import domain.support.ExampleSettings;
import domain.support.JpqlQuery;
import domain.support.NamedParameters;
import domain.support.NamedQuery;
import domain.support.SqlQuery;

public class Repository implements IRepository {

	@Override
	public <E extends Entity> void add(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E extends Entity> E save(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> void remove(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E extends Entity> List<E> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E update(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> boolean exists(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <E extends Entity> E get(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E load(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getUnmodified(Class<E> clazz, E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getByBusinessKeys(Class<E> clazz,
			NamedParameters keyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> List<E> findAll(Class<E> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> CriteriaQuery<E> createCriteriaQuery(Class<E> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public <E> List<E> find(CriteriaQuery<E> criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getSingleResult(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public JpqlQuery createJpqlQuery(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getSingleResult(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NamedQuery createNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getSingleResult(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SqlQuery createSqlQuery(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> List<E> find(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getSingleResult(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <E extends Entity, E2 extends E> List<E> findByExample(E2 example,
			ExampleSettings<E> settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> List<E> findByProperty(Class<E> clazz,
			String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> List<E> findByProperties(Class<E> clazz,
			NamedParameters properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryStringOfNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E extends Entity> void refresh(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E> List<E> find(CriteriaQuery<?> criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E extends Entity> E getSingleResult(CriteriaQuery<?> criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	    	
	
}
