package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	public static <Elem> Elem extraireList(List<Elem> list) {
		Random rand = new Random();
		int iChoix = rand.nextInt(list.size());
		Elem choix = list.get(iChoix);
		list.remove(iChoix);
		return choix;
	}
	
	public static  <Elem> List<Elem> mélanger(List<Elem> list) {
		List<Elem> returnList = new ArrayList<>();
		while (!list.isEmpty())
			returnList.add(extraireList(list));
		return returnList;
		
	}
	
	public static <Elem> boolean verifierMelange(List<Elem> list1, List<Elem> list2) {
		if (list1.size() != list2.size())
			return false;
		for (int i = 0; i < list1.size(); i++) {
			Elem elem = list1.get(i);
			if (Collections.frequency(list1, elem) != Collections.frequency(list2, elem))
				return false;
		}
		return true;
	}
	
	public static <Elem> List<Elem> rassembler(List<Elem> list) {
		List<Elem> returnList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Elem current = list.get(i);
			if (!returnList.contains(current)) {
				for (int j = 0; j < Collections.frequency(list, current); j++) {
					returnList.add(current);
				}
			}
		}
		return returnList;
	}
	
	public static <Elem> boolean verifierRassemblement(List<Elem> list) {
		ListIterator<Elem> iter1 = list.listIterator(0);
		ListIterator<Elem> iter2 = list.listIterator(0);
		return true;
	}
}
