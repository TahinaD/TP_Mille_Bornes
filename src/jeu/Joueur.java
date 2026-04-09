package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneJeu;
	private MainJoueur mainJoueur;

	public Joueur(String nom) {
		this.nom = nom;
		this.zoneJeu = new ZoneDeJeu();
		this.mainJoueur = new MainJoueur();
	}

	@Override
	public String toString() {
		return "Joueur " + nom;
	}
	
	@Override
    public boolean equals(Object obj) {
		return obj instanceof Joueur joueur && joueur.nom == nom;
    }

	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		Carte cartePioche = sabot.piocher();
		donner(cartePioche);
		return cartePioche;
	}
	
	public void deposer(Carte carte) {
		zoneJeu.deposer(carte);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneJeu.estDepotAutorise(carte);
	}
	
	public HashSet<Coup> coupsPossibles(Set<Joueur> participants) {
		HashSet<Coup> coups = new HashSet<>();
		for (Iterator<Joueur> iterJ = participants.iterator(); iterJ.hasNext();) {
			for (ListIterator<Carte> iterM = (mainJoueur.getListeCartes()).listIterator(); iterM.hasNext();) {
				
			}
		}
		return coups;
	}

}
