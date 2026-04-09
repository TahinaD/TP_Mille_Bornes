package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	Joueur joueurCourant;
	Carte carteJouee;
	Joueur joueurCible;
	

	protected Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible;
	}


	public Joueur getJoueurCourant() {
		return joueurCourant;
	}


	public Carte getCarteJouee() {
		return carteJouee;
	}


	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite)
			return joueurCourant != joueurCible;
		return joueurCourant == joueurCible;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Coup) {
			Coup compareCoup = (Coup) obj;
				return joueurCourant != null && joueurCourant.equals(compareCoup.getJoueurCourant())
						&& carteJouee != null && carteJouee.equals(compareCoup.getCarteJouee())
								&& joueurCible != null && joueurCible.equals(compareCoup.getJoueurCible());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31*(joueurCourant.hashCode() + carteJouee.hashCode() + joueurCible.hashCode());
	}
}
