package GUI;

import java.text.NumberFormat;

public class InterestTableCalculations {

	public static String simpleInterest(double principle, double rate, double year) {
		String formattedValue = "Principle: " + principle + ". Rate: " + rate 
				+ "\n" + "Year. Simple Interest Amount \n";
		for (int i = 1; i <= year; i++) {
			formattedValue += i;
			formattedValue += "-->";
			formattedValue += NumberFormat.getCurrencyInstance()
					.format(Double.parseDouble(String.format
							("%.2f", principle + (principle * (rate/100) * i))));
			formattedValue += "\n";
		}
		return formattedValue;
	}
	public static String compoundInterest(double principle, double rate, double year) {
		String formattedValue = "Principle: " + principle + ". Rate: " + rate 
				+ "\n" + "Year. Compound Interest Amount \n";
		for (int i = 1; i <= year; i++) {
			formattedValue += i;
			formattedValue += "-->";
			formattedValue += NumberFormat.getCurrencyInstance()
					.format(Double.parseDouble(String.format
							("%.2f", principle * (Math.pow((1 + rate/100), i)))));
			formattedValue += "\n";
		}
		return formattedValue;
	}
	public static String bothInterest(double principle, double rate, double year) {
		String formattedValue = "Principle: " + principle + ". Rate: " + rate 
				+ "\n" + "Year. Simple Interest Amount. Compound Interest Amount \n";
		for (int i = 1; i <= year; i++) {
			formattedValue += i + "-->";
			formattedValue += NumberFormat.getCurrencyInstance()
					.format(Double.parseDouble(String.format
							("%.2f", principle + (principle * (rate/100) * i))));
			formattedValue += "-->";
			formattedValue += NumberFormat.getCurrencyInstance()
					.format(Double.parseDouble(String.format
							("%.2f", principle * (Math.pow((1 + rate/100), i)))));
			formattedValue += "\n";
		}
		return formattedValue;
	}
}
