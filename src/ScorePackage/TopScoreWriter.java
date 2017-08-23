package ScorePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class TopScoreWriter {
	TopScore s;
	public TopScoreWriter(TopScore s) {
		this.s=s;
	
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("topscr.BGts");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(s);
			JOptionPane.showMessageDialog(null, "New high score", "congratulation", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException fex) {
			System.out.println(fex);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (Exception ex1) {
			}
		}
	}
}
