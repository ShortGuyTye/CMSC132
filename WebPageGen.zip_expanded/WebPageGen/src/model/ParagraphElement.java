package model;

import java.util.ArrayList;

/**
 * 
 * Represents a paragraph (&lt;p&gt;) tag. It relies on an ArrayList in order to
 * keep track of the set of Element objects that are part of the paragraph.
 * 
 * @author UMCP
 *
 */
public class ParagraphElement extends TagElement {
	private ArrayList<Element> items;

	public ParagraphElement(String attributes) {
		super("p", true, null, attributes);
		items = new ArrayList<>();
	}

	public void addItem(Element item) {
		items.add(item);
	}

	public String genHTML(int indentation) {
		String content = "";
		for (int i = 0; i < items.size(); i++) {
			content += Utilities.spaces(3);
			content += items.get(i).genHTML(indentation);
			content += "\n";
		}
		return Utilities.spaces(indentation) + super.getStartTag() + "\n" 
		+ content + Utilities.spaces(indentation) + super.getEndTag();
	}
}
