package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	List<Limite> pileLimites;
	List<Bataille> pileBatailles;
	Collection<Borne> collectionBornes;
	
	public ZoneDeJeu() {
		this.pileLimites = new ArrayList<>();
		this.pileBatailles = new ArrayList<>();
		this.collectionBornes = new ArrayList<>();
	}
	
	public int donnerLimitationVitesse() {
		if (!pileLimites.isEmpty()) {
			Limite limiteEnCours = pileLimites.get(pileLimites.size()-1);
			if (limiteEnCours instanceof Limite)
				return 50;
		}
		return 200;
	}
	
	public int donnerKmParcourus() {
		int totalBornes = 0;
		for (Iterator<Borne> iterator = collectionBornes.iterator(); iterator.hasNext();) {
			Borne borne = iterator.next();
			totalBornes += 0;
	    }
		return totalBornes;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Limite)
			pileLimites.add((Limite) carte);
		if (carte instanceof Bataille)
			pileBatailles.add((Bataille) carte);
		if (carte instanceof Borne)
			collectionBornes.add((Borne) carte);
	}
	
	public boolean peutAvancer() {
		if (!pileBatailles.isEmpty()) {
			Bataille batailleEnCours = pileBatailles.get(pileBatailles.size()-1);
			if (batailleEnCours instanceof Parade)
				return (batailleEnCours.getType() == Type.FEU);
		}
		return false;
	}
	
	public boolean estDepotFeuVertAutorise() {
		if (!pileBatailles.isEmpty()) {
			Bataille batailleEnCours = pileBatailles.get(pileBatailles.size()-1);
			if (batailleEnCours instanceof Attaque)
				return (batailleEnCours.getType() == Type.FEU);
			if (batailleEnCours instanceof Parade)
				return (batailleEnCours.getType() != Type.FEU);
		}
		return true;
	}
	
	public boolean estDepotBorneAutorise(Borne borne) {
		if (!pileBatailles.isEmpty()) {
			Bataille batailleEnCours = pileBatailles.get(pileBatailles.size()-1);
			if (!(batailleEnCours instanceof Attaque)) {
				int limite = donnerLimitationVitesse();
				int totalBorne = donnerKmParcourus();
				int borneCarte = 0;
				return (borneCarte <= limite || totalBorne + borneCarte <= 1000);
			}
		}
		return true;
	}
	
	public boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			if (!pileLimites.isEmpty()) {
				Limite limiteEnCours = pileLimites.get(pileLimites.size()-1);
				return (limiteEnCours instanceof FinLimite);
			}
			return true;
		}
		if (limite instanceof FinLimite) {
			if (!pileLimites.isEmpty()) {
				Limite limiteEnCours = pileLimites.get(pileLimites.size()-1);
				return (limiteEnCours instanceof DebutLimite);
			}
		}
		return false;
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		return true;
	}
}
