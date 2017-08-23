package ScorePackage;

import java.io.Serializable;
import java.sql.Time;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TopScore implements Serializable {

	Score[] array = new Score[10];

	public void sort() {
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 10; j++) {
				if (array[i].score < array[j].score) {
					Score tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
				}
			}
		}
	}

	public boolean addScore(Score s) {
		int index = 0;
		boolean b = false;
		for (int i = 0; i < 10; i++) {
			if (array[i].score == 0) {
				array[i] = s;
				sort();
				return true;
			} else if (array[i].score < s.score) {
				b = true;
				index = i;
			}
		}
		if (b == true) {
			array[index] = s;
			sort();
			return true;
		}
		return false;
	}

	public void initiali() {
		@SuppressWarnings("deprecation")
		Time temp = new Time(0, 0, 0);
		for (int i = 0; i < array.length; i++) {
			array[i] = new Score(0, "", temp);
		}
	}

	@SuppressWarnings("deprecation")
	public void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.println("Name:" + array[i].name + "    Score:"
					+ array[i].score + "    Time:" + array[i].t.getHours()
					+ ":" + array[i].t.getMinutes() + ":"
					+ array[i].t.getSeconds());
		}
	}

	@SuppressWarnings("deprecation")
	public void viewScores() {
		String s = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i].score != 0)
				s += "Name: " + array[i].name + "\nScore: " + array[i].score
						+ "\nTime: " + array[i].t.getHours() + ":"
						+ array[i].t.getMinutes() + ":"
						+ array[i].t.getSeconds()
						+ "\n-----------------------------------------\n";
		}
		JOptionPane.showMessageDialog(null, s, "Top Scores",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
