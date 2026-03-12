package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	Sabot sabot;

	public Jeu(Sabot sabot) {
		JeuDeCartes nouveauJeu = new JeuDeCartes();
		Carte[] jeuComplet = nouveauJeu.donnerCartes();
		List<Carte> listeCartes = listeCartes.toArray(jeuComplet);
		GestionCartes.mélanger(listeCartes);
		Carte[] jeuDepart = Collections.addAll(listeCartes);;
		this.sabot = new Sabot(jeuDepart);
	}
}
