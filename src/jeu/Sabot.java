package jeu;

import cartes.Carte;

public class Sabot {
	int nbCartes;
	Carte[] cartes;
	
	public Sabot(int nbCartes, Carte[] cartes) {
		this.nbCartes = nbCartes;
		this.cartes = cartes;
	}
	
	public boolean estVide() {
		return (nbCartes == 0);
	}
	
	public void ajouterCarte(Carte carte) {
		
	}
}
