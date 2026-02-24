package utils;

import java.util.List;
import java.util.Random;

public class GestionCartes {
	public static <Elem> extraire(List<Elem> list) {
		Random rand = new Random();
		int iChoix = list.get(rand.nextInt(list.size()));
	}
	
	public static List<Elem> mélanger(List<Elem> list) {
		
	}
	
	public static boolean verifierMelange(List<Elem> list1, List<Elem> list2) {
		
	}
	
	public static List<Elem> rassembler(List<Elem> list) {
		
	}
	
	public static boolean verifierRassemblement(List<Elem> list) {
		
	}
}
