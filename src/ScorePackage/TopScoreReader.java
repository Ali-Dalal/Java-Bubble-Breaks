package ScorePackage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class TopScoreReader {
	public TopScore returnScore() {
		TopScore tmp = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		File f = new File("topscr.BGts");
		if(f.exists()==false)
			try {
				f.createNewFile();
				TopScore ts = new TopScore();
				ts.initiali();
				FileOutputStream fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(ts);
				oos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		try {
			FileInputStream fis = new FileInputStream("topscr.BGts");
			ois = new ObjectInputStream(fis);
			Object obj = null;
			obj = ois.readObject();
			tmp=(TopScore)obj;
			
		} catch (FileNotFoundException ex){ 
			JOptionPane.showMessageDialog(null, "No game played", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (EOFException ex) { 
			System.out.println("End of file reached.");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		}finally {
		
			try {
				if(ois!=null)
					ois.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		return tmp;
	}
}
