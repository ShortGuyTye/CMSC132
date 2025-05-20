package implementation;

import java.util.Comparator;
import java.util.TreeSet;

public class BinarySearchTree<K, V> {
	/*
	 * You may not modify the Node class and may not add any instance nor static
	 * variables to the BinarySearchTree class.
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;

	public BinarySearchTree(Comparator<K> comparator, int maxEntries) {
		if (comparator == null || maxEntries < 1) {
			throw new IllegalArgumentException("Invalid parameter");
		}
		this.comparator = comparator;
		this.maxEntries = maxEntries;
		root = null;
		treeSize = 0;
	}

	public BinarySearchTree<K, V> add(K key, V value)
			throws TreeIsFullException {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Null Parameters");
		}
		if (treeSize == maxEntries) {
			throw new TreeIsFullException("Tree is full");
		}
		if (root == null) {
			root = new Node(key, value);
			treeSize++;
			return this;
		} else {
			return addAux(key, value, root);
		}
	}

	private BinarySearchTree<K, V> addAux(K key, V value, Node rootAux) {
		int comparison = comparator.compare(key, rootAux.key);

		if (comparison == 0) {
			rootAux.value = value;
			return this;
		} else if (comparison < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(key, value);
				treeSize++;
				return this;
			} else {
				return addAux(key, value, rootAux.left);
			}
		} else {
			if (rootAux.right == null) {
				rootAux.right = new Node(key, value);
				treeSize++;
				return this;
			} else {
				return addAux(key, value, rootAux.right);
			}
		}
	}

	public String toString() {
		if (root == null) {
			return "EMPTY TREE";
		}
		return toStringAux(root);
	}

	private String toStringAux(Node rootAux) {
		return rootAux == null ? ""
				: toStringAux(rootAux.left) + "{" + rootAux.key + ":"
						+ rootAux.value + "}" + toStringAux(rootAux.right);
	}

	/* Provided: */
	public boolean isEmpty() {
		return root == null;
	}

	/* Provided: */
	public int size() {
		return treeSize;
	}

