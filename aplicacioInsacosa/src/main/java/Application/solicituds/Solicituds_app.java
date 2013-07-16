package Application.solicituds;

import com.google.appengine.api.datastore.Entity;
import Application.InsacosaClasseApp;

import domini.IEntityService;
import domini.IInsacosaClasseService;
import domini.IRepository;

import entitats.Solicituds;

public class Solicituds_app extends InsacosaClasseApp
	implements ISolicituds {

	public Solicituds_app(IInsacosaClasseService ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}
	
	public Solicituds getSolicitudById()
	{
		IEntityService<Entity> d = ds.CreateService();
		//IRepository r = d._repositori;
		//r.Create(t);
		return null;
		
	}
	

}
