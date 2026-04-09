package jeu;

import java.util.LinkedList;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	LinkedList<Limite> pileLimites;
	LinkedList<Bataille> pileBatailles;
	Collection<Borne> collectionBornes;
	Set<Botte> bottes;
	
	public ZoneDeJeu() {
		this.pileLimites = new LinkedList<>();
		this.pileBatailles = new LinkedList<>();
		this.collectionBornes = new LinkedList<>();
		this.bottes = new HashSet<>();
	}
	
	private Limite sommetPileLimite() {
		return pileLimites.peekFirst();
	}
	
	private Bataille sommetPileBataille() {
		return pileBatailles.peekFirst();
	}
	
	public int donnerLimitationVitesse() {
		if (sommetPileLimite() instanceof DebutLimite && !estPrioritaire())
			return 50;
		return 200;
	}
	
	public int donnerKmParcourus() {
		int totalBornes = 0;
		for (Iterator<Borne> iterator = collectionBornes.iterator(); iterator.hasNext();)
			totalBornes += (iterator.next()).getKm();
		return totalBornes;
	}
	
	public void deposer(Carte carte) {
		if (carte instanceof Limite)
			pileLimites.offerFirst((Limite) carte);
		if (carte instanceof Bataille)
			pileBatailles.offerFirst((Bataille) carte);
		if (carte instanceof Borne)
			collectionBornes.add((Borne) carte);
		if (carte instanceof Botte)
			bottes.add((Botte) carte);
	}
	
	public boolean peutAvancer() {
		Bataille batailleEnCours = sommetPileBataille();
		boolean prioritaire = estPrioritaire();
		if (batailleEnCours != null && batailleEnCours instanceof Parade)
				return (batailleEnCours.getType() == Type.FEU || prioritaire);
		if (batailleEnCours != null && batailleEnCours instanceof Attaque)
			return ((batailleEnCours.getType() == Type.FEU && prioritaire)  
					|| (bottes.contains(new Botte(batailleEnCours.getType())) && prioritaire));
		return prioritaire;
	}
	
	public boolean estDepotFeuVertAutorise() {
		Bataille batailleEnCours = sommetPileBataille();
		if (estPrioritaire())
			return false;
		if (batailleEnCours instanceof Attaque)
			return (batailleEnCours.getType() == Type.FEU)  
					|| bottes.contains(new Botte(batailleEnCours.getType()));
		if (batailleEnCours instanceof Parade)
			return (batailleEnCours.getType() != Type.FEU);
		return true;
	}
	
	public boolean estDepotBorneAutorise(Borne borne) {
		Bataille batailleEnCours = pileBatailles.get(pileBatailles.size()-1);
			if (!(batailleEnCours instanceof Attaque)) {
				int limite = donnerLimitationVitesse();
				int totalBorne = donnerKmParcourus();
				int borneCarte = borne.getKm();
				return (borneCarte <= limite || totalBorne + borneCarte <= 1000);
			}
		return true;
	}
	
	public boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite)
			return (sommetPileLimite() instanceof FinLimite && !estPrioritaire());
		if (limite instanceof FinLimite)
			return (sommetPileLimite() instanceof DebutLimite && !estPrioritaire());
		return false;
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bottes.contains(new Botte(bataille.getType())))
			return false;
		Bataille batailleEnCours = sommetPileBataille();
		if (bataille instanceof Attaque)
			return !(batailleEnCours instanceof Attaque);
		if (bataille instanceof Parade) {
			if (bataille.getType() == Type.FEU) {
				return estDepotFeuVertAutorise();
			} else {
				return (batailleEnCours instanceof Attaque && batailleEnCours.getType() == bataille.getType());
			}
		}
		return true;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne)
			return estDepotBorneAutorise((Borne) carte);
		if (carte instanceof Limite)
			return estDepotLimiteAutorise((Limite) carte);
		if (carte instanceof Bataille)
			return estDepotBatailleAutorise((Bataille) carte);
		if (carte instanceof Botte)
			return true;
		return false;
	}
	
	public boolean estPrioritaire() {
		return bottes.contains(new Botte(Type.FEU));
	}
}
