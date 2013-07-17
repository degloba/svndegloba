package Application.caracteristiques;

import java.util.List;

import entitats.Caracteristiques;
import entitats.Caractinmobles;
import entitats.Tipus;
import Application.IInsacosaClasseApp;

public interface ICaracteristiques extends IInsacosaClasseApp {

	List<Caracteristiques> caractTipus(Tipus tipus);
	List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio, Boolean inclouCaractComu);
	Caracteristiques caractPerKey(String idCaract);
	List<Caracteristiques> allCaract();
	Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble);
}
