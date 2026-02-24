package testsFonctionnels;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import jeu.Sabot;

public class TestSabot {

	public static void main(String[] args) {
		JeuDeCartes nouveauJeu = new JeuDeCartes();
		Sabot sabotJeu1 = new Sabot(nouveauJeu.donnerCartes());
		Carte cartePiochee;
		
		while (!(sabotJeu1.estVide())) {
				cartePiochee = sabotJeu1.piocher();
				System.out.println("je pioche " + cartePiochee.toString());
		}
		
		Sabot sabotJeu2 = new Sabot(nouveauJeu.donnerCartes());
		cartePiochee = sabotJeu2.piocher();
		sabotJeu2.ajouterCarte(new Botte(cartes.Type.ACCIDENT));
		try {
			for (Iterator<Carte> iterator = sabotJeu2.iterator(); iterator.hasNext();) {
				System.out.println("je pioche " + iterator.next());
				iterator.remove();
				cartePiochee = sabotJeu2.piocher();
				
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Trop ou pas assez de cartes dans la pioche.");
		} catch (ConcurrentModificationException e) {
			System.out.println("Plusieurs joueurs piochent en même temps.");
		}
	}

}
