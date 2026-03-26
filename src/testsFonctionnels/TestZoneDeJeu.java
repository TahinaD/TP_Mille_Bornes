package testsFonctionnels;

import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	
	public static void main(String[] args) {
		
		ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
		System.out.println("Deposer carte 25 km");
		zoneDeJeu.deposer(new Borne(25));
		System.out.println("Deposer carte 50 km");
		zoneDeJeu.deposer(new Borne(50));
		System.out.println("Deposer carte 75 km");
		zoneDeJeu.deposer(new Borne(75));
		System.out.println("Total des bornes : " + zoneDeJeu.donnerKmParcourus());

		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
		zoneDeJeu.deposer(new DebutLimite());
		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
		zoneDeJeu.deposer(new FinLimite());
		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
	}
}