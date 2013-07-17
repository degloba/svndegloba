package Application.caracteristiques;

import java.util.List;

import Application.InsacosaClasseApp;

import domini.IInsacosaClasseService;
import entitats.Caracteristiques;
import entitats.Caractinmobles;
import entitats.Tipus;

public class Caracteristiques_app extends InsacosaClasseApp
	implements ICaracteristiques
	 {

	public Caracteristiques_app(IInsacosaClasseService ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	public List<Caracteristiques> caractTipus(Tipus tipus) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio,
			Boolean inclouCaractComu) {
		// TODO Auto-generated method stub
		return null;
	}

	public Caracteristiques caractPerKey(String idCaract) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Caracteristiques> allCaract() {
		// TODO Auto-generated method stub
		return null;
	}

	public Caracteristiques caracteristicaCaractInmoble(
			Caractinmobles caractinmoble) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
