package model;

/**
 * Represents the &lt;a&gt; tag.
 * 
 * @author UMCP
 *
 */
public class AnchorElement extends TagElement {
	private String linkText;
	private String url;

	public AnchorElement(String url, String linkText, String attributes) {
		super("a", true, new TextElement(linkText), "href=\"" + url + "\"" + 
	(attributes == null ? "" : attributes));
		this.url = url;
		this.linkText = linkText;
	}

	public String getLinkText() {
		return linkText;
	}

	public String getUrlText() {
		return url;
	}

	public String genHTML​(int indentation) {
		return super.genHTML(indentation);
	}
}
