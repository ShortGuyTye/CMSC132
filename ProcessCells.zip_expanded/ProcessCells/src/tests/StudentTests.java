package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.Game;
import model.ProcessCellGame;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	
	/* Remove the following test and add your tests */
	@Test
	public void test1() {
		int maxRows = 5, maxCols = 5, strategy = 1;
		Game ccGame = new ProcessCellGame(maxRows, maxCols, new Random(1L), strategy);
		//ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.setBoardCell(0, 0, BoardCell.RED);
		ccGame.setBoardCell(1, 1, BoardCell.RED);
		ccGame.setBoardCell(2, 2, BoardCell.RED);
		ccGame.setBoardCell(3, 3, BoardCell.RED);
		ccGame.setBoardCell(3, 2, BoardCell.BLUE);
		//ccGame.setColWithColor(2, BoardCell.RED);
		ccGame.setRowWithColor(4, BoardCell.BLUE);
		ccGame.processCell(0 ,0);
		ccGame.processCell(1 ,2);
		String answer = getBoardStr(ccGame);
		System.out.println(answer);
	}
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}

