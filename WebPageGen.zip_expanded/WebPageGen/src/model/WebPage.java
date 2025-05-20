package model;

import java.util.*;

/**
 * Represents a web page. Web page elements are stored in an ArrayList of
 * Element objects. A title is associated with every page. This class implements
 * the Comparable interface. Pages will be compared based on the title.
 * 
 * @author UMCP
 *
 */
public class WebPage implements Comparable<WebPage> {
	private ArrayList<Element> elements;
	public static boolean enableId;
	private String title;

	public WebPage(String title) {
		this.title = title;
		elements = new ArrayList<>();
	}

	public int addElement(Element element) {
		elements.add(element);
		if (element instanceof TagElement) {
			return ((TagElement) element).getId();
		} else {
			return -1;
		}
	}

	public String getWebPageHTML(int indentation) {
		String content = "";
		for (int i = 0; i < elements.size(); i++) {
			content += Utilities.spaces(3);
			content += elements.get(i).genHTML(indentation);
			content += "\n";
		}
		return "<!doctype html>\n" + "<html>\n"
				+ Utilities.spaces(indentation) + "<head>\n"
				+ Utilities.spaces(2 * indentation) + "<meta charset=\"utf-8\"/>\n"
				+ Utilities.spaces(2 *indentation) + "<title>" + title +"</title>\n"
				+ Utilities.spaces(indentation) + "</head>\n"
				+ Utilities.spaces(indentation) + "<body>\n" + content
				+ Utilities.spaces(indentation) + "</body>\n" + "</html>";
	}

	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}

	public Element findElement(int id) {
		for (Element element : elements) {
			if (element instanceof TagElement) {
				if (((TagElement) element).getId() == id) {
					return element;
				}
			}
		}
		return null;
	}

	public String stats() {
		int list = 0, para = 0, table = 0;
		double tablePercent = 0;
		for (int i = 0; i < elements.size(); i ++) {
			if (elements.get(i) instanceof ListElement) {
				list ++;
			} else if (elements.get(i) instanceof ParagraphElement) {
				para++;
			}
			else if (elements.get(i) instanceof TableElement) {
				table++;
				tablePercent += ((TableElement) elements.get(i)).getTableUtilization();
			}

		}
		return "List Count: " + list + "\n"
				+ "Paragraph Count: " + para + "\n"
				+ "Table Count: " + table + "\n"
				+ "TableElement Utilization: " + (100 * (tablePercent/table));
	}
	public int compareTo(WebPage webPage) {
		return this.title.compareTo(webPage.title);
	}

	public static void enableId(boolean choice) {
		enableId = choice;
	}
}
