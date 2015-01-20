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
	public <T extends Entity> void add(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Entity> T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> void remove(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Entity> List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> boolean exists(Class<T> clazz, Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends Entity> T get(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T load(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T getUnmodified(Class<T> clazz, T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> T getByBusinessKeys(Class<T> clazz,
			NamedParameters keyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> CriteriaQuery createCriteriaQuery(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getSingleResult(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JpqlQuery createJpqlQuery(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(JpqlQuery jpqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getSingleResult(JpqlQuery jpqlQuery) {
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
	public <T> List<T> find(NamedQuery namedQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getSingleResult(NamedQuery namedQuery) {
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
	public <T extends Entity> List<T> find(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getSingleResult(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends Entity, E2 extends T> List<T> findByExample(E2 example,
			ExampleSettings<T> settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Entity> List<T> findByProperties(Class<T> clazz,
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
	public <T extends Entity> void refresh(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

   	
	
}
