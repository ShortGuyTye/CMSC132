package model;

import java.awt.Color;
import java.util.Random;

/**
 * This class extends GameModel and implements the logic of strategy #1. A
 * description of this strategy can be found in the javadoc for the processCell
 * method.
 * 
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ProcessCellGame extends Game {

	private Random random;
	private int strategy;
	private static int score = 0;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which processing cell strategy to use
	 * (for this project the default will be 1).
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ProcessCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		if (isBlankRow(board.length - 1)) {
			return false;
		} else {
			return true;
		}
	}

	private int edge() {
		for (int i = getMaxRows() - 1; i > 0; i--) {
			if (!isBlankRow(i)) {
				return i;
			}
		}
		return -1;
	}

	public int getScore() {
		return score;
	}

	private boolean isBlankRow(int row) {
		int counter = 0;
		for (int i = 0; i < board[row].length; i++) {
			if (board[row][i] == BoardCell.EMPTY) {
				counter++;
			}
		}
		if (counter == board[row].length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if (isGameOver() == false) {
			for (int row = getMaxRows() - 1; row >= 0; row--) {
				if (row != 0) {
					for (int i = 0; i < board[getMaxRows() - 1].length; i++) {
						board[row][i] = board[row - 1][i];
					}
				}
				if (row == 0) {
					for (int j = 0; j < board[board.length - 1].length; j++) {
						setBoardCell(0, j, BoardCell.getNonEmptyRandomBoardCell(random));
					}
				}
			}
		}
	}

	/**
	 * The default processing associated with this method is that for strategy #1.
	 * 
	 * <br>
	 * <br>
	 * Strategy #1 Description.<br>
	 * <br>
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions). <br>
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br>
	 * <br>
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		// horizontal clearing
		Color color = getBoardCell(rowIndex, colIndex).getColor();
		for (int i = 0; true; i++) {
			if (colIndex + i < board[rowIndex].length && board[rowIndex][colIndex + i].getColor() == color
					&& board[rowIndex][colIndex + i].getColor() != Color.WHITE) {
				board[rowIndex][colIndex + i] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// left horizontal
		for (int i = 1; true; i++) {
			if (colIndex - i >= 0 && board[rowIndex][colIndex - i].getColor() == color
					&& board[rowIndex][colIndex - i].getColor() != Color.WHITE) {
				board[rowIndex][colIndex - i] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// vertical clearing
		for (int i = 1; true; i++) {
			if (rowIndex + i < board.length && board[rowIndex + i][colIndex].getColor() == color
					&& board[rowIndex + i][colIndex].getColor() != Color.WHITE) {
				board[rowIndex + i][colIndex] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// up clearing
		for (int i = 1; true; i++) {
			if (rowIndex - i >= 0 && board[rowIndex - i][colIndex].getColor() == color
					&& board[rowIndex - i][colIndex].getColor() != Color.WHITE) {
				board[rowIndex - i][colIndex] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// diagonal clearing down right
		for (int i = 1; true; i++) {
			if (colIndex + i < board[rowIndex].length && rowIndex + i < board.length
					&& board[rowIndex + i][colIndex + i].getColor() == color
					&& board[rowIndex + i][colIndex + i].getColor() != Color.WHITE) {
				board[rowIndex + i][colIndex + i] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// diagonal down left
		for (int i = 1; true; i++) {
			if (colIndex - i >= 0 && rowIndex + i < board.length
					&& board[rowIndex + i][colIndex - i].getColor() == color
					&& board[rowIndex + i][colIndex - i].getColor() != Color.WHITE) {
				board[rowIndex + i][colIndex - i] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// diagonal up right
		for (int i = 1; true; i++) {
			if (colIndex + i < board[rowIndex].length && rowIndex - i >= 0
					&& board[rowIndex - i][colIndex + i].getColor() == color
					&& board[rowIndex - i][colIndex + i].getColor() != Color.WHITE) {
				board[rowIndex - i][colIndex + i] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// diagonal up left
		for (int i = 1; true; i++) {
			if (colIndex - i >= 0 && rowIndex - i >= 0 && board[rowIndex - i][colIndex - i].getColor() == color
					&& board[rowIndex - i][colIndex - i].getColor() != Color.WHITE) {
				board[rowIndex - i][colIndex - i] = BoardCell.EMPTY;
				score++;
			} else {
				break;
			}
		}
		// clear rows
		for (int i = 0; i < board.length; i++) {
			if (isBlankRow(i)) {
				for (int j = i; j < board.length; j++) {
					if (j + 1 < board.length && j < edge()) {
						for (int k = 0; k < board[getMaxRows() - 1].length; k++) {
							board[j][k] = board[j + 1][k];
						}
					} else if (j + 1 <= board.length && j == edge()) {
						setRowWithColor(j, BoardCell.EMPTY);
					}
				}
				if (i < edge()) {
					i = i - 1;
				}
			}
		}
	}
}