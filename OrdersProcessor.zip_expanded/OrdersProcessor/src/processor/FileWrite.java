package processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class FileWrite {
	private BufferedWriter bufferedWriter;
	HashMap<String, Double> items;
	TreeMap<String, Integer> totalItems = new TreeMap<>();;
	TreeMap<String, TreeMap<String, Double>> orders = new TreeMap<>();

	public FileWrite(String resultFileName, HashMap<String, Double> items)
			throws IOException {
		this.items = items;
		bufferedWriter = new BufferedWriter(new FileWriter(resultFileName));
	}

	public void finalWrite() throws IOException {
		Set<String> keys = orders.keySet();
		for (String key : keys) {
			fileWriter(key, orders.get(key));
		}
		summaryWrite();
	}

	private void fileWriter(String clientId, TreeMap<String, Double> orderItems)
			throws IOException {
		String clientLine = "----- Order details for client with Id: "
				+ clientId + " -----\n";
		bufferedWriter.write(clientLine);

		Set<String> keys = orderItems.keySet();
		for (String key : keys) {
			String line = "Item's name: " + key + ", Cost per item: "
					+ NumberFormat.getCurrencyInstance().format(items.get(key))
					+ ", Quantity: "
					+ (int) (orderItems.get(key) / items.get(key)) + ", Cost: "
					+ NumberFormat.getCurrencyInstance()
							.format(orderItems.get(key))
					+ "\n";
			bufferedWriter.write(line);
		}
		double sum = 0;
		for (String key : keys) {
			sum += orderItems.get(key);
		}

		String total = "Order Total: "
				+ NumberFormat.getCurrencyInstance().format(sum) + "\n";
		bufferedWriter.write(total);

	}

	public void summaryWrite() throws IOException {
		double sum = 0;
		bufferedWriter.write("***** Summary of all orders *****\n");
		Set<String> keys = totalItems.keySet();
		for (String key : keys) {
			String line = "Summary - Item's name: " + key + ", Cost per item: "
					+ NumberFormat.getCurrencyInstance().format(items.get(key))
					+ ", Number sold: " + totalItems.get(key)
					+ ", Item's Total: " + NumberFormat.getCurrencyInstance()
							.format(totalItems.get(key) * items.get(key))
					+ "\n";
			bufferedWriter.write(line);
			sum += totalItems.get(key) * items.get(key);
		}

		String total = "Summary Grand Total: "
				+ NumberFormat.getCurrencyInstance().format(sum) + "\n";
		bufferedWriter.write(total);
		bufferedWriter.close();

	}

	public void addTotalItems(TreeMap<String, Integer> newTotalItems) {
		Set<String> keys = newTotalItems.keySet();
		for (String key : keys) {
			if (totalItems.containsKey(key)) {
				totalItems.put(key,
						totalItems.get(key) + newTotalItems.get(key));
			} else {
				totalItems.put(key, newTotalItems.get(key));
			}
		}

	}

	public void addOrder(String clientID, TreeMap<String, Double> orderItems) {
		orders.put(clientID, orderItems);
	}

}
