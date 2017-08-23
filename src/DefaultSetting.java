import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;


public class DefaultSetting {
	

	int sizeX;
	int sizeY;
	int numberOfColors;
	int delay;
	int duration ;
	int x ;
	int y ;
	File setting = new File("Setting.BGsitting");
	
	public DefaultSetting() {
		
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		
		if(setting.exists()==false) {
			int p =JOptionPane.showConfirmDialog(null, "No setting setup\nSetup now ?", "Attention", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(p==JOptionPane.YES_OPTION) {
				new SetSetting();
				return;
			}
			else {
				new MainMenu();
				return;
			}
		}
		
		try {
			 fis = new FileInputStream(setting);
			 
			 ois = new ObjectInputStream(fis);

				Object obj = null;

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
				
				new GameFram(sizeX, sizeY, numberOfColors, x, y, delay, duration);

		} catch (FileNotFoundException ex){ 
			JOptionPane.showMessageDialog(null, "No file setting set", "Error", JOptionPane.ERROR_MESSAGE);
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
}
