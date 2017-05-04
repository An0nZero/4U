package game.entities;

public class Player {

	private final String name;
	private int highscore;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return highscore;
	}

	public boolean setScore(int score) {
		if (score > highscore) {
			highscore = score;
			return true;
		}
		return false;
	}

}
