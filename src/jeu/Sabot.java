package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private int nbCartes;
	private int capacite;
	private Carte[] pioche;
	private int nombreOperations = 0;
	
	public Iterator<Carte> iterator() { 
		return new Iterateur(); 
	}
	
	
	public Sabot(Carte[] pioche) {
		this.pioche = pioche;
		this.nbCartes = pioche.length;
		this.capacite = pioche.length;
	}
	
	public boolean estVide() {
		return (nbCartes == 0);
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes > capacite)
			throw new IllegalStateException();
		else {
			pioche[nbCartes] = carte;
			nbCartes++;
			nombreOperations++;
		}
	}
	
	public Carte piocher() {
		if (estVide()) {
			throw new IllegalStateException();
		}
		Iterateur iterateur = new Iterateur();
		Carte cartePiochee = null;
		if (iterateur.hasNext())
			cartePiochee = iterateur.next();
		iterateur.remove();
		nbCartes--;
		return cartePiochee;
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		@Override
		public boolean hasNext() {
			return (indiceIterateur < nbCartes);
		}
		
		@Override
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte nextCarte = pioche[indiceIterateur];
				nextEffectue = true;
				indiceIterateur++;
				return nextCarte;
			} else
				throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue)
				throw new IllegalStateException();
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				pioche[i] = pioche[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nombreOperations++; 
			nombreOperationsReference++;
		}
		
		private void verificationConcurrence(){
			if (nombreOperations != nombreOperationsReference)
				throw new ConcurrentModificationException();
		}
	}
}
