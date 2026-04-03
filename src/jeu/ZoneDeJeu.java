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
		for (Iterator<Borne> iterator = collectionBornes.iterator(); iterator.hasNext();)
			totalBornes += (iterator.next()).getKm();
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
	
	private Limite sommetPileLimite() {
		if (!pileLimites.isEmpty())
			return pileLimites.get(pileLimites.size()-1);
		return null;
	}
	
	private Bataille sommetPileBataille() {
		if (!pileBatailles.isEmpty())
			return pileBatailles.get(pileBatailles.size()-1);
		return null;
	}
	
	public boolean peutAvancer() {
		Bataille batailleEnCours = sommetPileBataille();
		if (batailleEnCours instanceof Parade)
				return (batailleEnCours.getType() == Type.FEU);
		return false;
	}
	
	public boolean estDepotFeuVertAutorise() {
		Bataille batailleEnCours = sommetPileBataille();
		if (batailleEnCours instanceof Attaque)
			return (batailleEnCours.getType() == Type.FEU);
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
			return (sommetPileLimite() instanceof FinLimite);
		if (limite instanceof FinLimite)
			return (sommetPileLimite() instanceof DebutLimite);
		return false;
	}
	
	public boolean estDepotBatailleAutorise(Bataille bataille) {
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
		return false;
	}
}
