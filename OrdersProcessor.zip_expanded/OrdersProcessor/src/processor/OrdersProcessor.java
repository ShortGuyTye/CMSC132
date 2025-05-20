package processor;

import java.io.*;
import java.util.*;

public class OrdersProcessor {

	public static void main(String args[])
			throws IOException, InterruptedException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter item’s data file name: ");
		String fileName = scan.nextLine();
		System.out.print(
				"Enter ’y’ for multiple threads, any other character otherwise: ");
		boolean threads = scan.nextLine().equals("y");
		System.out.print("Enter number of orders to process: ");
		int orderNum = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter order’s base filename: ");
		String orderFileName = scan.nextLine();
		System.out.print("Enter result’s filename: ");
		String resultFileName = scan.nextLine();
		long startTime = System.currentTimeMillis();
		ArrayList<Integer> orderOfOrders = new ArrayList<>();
		int num = 1;
		while (num <= orderNum) {
			orderOfOrders.add(num);
			num++;
		}
		Process pro = new Process(fileName, orderFileName, resultFileName,
				orderNum, threads, orderOfOrders);
		if (threads) {
			Thread[] allThreads = new Thread[orderNum];

			for (int i = 0; i < allThreads.length; i++) {
				allThreads[i] = new Thread(pro);
			}
			for (int i = 0; i < allThreads.length; i++) {
				if (i < orderNum) {
					allThreads[i].start();
				}
			}
			for (int i = 0; i < allThreads.length; i++) {
				allThreads[i].join();
			}
			pro.output();
			scan.close();
		} else {
			pro.singleThread();
			pro.output();
			scan.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out
				.println("Results can be found in the file: " + resultFileName);
	}
}