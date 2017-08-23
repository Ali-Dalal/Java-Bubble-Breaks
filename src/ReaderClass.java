import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ReaderClass {

	Game g1;
	int sizeX;
	int sizeY;
	int numberOfColors;
	int delay;
	int duration ;
	int x ;
	int y ;
	int scoure;
	int sec;
	int min;
	int hour;
	
	File f = null;
	JFileChooser chooser = new JFileChooser();
	File directory = new File("e:\\");
	boolean find=false;
	
	ReaderClass () {
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Bubble save game(BGsv)", "BGsv");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(directory);
		int returnVal = chooser.showOpenDialog(null);
		
		ObjectInputStream ois = null;
		FileInputStream fis = null;

		try {
			if (returnVal == JFileChooser.APPROVE_OPTION){
				 fis = new FileInputStream(chooser.getSelectedFile().getPath());
				 f=new File(chooser.getSelectedFile().getPath());
				 if(f.getPath().contains(".BGsv")==false) {
					 JOptionPane.showMessageDialog(null, "Invaled file", "Error", JOptionPane.ERROR_MESSAGE);
					 return;
				 }
			}
			else 
				return;
			
			find=true;
			ois = new ObjectInputStream(fis);

			Object obj = null;

			obj = ois.readObject();
			g1 = (Game) obj;
			obj = ois.readObject();
			sizeX = (Integer) obj;
			obj = ois.readObject();
			sizeY = (Integer) obj;
			obj = ois.readObject();
			numberOfColors = (Integer) obj;
			obj = ois.readObject();
			delay = (Integer) obj;
			obj = ois.readObject();
			duration = (Integer) obj;
			obj = ois.readObject();
			x = (Integer) obj;
			obj = ois.readObject();
			y = (Integer) obj;
			obj = ois.readObject();
			scoure = (Integer) obj;
			obj = ois.readObject();
			sec = (Integer) obj;
			obj = ois.readObject();
			min = (Integer) obj;
			obj = ois.readObject();
			hour = (Integer) obj;
		} catch (FileNotFoundException ex){ 
			JOptionPane.showMessageDialog(null, "No file found with this name", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (EOFException ex) { 
			System.out.println("End of file reached.");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		}finally {
		
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

	}
	public boolean chekIfFileExist() {
		if(find==true)
			return true;
		else return false;
	}
}
