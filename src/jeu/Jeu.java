package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	Sabot sabot;

	public Jeu() {
		JeuDeCartes nouveauJeu = new JeuDeCartes();
		Carte[] jeuComplet = nouveauJeu.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>(); 
		Collections.addAll(listeCartes, jeuComplet);
		GestionCartes.mélanger(listeCartes);
		Carte[] jeuDepart = listeCartes.toArray(new Carte[jeuComplet.length]);;
		this.sabot = new Sabot(jeuDepart);
	}
}
