package model;

import java.util.ArrayList;

/**
 * Represents the &lt;ul&gt and the &lt;ol&gt; tags. An ArrayList is used to
 * keep track of the Element objects of the list.
 * 
 * @author UMCP
 *
 */
public class ListElement extends TagElement {
	private ArrayList<Element> items;
	private boolean ordered;

	public ListElement(boolean ordered, String attributes) {
		super("ol", true, null, attributes);
		this.ordered = ordered;
		items = new ArrayList<>();
	}

	public void addItem(Element Item) {
		items.add(Item);
	}

	public String genHTML(int indentation) {
		if (ordered == false) {
			super.setTagName("ul");
		}
		String content = "";
		for (int i = 0; i < items.size(); i++) {
			content += Utilities.spaces(indentation + 3) + "<li>\n";
			content += Utilities.spaces(6) + items.get(i).genHTML(indentation) + "\n";
			content += Utilities.spaces(indentation + 3) + "</li>\n";
		}
		return Utilities.spaces(indentation) + super.getStartTag() + "\n" + content 
				+ Utilities.spaces(indentation)
				+ super.getEndTag();

	}
}
