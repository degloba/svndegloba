package infrastructure.seedwork;

import java.io.Serializable;
import java.util.List;

import domain.seedwork.*;
import domain.support.BaseAggregateRoot;
import domain.support.CriteriaQuery;
import domain.support.ExampleSettings;
import domain.support.JpqlQuery;
import domain.support.NamedParameters;
import domain.support.NamedQuery;
import domain.support.SqlQuery;

public class Repository<E extends BaseAggregateRoot> implements IRepository<BaseAggregateRoot> {

	@Override
	public void add(BaseAggregateRoot entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseAggregateRoot save(BaseAggregateRoot entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(BaseAggregateRoot entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BaseAggregateRoot> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseAggregateRoot update(BaseAggregateRoot entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Class<?> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <E> E get(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E load(Class<E> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getUnmodified(Class<E> clazz, E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseAggregateRoot getByBusinessKeys(Class<BaseAggregateRoot> clazz,
			NamedParameters keyValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseAggregateRoot> findAll(Class<BaseAggregateRoot> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> CriteriaQuery createCriteriaQuery(Class<E> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> find(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(CriteriaQuery criteriaQuery) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public <E> E getSingleResult(JpqlQuery jpqlQuery) {
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
	public <E> E getSingleResult(NamedQuery namedQuery) {
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
	public <E> List<E> find(SqlQuery sqlQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getSingleResult(SqlQuery sqlQuery) {
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
	public <E> List<E> findByProperty(Class<E> clazz, String propertyName,
			Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> findByProperties(Class<E> clazz,
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
	public void refresh(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
}
