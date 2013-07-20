package domini;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;

import entitats.Usuaris;



public class InsacosaClasseService implements IInsacosaClasseService {
	
	public IEntityService CreateService()
	{
		return null;
		
	}

	public <E> void Add(E entitat) {
		// TODO Auto-generated method stub
		
	}

	public <E> void Update(E entitat) {
		// TODO Auto-generated method stub
		
	}

	public <E> void Delete(E entitat) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see domini.IInsacosaClasseService#usuariValid(entitats.Usuaris)
	 */
	public  Entity usuariValid(Usuaris usuari) {
		
		try {    
			
			// Get the Datastore Service
			/*DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Filter nomFilter =  new Query.FilterPredicate("nomusuari", Query.FilterOperator.EQUAL, usuari.getNomusuari() );
			Query q2 = new Query("Usuaris").setFilter(nomFilter);
			PreparedQuery pq = datastore.prepare(q2);
			Entity usEntity = pq.asSingleEntity();
						
			Query query = new Query("Usuaris"); 
			query.addFilter("nom", FilterOperator.EQUAL, usuari.getNomusuari()); 
			usEntity = Util.getDatastoreServiceInstance().prepare(query).asSingleEntity(); 

			// **************************************************
			
			CriteriaQuery<Usuaris> crit = createSelectCriteriaQuery(new ArrayList<String>(){{add("nom");}}, new ArrayList<Object>(){{add("peresan");}}, null, null);
							
			TypedQuery<Usuaris> tq = entityManager.createQuery(crit);
			List<Usuaris> results = tq.getResultList();
			
			System.out.println("0 - " + results.get(0).getNom() + " " + results.get(0).getCognoms());
			
			// **************************************************
			

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();  // comu
			
			crit = cb.createQuery(Usuaris.class);
			Root<Usuaris> root = crit.from(Usuaris.class);  // FROM
			crit.select(root);
						
			tq = entityManager.createQuery(crit);
			Usuaris results3 = tq.getSingleResult();
			
			System.out.println("1 - " + results3.getNom() + " " + results3.getCognoms());
			
			// **************************************************			
			
			CriteriaQuery<Usuaris> cq = cb.createQuery(Usuaris.class);
			Root<Usuaris> r = cq.from(Usuaris.class);			
						
			Selection<Usuaris> s = cq.getSelection();
			Path<String> p = r.get("nom");
						
			Predicate predicate = cb.equal(p,  usuari.getNomusuari());  // WHERE
			cq.where(predicate);

			tq = entityManager.createQuery(cq);
			results3 = tq.getSingleResult();
			
			System.out.println("2 - " + results3.getNom() + " " + results3.getCognoms());
			
			// **************************************************	
			
			p = root.get("nom");
			Predicate pr = cb.equal(p, "peresan");
			crit.where(pr);
			
			tq = entityManager.createQuery(crit);
			Usuaris u= tq.getSingleResult();
			
			// **************************************************	
			
			javax.persistence.Query q = entityManager.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
			q.setParameter("nomUsuari", usuari.getNomusuari());
			
			return (Usuaris)q.getSingleResult();*/
			    
		} catch (NoResultException e) {
			return null;
		}
				
		return null;
	}

}
