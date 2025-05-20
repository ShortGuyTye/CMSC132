package model;

/**
 * Represents an &lt;img&gt; tag. For this project you can assume we will not
 * update any of the attributes associated with this tag.
 * 
 * @author UMCP
 *
 */
public class ImageElement extends TagElement {
	private String imageURL;

	public ImageElement(String imageURL, int width, int height, String alt, 
			String attributes) {
		super("img", false, null, "src=\"" + imageURL + "\" width=\"" + width 
				+ "\" height=\"" + height + "\" alt=\""
				+ alt + "\"" + (attributes == null ? "" : attributes));
		this.imageURL = imageURL;
	}

	public String getImageUrl() {
		return imageURL;
	}
}
