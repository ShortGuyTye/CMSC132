package tests;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import implementation.BinarySearchTree;
import implementation.GetDataIntoArrays;
import implementation.KeyValuePair;
import implementation.TreeIsEmptyException;
import implementation.TreeIsFullException;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {

	/* Remove the following test and add your tests */
	@Test
	public void test1() {
		System.out.println("======== Marker #1 ========");
		Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
		int maxEntries = 11;
		BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>(
				comparator, maxEntries);
		System.out.println(bst);
		System.out.println("Empty Tree?: " + bst.isEmpty());

		System.out.println("\n======== Marker #2 ========");
		try {
			bst.add("Oliver", 1000).add("Arlene", 50000).add("Terry", 60)
					.add("Bob", 10).add("Don", 5000).add("Carl", 500)
					.add("Wade", 200).add("Steve", 1).add("Earl", 100000)
					.add("Aubry", 50).add("Alex", 33);
		} catch (TreeIsFullException e) {
			System.out.println("full tree");
		}
		System.out.println(bst);
		System.out.println("Full Tree?: " + bst.isFull());
		System.out.println("Size: " + bst.size());

		System.out.println("\n======== Marker #3 ========");
		try {
			KeyValuePair<String, Integer> maximum = bst.getMaximumKeyValue();
			KeyValuePair<String, Integer> minimum = bst.getMinimumKeyValue();
			System.out.println(
					"Maximum: " + maximum.getKey() + "/" + maximum.getValue());
			System.out.println(
					"Minimum: " + minimum.getKey() + "/" + minimum.getValue());
		} catch (TreeIsEmptyException e) {
			System.out.println("empty tree");
		}

		System.out.println("\n======== Marker #4 ========");
		KeyValuePair<String, Integer> found = bst.find("Don");
		System.out.println(found == null ? "NOT FOUND"
				: found.getKey() + "/" + found.getValue());

		System.out.println("\n======== Marker #5 ========");
		GetDataIntoArrays<String, Integer> callback = new GetDataIntoArrays<String, Integer>();
		bst.processInorder(callback);
		System.out.println("Keys: " + callback.getKeys());
		System.out.println("Values: " + callback.getValues());

		System.out.println("\n======== Marker #6 ========");
		/*
		 * Terry is not a mistake; it shows that lowerLimit and upperLimit does
		 * not need to be in the tree to be valid limits during the generation
		 * of a subtree.
		 */
		BinarySearchTree<String, Integer> subTree = bst.subTree("B",
				"D");
		System.out.println("Tree: " + bst);
		System.out.println("SubTree: " + subTree);

		System.out.println("\n======== Marker #7 ========");
		TreeSet<Integer> leavesValuesSet = bst.getLeavesValues();
		System.out.println(leavesValuesSet);

		System.out.println("\n======== Marker #8 ========");
		try {
			bst.delete("Wade");
		} catch (TreeIsEmptyException e) {
			System.out.println("empty tree");
		}
		System.out.println(bst);
		assertTrue(true);
	}
}
