package jeu;

import cartes.Attaque;
import cartes.Botte;
import cartes.Parade;
import cartes.Type;

public interface Cartes {
	static final Botte PRIORITAIRE = new Botte(Type.FEU);
	static final Attaque FEU_ROUGE = new Attaque(Type.FEU);
	static final Parade FEU_VERT = new Parade(Type.FEU);
}
