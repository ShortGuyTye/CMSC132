package model;

/**
 * Represents the &lt;table&gt tag. A two dimensional array is used to keep
 * track of the Element objects of table.
 * 
 * @author UMCP
 *
 */
public class TableElement extends TagElement {
	private Element[][] items;
	private int rows, col;

	public TableElement(int rows, int col, String attributes) {
		super("table", true, null, attributes);
		items = new Element[rows][col];
		this.rows = rows;
		this.col = col;
	}

	public void addItem(int rowIndex, int colIndex, Element item) {
		items[rowIndex][colIndex] = item;
	}

	public double getTableUtilization() {
		double counter = 0;
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				if (items[i][j] != null) {
					counter = counter + 1;
				}
			}
		}
		return (counter / (rows * col));
	}

	public String genHTML(int indentation) {
		String content = "";
		for (int i = 0; i < items.length; i++) {
			content += "\n" + Utilities.spaces(3 + indentation) + "<tr>";
			for (int j = 0; j < items[i].length; j++) {
				content += "<td>";
				content += (items[i][j] != null ? items[i][j].genHTML(0) : "");
				content += "</td>";
			}
			content += "</tr>";
		}
		return Utilities.spaces(indentation) + getStartTag() + content + "\n" 
		+ Utilities.spaces(indentation) + getEndTag();
	}
}
