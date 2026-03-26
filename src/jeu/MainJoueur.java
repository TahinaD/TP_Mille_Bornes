package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	List<Carte> listeCartes;

	public MainJoueur() {
		this.listeCartes = new ArrayList<>();
	}
	
	public void prendre(Carte carte) {
		listeCartes.add(carte);
	}
	
	public void jouer(Carte carte) {
		if (listeCartes.contains(carte))
			listeCartes.remove(carte);
	}

	@Override
	public String toString() {
		return "MainJoueur : " + listeCartes;
	}

}
