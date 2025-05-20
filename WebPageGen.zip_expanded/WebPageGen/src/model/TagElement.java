package model;

/**
 * 
 * Represents an HTML tag element (<&lt;p&gt;, &lt;ul&gt;, etc.). Each tag has
 * an id (ids start at 1). By default the start tag will have an id (e.g.,
 * <&lt;p id="a1"&gt;&lt;/p&gt;) when the HTML for the tag is generated. This
 * can be disabled by using enableId.
 * 
 * @author UMCP
 *
 */
public class TagElement implements Element {

	private static int id = 0;
	private String tagName, attributes;
	private boolean endTag;
	private static boolean enableId;
	private Element content;
	private int thisId;

	public TagElement(String tagName, boolean endTag, Element content, String attributes) {
		id++;
		this.tagName = tagName;
		this.attributes = attributes;
		this.endTag = endTag;
		this.content = content;
		thisId = id;
	}

	public int getId() {
		return thisId;
	}

	public String getStringId() {
		return " id=\"" + tagName + thisId + "\"";
	}

	public String getStartTag() {
		if (enableId) {
			return "<" + tagName + getStringId() + (attributes != null ? " " 
		+ attributes : "") + ">";
		} else {
			return "<" + tagName + (attributes != null ? " " + attributes : "") 
					+ ">";
		}
	}

	public String getEndTag() {
		if (endTag) {
			return "</" + tagName + '>';
		} else {
			return "";
		}
	}

	public void setAttributes​(String attributes) {
		this.attributes = attributes;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public static void resetIds() {
		id = 0;
	}

	public static void enableId(boolean choice) {
		enableId = choice;
	}

	public String genHTML(int indentation) {
		return Utilities.spaces(indentation) + getStartTag() 
		+ (endTag ? content.genHTML(0) + getEndTag() : "");
	}

}