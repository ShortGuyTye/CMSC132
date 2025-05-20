package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import java.util.*;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void getSize() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		BasicLinkedList<Integer> basicList2 = new BasicLinkedList<>();
		basicList.addToEnd(3);
		basicList.addToEnd(4);
		basicList.addToFront(1);
		basicList.addToFront(2);
		assertTrue(basicList.getSize() == 4 && basicList2.getSize() == 0);
	}
	
	@Test
	public void addToEnd() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		BasicLinkedList<Integer> basicList2 = new BasicLinkedList<>();
		basicList.addToEnd(1);
		basicList.addToEnd(3);
		basicList.addToEnd(2);
		basicList.addToEnd(4);
		
		assertTrue(basicList.getFirst() == 1 && basicList2.getFirst() == null);
	}
	@Test
	public void addToFront() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		BasicLinkedList<Integer> basicList2 = new BasicLinkedList<>();
		basicList.addToFront(3);
		basicList.addToFront(2);
		basicList.addToFront(1);
		basicList.addToFront(4);
		assertTrue(basicList.getFirst() == 4 && basicList2.getFirst() == null);
		
	}
	@Test
	public void getFirst() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		BasicLinkedList<Integer> basicList2 = new BasicLinkedList<>();
		basicList.addToFront(3);
		basicList.addToFront(2);
		basicList.addToFront(1);
		basicList.addToFront(4);
		assertTrue(basicList.getFirst() == 4 && basicList2.getFirst() == null);
	}
	@Test
	public void getLast() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		BasicLinkedList<Integer> basicList2 = new BasicLinkedList<>();
		basicList.addToFront(1);
		assertTrue(basicList.getLast() == 1 && basicList2.getFirst() == null);
	}
	@Test
	public void retrieveFirstElement() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		basicList.addToFront(1);
		basicList.addToFront(2);
		basicList.addToFront(3);
		basicList.addToFront(4);
		assertTrue(basicList.retrieveFirstElement() == 4 
				&& basicList.getFirst() == 3 && basicList.getSize() == 3);
	}
	@Test
	public void retrieveLastElement() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		basicList.addToFront(1);
		basicList.addToFront(2);
		basicList.addToFront(3);
		basicList.addToFront(4);
		assertTrue(basicList.retrieveLastElement() == 1 
				&& basicList.getLast() == 2 && basicList.getSize() == 3);
	}
	@Test
	public void remove() {
		BasicLinkedList<String> basicList = new BasicLinkedList<>();
		basicList.addToEnd("One");
		basicList.remove(null, String.CASE_INSENSITIVE_ORDER);
		assertTrue(basicList.getFirst() == "One");
		basicList.remove("One", String.CASE_INSENSITIVE_ORDER);
		assertTrue(basicList.getFirst() == null);
	}
	@Test
	public void iteration() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		ArrayList<Integer> testList = new ArrayList<>();
		ArrayList<Integer> answer = new ArrayList<>();
		answer.add(4);
		answer.add(3);
		answer.add(2);
		answer.add(1);
		basicList.addToFront(1);
		basicList.addToFront(2);
		basicList.addToFront(3);
		basicList.addToFront(4);
		
		Iterator<Integer> it = basicList.iterator();
		
		  while(it.hasNext()) { testList.add(it.next()); }
		  assertTrue(answer.equals(testList));
		 
	}
	@Test
	public void reverseArrayList() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		ArrayList<Integer> answer = new ArrayList<>();
		answer.add(1);
		answer.add(2);
		answer.add(3);
		answer.add(4);
		basicList.addToFront(1);
		basicList.addToFront(2);
		basicList.addToFront(3);
		basicList.addToFront(4);
		
		assertTrue(basicList.getReverseArrayList().equals(answer));
	}
	@Test
	public void reverseList() {
		BasicLinkedList<Integer> basicList = new BasicLinkedList<>();
		basicList.addToFront(1);
		basicList.addToFront(2);
		basicList.addToFront(3);
		basicList.addToFront(4);
		
		basicList = basicList.getReverseList();
		assertTrue(basicList.getFirst() == 1 && basicList.getLast() == 4
				&& basicList.getSize() == 4);
	}
	@Test
	public void add() {
		SortedLinkedList<String> sortedList 
		= new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sortedList.add("def");
		sortedList.add("jkl");
		sortedList.add("abc");
		sortedList.add("ghi");
		sortedList.add(null);
		
		assertTrue(sortedList.getFirst().equals("abc") 
				&& sortedList.getLast().equals("jkl"));

	}
	@Test
	public void removeSLL() {
		SortedLinkedList<String> sortedList 
		= new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sortedList.add("jkl");
		sortedList.add("ghi");
		sortedList.add("mno");
		sortedList.add("def");
		sortedList.add("abc");
		sortedList.remove("abc");
		
		assertTrue(sortedList.getFirst().equals("def") 
				&& sortedList.getLast().equals("mno") && sortedList.getSize() == 4);

	}
	@Test
	public void addToEndSLL() {
		SortedLinkedList<String> sortedList 
		= new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		boolean works = false;
		try {
			sortedList.addToEnd("abc");
		} catch(UnsupportedOperationException e) {
			works = true;
		}
		
		assertTrue(works);

	}
	@Test
	public void addToFrontSLL() {
		SortedLinkedList<String> sortedList 
		= new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		boolean works = false;
		try {
			sortedList.addToFront("abc");
		} catch(UnsupportedOperationException e) {
			works = true;
		}
		assertTrue(works);

	}
}
