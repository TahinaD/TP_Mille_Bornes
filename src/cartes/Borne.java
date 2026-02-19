package cartes;

public class Borne extends Carte {

	private int km;

	private Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km + " KM";
	}
}
