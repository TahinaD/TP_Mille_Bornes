package testsFonctionnels;

import cartes.Carte;
import cartes.JeuDeCartes;

public class TestJeuDeCartes {

	public static void main(String[] args) {
		JeuDeCartes nouveauJeu = new JeuDeCartes();
		System.out.println(nouveauJeu.affichageJeuDeCartes());
		Carte[] jeuComplet = nouveauJeu.donnerCartes();
		for (Carte carte : jeuComplet) {
			System.out.println(carte.toString());
		}
	}

}
