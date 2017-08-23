package ScorePackage;

import java.io.Serializable;
import java.sql.Time;

@SuppressWarnings("serial")
public class Score implements Serializable{
	int score;
	String name;
	Time t;

	public Score(int score, String name, Time t) {
		this.score = score;
		this.name = name;
		this.t = t;
	}
}
