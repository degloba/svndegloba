package com.degloba.utils;

import com.degloba.interfaces.Inmoble_Impl;
import com.degloba.interfaces.Usuari_Impl;

import java.util.Random;

import com.degloba.HBM.Caracteristiques;
import com.degloba.HBM.Inmobles;
import com.degloba.HBM.ValuesCaracteristiques;
import com.degloba.HBM.ValuesCaracteristiquesId;

public class CarregaMassiva {

	public void afegirMoltsInmobles()
	{
		Inmoble_Impl r = new Inmoble_Impl();
		Usuari_Impl r2 = new Usuari_Impl();
		
		String[] usuaris = {"PERE","ENRIC","JOAN", "ROSA","CARME","LLUIS","POL","MARIA", "ANNA", "ROBERT","MARCEL","JOANA","MIQUEL","SANTI",
							"RICARD","ESTER","YOEL","LIA","LAURA","LAIA","EVA","JORDI","ISABEL","DOLORS","ROSER","XAVIER","DAVID","BIEL",
							"MARGARITA","CARLES","VALENTI","SILVIA","SONIA","JUDIT","SARA","CARLA","MARC","CARLOTA"}; 
		
		// afegim usuaris
	/*	for (int u=1;u<usuaris.length;u++)
		{
			Usuaris us = new Usuaris();
			us.setNomusuari(usuaris[u]);
			r2.afegirUsuari(us);
		}
		*/
		
		
		// carreguem les provincies
		/*Provincies prov = new Provincies();
		List<Provincies> provList = new ArrayList<Provincies>();
		Iterator<Objecte> iter = prov.llistaObjectes(Provincies.class, "name", "").iterator();
		while (iter.hasNext())
			{
				Provincies provincia = (Provincies)(iter.next());
				provList.add(provincia);
			}
		
		// carreguem les ciutats
		Ciutats ciut = new Ciutats();
		List<Ciutats> ciutList = new ArrayList<Ciutats>();
		iter = ciut.llistaObjectes(Ciutats.class, "name", "").iterator();
		while (iter.hasNext())
			{
				Ciutats ciutat = (Ciutats)(iter.next());
				ciutList.add(ciutat);
			}

		// carreguem el tipus
		Tipus tipus = new Tipus();
		List<Tipus> tipusList = new ArrayList<Tipus>();
		iter = tipus.llistaObjectes(Tipus.class, "nom", "").iterator();
		while (iter.hasNext())
			{
				tipus = (Tipus)(iter.next());
				tipusList.add(tipus);
			}
		*/
		
		// afegim inmobles
	/*	for (int i=1; i<3000 ; i++)
			{
				
				// calculem usuari aleatori (nomUsuari)
				Random rndUs = new Random();
				
				int numAleat = rndUs.nextInt(usuaris.length);
				if (numAleat == 0) numAleat++;
				System.out.println("Aleatori Usuari : " + numAleat);
				Usuaris usAleat = r2.cercarUsuari(usuaris[numAleat]);
				
				Inmobles nou = new Inmobles();
				nou.setId(i);
				nou.setAdreca(usAleat.getNomusuari() + i + "addr");
				nou.setNom(usAleat.getNomusuari() + i + "nom");
				
				Usuaris usuari = new Usuaris();
				usuari.setNomusuari(usAleat.getNomusuari());
				nou.setUsuaris(usuari);
				
				
				// calculem aleatori ciutat/provincia
				Random rndCiut = new Random();
				///////Ciutats condCiut = ciutList.get(rndCiut.nextInt(ciutList.size()));
				Ciutats condCiut = new Ciutats();
				condCiut.setId(967);
				nou.setCiutats(condCiut);
				
				Provincies condProv = new Provincies();
				//////condProv.setId(condCiut.getIdProv());
				condProv.setId(8);
				nou.setProvincies(condProv);
				
				// calculem aleatori ciutat/provincia
				Random rndTipus = new Random();
				///////Tipus condTipus = tipusList.get(rndTipus.nextInt(tipusList.size()));
				Tipus condTipus = new Tipus();
				condTipus.setId(1);
				nou.setTipus(condTipus);
				
				try
				{
				
				Inmobles inmoble = r.afegirInmoble(nou);
				}
				catch(Exception ex)
				{
					
				}
			}
		*/	
		
		// afegim solicituds 
		 /*
		for (int s=1; s<10000; s++)
		{
			// calculem un usuari aleatori
			Random rndUs = new Random();
			
			int usAleat = rndUs.nextInt(usuaris.length);
			if (usAleat == 0) usAleat++;
			Usuaris condUsuari = r2.cercarUsuari(usuaris[usAleat]);
			
			
			// calculem inmoble aleatori
			Random rndInm = new Random();  // inmobles
			int numAleatInm = rndInm.nextInt(3000);
			
			Inmobles condInmoble = new Inmobles();
			if (numAleatInm == 0) numAleatInm++;
			condInmoble.setAdreca(condUsuari.getNomusuari() + numAleatInm + "addr");
			condInmoble.setNom(condUsuari.getNomusuari() + numAleatInm + "nom");
			
			Ciutats ciutat = new Ciutats();
			ciutat.setId(967);
			ciutat.setIdProv(8);
			condInmoble.setCiutats(ciutat);
			
			tipus = new Tipus();
			tipus.setId(1);
			condInmoble.setTipus(tipus);
			
			try {
				Inmobles inmAleat = r.buscarInmobles(condInmoble).get(0);
				
				Solicituds sol = new Solicituds();
				sol.setInmobles(inmAleat);
				
				// CALCULEM EL COMPRADOR
				Random rndComp = new Random();
				
				int compAleat = rndComp.nextInt(usuaris.length);
				if (compAleat == 0) compAleat++;
				Usuaris condComprador = r2.cercarUsuari(usuaris[compAleat]);
				
				sol.setUsuaris(condComprador);
				r.solicitarInmobles(sol);
			}
			catch(Exception ex)
			{
			
			}
		}
			*/
		

		
		// afegim VALORS CARACTERISTIQUES 
		for (int s=1; s<10000; s++)
		{

			ValuesCaracteristiques vc = new ValuesCaracteristiques();
			
			// calculem inmoble aleatori
			Random rndInm = new Random();  // inmobles
			int numAleatInm = 36609 + rndInm.nextInt(3132);  // rang ids inmoble
			
			Inmobles inmoble = new Inmobles();
			inmoble.setId(numAleatInm);
			
			vc.setInmobles(inmoble);
			
			// calculem caracteristica
			Random rndCaract = new Random();  // inmobles
			Long numAleatCaract = 1L + rndCaract.nextInt(75);  // rang ids caracteristiques
			
			
			Caracteristiques caract = new Caracteristiques();
			caract.setId(numAleatCaract);
			
			vc.setCaracteristiques(caract);
			
			
			// calcumen el tipus de columna de la caracteristica.
			// en funcio del tipus el VALUE sera String o Integer
			String tipusColumna = r.tipusColumnaCaract(numAleatCaract);
			
			
			ValuesCaracteristiquesId vcId = new ValuesCaracteristiquesId();
			
			vcId.setIdcaracteristica(numAleatCaract);
			vcId.setIdinmoble(numAleatInm);
			
			vc.setId(vcId);
			
    		if (tipusColumna != null && tipusColumna.compareToIgnoreCase("INT") == 0)
    			vc.setValue("33");
    		else if (tipusColumna != null && tipusColumna.compareToIgnoreCase("VCHR") == 0)
    			vc.setValue("cadena");
    		else if (tipusColumna != null && tipusColumna.compareToIgnoreCase("BOOL") == 0)
    			{
    				Random rndBool = new Random();
    				rndBool.nextInt(1);
    				
    				vc.setValue(rndBool.nextInt(1) == 0 ? "true" : "false");
    			}
    		
    		
    		try {
    			r.afegirValorCaract(vc);
    		}
    		catch(Exception ex)  // podria ser que s'insertes duplicats, pero ha de segui
    		{
    			
    		}
    		
		}
			
		
	}
	
	

}
