package tests;

import org.junit.Test;

import game.entities.Board;
import game.entities.Bot;
import game.exceptions.ColumnFullException;

public class TestBot {

	@Test
	public void test() {

		Board b = new Board();
		Bot bot = new Bot("bot", b);

		assert bot.getName() == "bot";

		int play = bot.nextPlay();

		assert play > -1;

		try {
			bot.doPlay(play);
			assert true;
		} catch (ColumnFullException e) {
			assert false;
		}

		assert b.lastPiece().getCol() == play;
		assert b.lastPiece().getOwner() == bot;

	}

}
