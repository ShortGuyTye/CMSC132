package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Process implements Runnable {
	private String fileName, orderFileName;
	private HashMap<String, Double> items;
	private FileWrite fileWrite;
	private ArrayList<Integer> orderOfOrders;
	private Object lock;
	private int orderNum;

	public Process(String fileName, String orderFileName, String resultFileName,
			int orderNum, boolean threads, ArrayList<Integer> orderOfOrders)
			throws IOException {
		this.fileName = fileName;
		this.orderFileName = orderFileName;
		this.orderNum = orderNum;
		items = getItemMap();
		fileWrite = new FileWrite(resultFileName, items);
		this.orderOfOrders = orderOfOrders;
		lock = new Object();

	}

	public void run() {
		try {
			int i = 0;
			TreeMap<String, Integer> totalItems = new TreeMap<>();
			synchronized (lock) {
				i = orderOfOrders.get(0);
				orderOfOrders.remove(0);
			}
			TreeMap<String, Double> orderItems = new TreeMap<>();
			File orderFile = new File(orderFileName + i + ".txt");
			Scanner scan = new Scanner(orderFile);
			String clientId = "" + (int) getPrice(scan.nextLine());
			System.out.println("Reading order for client with id: " + clientId);
			while (scan.hasNext()) {
				String line = scan.nextLine();
				if (totalItems != null
						&& totalItems.containsKey(getItem(line))) {
					totalItems.put(getItem(line),
							totalItems.get(getItem(line)) + 1);
				} else {
					totalItems.put(getItem(line), 1);
				}
				if (orderItems.containsKey(getItem(line))) {
					orderItems.put(getItem(line), orderItems.get(getItem(line))
							+ items.get(getItem(line)));
				} else {
					orderItems.put(getItem(line), items.get(getItem(line)));
				}
			}
			scan.close();
			synchronized (lock) {
				fileWrite.addOrder(clientId, orderItems);
				fileWrite.addTotalItems(totalItems);
				totalItems = new TreeMap<>();
			}
		} catch (IOException e) {

		}
	}

	public void singleThread() {
		try {
			int i = 1;
			TreeMap<String, Integer> totalItems = new TreeMap<>();
			while (i <= orderNum) {
				TreeMap<String, Double> orderItems = new TreeMap<>();
				File orderFile = new File(orderFileName + i + ".txt");
				Scanner scan = new Scanner(orderFile);
				String clientId = "" + (int) getPrice(scan.nextLine());
				System.out.println(
						"Reading order for client with id: " + clientId);
				while (scan.hasNext()) {
					String line = scan.nextLine();
					if (totalItems != null
							&& totalItems.containsKey(getItem(line))) {
						totalItems.put(getItem(line),
								totalItems.get(getItem(line)) + 1);
					} else {
						totalItems.put(getItem(line), 1);
					}
					if (orderItems.containsKey(getItem(line))) {
						orderItems.put(getItem(line),
								orderItems.get(getItem(line))
										+ items.get(getItem(line)));
					} else {
						orderItems.put(getItem(line), items.get(getItem(line)));
					}
				}
				scan.close();
				fileWrite.addOrder(clientId, orderItems);
				fileWrite.addTotalItems(totalItems);
				totalItems = new TreeMap<>();
				i++;
			}
		} catch (IOException e) {

		}
	}

	private HashMap<String, Double> getItemMap() throws FileNotFoundException {
		HashMap<String, Double> items = new HashMap<>();
		File file = new File(fileName);
		Scanner scan = new Scanner(file);
		while (scan.hasNext()) {
			String line = scan.nextLine();
			items.put(getItem(line), getPrice(line));
		}
		scan.close();
		return items;
	}

	private String getItem(String itemData) {
		int index = 0;
		char letter = itemData.charAt(index);
		while (letter != ' ') {
			letter = itemData.charAt(index++);

		}
		return itemData.substring(0, index - 1);
	}

	private double getPrice(String itemData) {
		int index = 0;
		char letter = itemData.charAt(index);
		while (letter != ' ') {
			letter = itemData.charAt(index++);

		}
		return Double.parseDouble(itemData.substring(index));
	}

	public void output() throws IOException {
		fileWrite.finalWrite();
	}
}
