package implementation;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		adjacencyMap = new HashMap<>();
		dataMap = new HashMap<>();
	}

	public void addVertex(String vertexName, E data) {
		if (adjacencyMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("Vertex already added");
		} else {
			adjacencyMap.put(vertexName, new HashMap<>());
			dataMap.put(vertexName, data);
		}
	}

	public void addDirectedEdge(String startVertexName, String endVertexName,
			int cost) {
		if (!adjacencyMap.containsKey(startVertexName)
				|| !adjacencyMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException("Invalid Vertex");
		}
		adjacencyMap.get(startVertexName).put(endVertexName, cost);
	}

	public String toString() {
		TreeMap<String, E> sorted = new TreeMap<>(dataMap);
		String answer = "Vertices: [" + sorted.firstKey();
		sorted.remove(sorted.firstKey());
		Set<String> keys = sorted.keySet();
		if (!sorted.isEmpty()) {
			for (String key : keys) {
				answer += ", " + key;
			}
		}
		answer += "]\nEdges:";

		sorted = new TreeMap<>(dataMap);
		keys = sorted.keySet();
		for (String key : keys) {
			answer += "\nVertex(" + key + ")--->{";
			TreeMap<String, Integer> pointeds = new TreeMap<>(
					adjacencyMap.get(key));
			Set<String> pKeys = pointeds.keySet();
			if (!pointeds.isEmpty()) {
				answer += pointeds.firstKey() + "="
						+ pointeds.get(pointeds.firstKey());
				pKeys.remove(pointeds.firstKey());
				if (!pKeys.isEmpty()) {
					for (String pkey : pKeys) {
						answer += ", " + pkey + "=" + pointeds.get(pkey);
					}
				}
			}
			answer += "}";
		}
		return answer;
	}

	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		if(!adjacencyMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		if (adjacencyMap.containsKey(vertexName)) {
			return adjacencyMap.get(vertexName);
		}
		return new HashMap<String, Integer>();
	}

	public int getCost(String startVertexName, String endVertexName) {
		if (!adjacencyMap.containsKey(startVertexName)
				|| !adjacencyMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		return adjacencyMap.get(startVertexName).get(endVertexName);
	}

	public Set<String> getVertices() {
		return dataMap.keySet();
	}

	public E getData(String vertex) {
		if (!dataMap.containsKey(vertex)) {
			throw new IllegalArgumentException("No such vertex");
		}
		return dataMap.get(vertex);
	}

	public void doDepthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException("No Vertex");
		}
		Set<String> visited = new HashSet<>();
		Stack<String> discovered = new Stack<>();
		discovered.push(startVertexName);
		while (!discovered.isEmpty()) {
			String curr = discovered.pop();
			if (!visited.contains(curr)) {
				visited.add(curr);
				callback.processVertex(curr, dataMap.get(curr));
				Set<String> temp = adjacencyMap.get(curr).keySet();
				TreeSet<String> keys = new TreeSet<>(temp);
				for (String key : keys) {
					if (!visited.contains(key)) {
						discovered.push(key);
					}
				}
			}
		}
	}

	public void doBreadthFirstSearch(String startVertexName,
			CallBack<E> callback) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException("No Vertex");
		}
		Set<String> visited = new HashSet<>();
		PriorityQueue<String> discovered = new PriorityQueue<>();
		discovered.offer(startVertexName);
		while (!discovered.isEmpty()) {
			String curr = discovered.poll();
			if (!visited.contains(curr)) {
				visited.add(curr);
				callback.processVertex(curr, dataMap.get(curr));
				Set<String> temp = adjacencyMap.get(curr).keySet();
				TreeSet<String> keys = new TreeSet<>(temp);
				for (String key : keys) {
					if (!visited.contains(key)) {
						discovered.offer(key);
					}
				}
			}
		}
	}

	public int doDijkstras(String startVertexName, String endVertexName,
			ArrayList<String> shortestPath) {
		if (!adjacencyMap.containsKey(startVertexName)
				|| !adjacencyMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		if (startVertexName == endVertexName) {
			shortestPath.add(endVertexName);
			return 0;
		}
		HashSet<String> visited = new HashSet<>();
		Queue<String> disco = new LinkedList<>();
		HashMap<String, Integer> costs = new HashMap<>();
		HashMap<String, String> paths = new HashMap<>();
		Set<String> keys = adjacencyMap.keySet();
		for (String key : keys) {
			costs.put(key, Integer.MAX_VALUE);
			paths.put(key, null);
		}
		disco.offer(startVertexName);
		costs.put(startVertexName, 0);
		while (!disco.isEmpty()) {
			String curr = disco.poll();
			if (!visited.contains(curr)) {
				visited.add(curr);
				Set<String> pointed = adjacencyMap.get(curr).keySet();
				TreeMap<Integer, String> toBeAdded = new TreeMap<>();
				for (String key : pointed) {
					int cost = costs.get(curr)
							+ adjacencyMap.get(curr).get(key);
					if (cost < costs.get(key)) {
						costs.put(key, cost);
						paths.put(key, curr);
					}
					toBeAdded.put(cost, key);
				}
				Set<Integer> nodes = toBeAdded.keySet();
				for (int key : nodes) {
					if (!visited.contains(toBeAdded.get(key))) {
						disco.offer(toBeAdded.get(key));
					}
				}

			}
		}
		if (paths.get(endVertexName) == null) {
			shortestPath.add("None");
		} else {
			String curr = endVertexName;
			while (curr != null) {
				shortestPath.add(curr);
				curr = paths.get(curr);
			}
			Collections.reverse(shortestPath);
		}
		if (costs.get(endVertexName) == Integer.MAX_VALUE) {
			return -1;
		}
		return costs.get(endVertexName);
	}
}