package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes;
	
	private static class Configuration {
		private Carte carte;
		private int nbExemplaires;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}
	
	public String affichageJeuDeCartes() {
		return "";
	}

}