	/* Provided: */
	public boolean isFull() {
		return treeSize == maxEntries;
	}

	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
		if (root == null) {
			throw new TreeIsEmptyException("Tree is empty");
		}
		return getMinimumKeyValueAux(root);
	}

	private KeyValuePair<K, V> getMinimumKeyValueAux(Node rootAux) {
		if (rootAux.left == null) {
			return new KeyValuePair<K, V>(rootAux.key, rootAux.value);
		} else {
			return getMinimumKeyValueAux(rootAux.left);
		}

	}

	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if (root == null) {
			throw new TreeIsEmptyException("Tree is empty");
		}
		return getMaximumKeyValueAux(root);
	}

	private KeyValuePair<K, V> getMaximumKeyValueAux(Node rootAux) {
		if (rootAux.right == null) {
			return new KeyValuePair<K, V>(rootAux.key, rootAux.value);
		} else {
			return getMaximumKeyValueAux(rootAux.right);
		}

	}

	public KeyValuePair<K, V> find(K key) {
		return findAux(key, root);
	}

	public KeyValuePair<K, V> findAux(K key, Node rootAux) {
		if (rootAux == null) {
			return null;
		} else {
			int comparison = comparator.compare(key, rootAux.key);
			if (comparison == 0) {
				return new KeyValuePair<K, V>(rootAux.key, rootAux.value);
			} else if (comparison < 0) {
				return findAux(key, rootAux.left);
			} else {
				return findAux(key, rootAux.right);
			}
		}

	}

	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
		if (key == null) {
			throw new IllegalArgumentException("null parameter");
		}
		if (root == null) {
			throw new TreeIsEmptyException("Tree is empty");
		}
		deleteAux(key, root);
		return this;
	}

	public void deleteAux(K key, Node rootAux) {
		if (rootAux == null) {
			return;
		}
		// Checks the rootNode
		if (comparator.compare(key, rootAux.key) == 0) {
			if (rootAux.left == null && rootAux.right == null) {
				rootAux = null;
				treeSize--;
			} else if (rootAux.left != null) {
				rootAux.key = getMaximumKeyValueAux(rootAux.left).getKey();
				rootAux.value = getMaximumKeyValueAux(rootAux.left).getValue();
				deleteAux(getMaximumKeyValueAux(rootAux.left).getKey(),
						rootAux.left);
				treeSize--;
			} else {
				rootAux.key = getMinimumKeyValueAux(rootAux.right).getKey();
				rootAux.value = getMinimumKeyValueAux(rootAux.right).getValue();
				deleteAux(getMinimumKeyValueAux(rootAux.right).getKey(),
						rootAux.right);
				treeSize--;
			}
			// Checks to the left
		} else if (rootAux.left != null
				&& comparator.compare(key, rootAux.left.key) == 0) {
			if (rootAux.left.left == null && rootAux.left.right == null) {
				rootAux.left = null;
				treeSize--;
			} else if (rootAux.left.left != null) {
				K keyTemp = getMaximumKeyValueAux(rootAux.left.left).getKey();
				V valueTemp = getMaximumKeyValueAux(rootAux.left.left)
						.getValue();
				deleteAux(getMaximumKeyValueAux(rootAux.left.left).getKey(),
						rootAux.left);
				rootAux.left.key = keyTemp;
				rootAux.left.value = valueTemp;
				treeSize--;
			} else {
				K keyTemp = getMinimumKeyValueAux(rootAux.left.right).getKey();
				V valueTemp = getMinimumKeyValueAux(rootAux.left.right)
						.getValue();
				deleteAux(getMinimumKeyValueAux(rootAux.left.right).getKey(),
						rootAux.left);
				rootAux.left.key = keyTemp;
				rootAux.left.value = valueTemp;
				treeSize--;
			}
			// Checks to the right
		} else if (rootAux.right != null
				&& comparator.compare(key, rootAux.right.key) == 0) {
			if (rootAux.right.left == null && rootAux.right.right == null) {
				rootAux.right = null;
				treeSize--;
			} else if (rootAux.right.left != null) {
				K keyTemp = getMaximumKeyValueAux(rootAux.right.left).getKey();
				V valueTemp = getMaximumKeyValueAux(rootAux.right.left)
						.getValue();
				deleteAux(getMaximumKeyValueAux(rootAux.right.left).getKey(),
						rootAux.right);
				rootAux.right.key = keyTemp;
				rootAux.right.value = valueTemp;
				treeSize--;
			} else {
				K keyTemp = getMinimumKeyValueAux(rootAux.right.right).getKey();
				V valueTemp = getMinimumKeyValueAux(rootAux.right.right)
						.getValue();
				deleteAux(getMinimumKeyValueAux(rootAux.right.right).getKey(),
						rootAux.right);
				rootAux.right.key = keyTemp;
				rootAux.right.value = valueTemp;
				treeSize--;
			}
		}
		if (rootAux != null && comparator.compare(key, rootAux.key) < 0) {
			deleteAux(key, rootAux.left);
		} else if (rootAux != null) {
			deleteAux(key, rootAux.right);
		}
	}

	public void processInorder(Callback<K, V> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("null parameter");
		}
		processInorderAux(root, callback);
	}

	private void processInorderAux(Node node, Callback<K, V> callback) {
		if (node == null) {
			return;
		}
		processInorderAux(node.left, callback);
		callback.process(node.key, node.value);
		processInorderAux(node.right, callback);
	}

	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		if (lowerLimit == null || upperLimit == null
				|| comparator.compare(lowerLimit, upperLimit) > 0) {
			throw new IllegalArgumentException("Illegal parameter");
		}
		BinarySearchTree<K, V> bst = new BinarySearchTree<K, V>(comparator,
				maxEntries);
		return subTreeAux(root, lowerLimit, upperLimit, null, bst);
	}

	private BinarySearchTree<K, V> subTreeAux(Node node, K lowerLimit,
			K upperLimit, Node newNode, BinarySearchTree<K, V> bst) {
		if (node == null) {
			return null;
		}
		if (comparator.compare(node.key, lowerLimit) >= 0
				&& comparator.compare(node.key, upperLimit) <= 0) {
			if (bst.root == null) {
				bst.root = new Node(node.key, node.value);
				newNode = bst.root;
			} else {
				if (comparator.compare(node.key, newNode.key) < 0) {
					newNode.left = new Node(node.key, node.value);
					newNode = newNode.left;
				} else if (comparator.compare(node.key, newNode.key) > 0) {
					newNode.right = new Node(node.key, node.value);
					newNode = newNode.right;
				}
			}
			subTreeAux(node.left, lowerLimit, upperLimit, newNode, bst);
			subTreeAux(node.right, lowerLimit, upperLimit, newNode, bst);
		} else {
			if (node.left != null
					&& comparator.compare(node.key, lowerLimit) > 0) {
				subTreeAux(node.left, lowerLimit, upperLimit, newNode, bst);
			}
			if (comparator.compare(node.key, upperLimit) < 0) {
				subTreeAux(node.right, lowerLimit, upperLimit, newNode, bst);
			}
		}
		return bst;
	}

	public TreeSet<V> getLeavesValues() {
		TreeSet<V> treeSet = new TreeSet<V>();
		return getLeavesValuesAux(root, treeSet);
	}

	private TreeSet<V> getLeavesValuesAux(Node node, TreeSet<V> treeSet) {
		if (node == null) {
			return null;
		}
		if (node.left == null && node.right == null) {
			treeSet.add(node.value);
		}
		getLeavesValuesAux(node.left, treeSet);
		getLeavesValuesAux(node.right, treeSet);
		return treeSet;
	}
}
