package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random rand = new Random();
	
	private GestionCartes() {
		throw new IllegalStateException("Utility Class");
	}

	public static <E> E extraireList(List<E> list) {
		int iChoix = rand.nextInt(list.size());
		E choix = list.get(iChoix);
		list.remove(iChoix);
		return choix;
	}
	
	public static <E> E extraireListVIter(List<E> list) {
		int iChoix = rand.nextInt(list.size());
		ListIterator<E> iter = list.listIterator(iChoix);
		E choix = iter.next();
		iter.remove();
		return choix;
	}
	
	public static <E> List<E> melanger(List<E> list) {
		List<E> returnList = new ArrayList<>();
		while (!list.isEmpty())
			returnList.add(extraireListVIter(list));
		return returnList;
	}
	
	public static <E> boolean verifierMelange(List<E> list1, List<E> list2) {
		if (list1.size() != list2.size())
			return false;
		for (int i = 0; i < list1.size(); i++) {
			E elem = list1.get(i);
			if (Collections.frequency(list1, elem) != Collections.frequency(list2, elem))
				return false;
		}
		return true;
	}
	
	public static <E> List<E> rassembler(List<E> list) {
		List<E> returnList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			E current = list.get(i);
			if (!returnList.contains(current)) {
				for (int j = 0; j < Collections.frequency(list, current); j++) {
					returnList.add(current);
				}
			}
		}
		return returnList;
	}
	
	private static <E> boolean sautDouble(E elem, int nextIndex, List<E> list) {
		for (ListIterator<E> iter2 = list.listIterator(nextIndex); iter2.hasNext();) {
			E nextElem = iter2.next();
			if (nextElem == elem)
				return true;
		}
		return false;
	}
	
	public static <E> boolean verifierRassemblement(List<E> list) {
		E elem = list.get(0);
		for (ListIterator<E> iter1 = list.listIterator(); iter1.hasNext();) {
			E nextElem = iter1.next();
			if (nextElem != elem && sautDouble(elem, iter1.nextIndex(), list))
					return false;
			elem = nextElem;
		}
		return true;
	}
}
