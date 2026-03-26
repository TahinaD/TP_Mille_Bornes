package cartes;

public abstract class Carte {
	@Override
	public boolean equals(Object obj) {
		return getClass().equals(obj.getClass());
	}
}
