import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SuppressWarnings("serial")
public class Game implements Serializable {
	 int sizeX;
	 int sizeY;
	 int numberOfColors;
	//Bubble a[][] = new Bubble[size][size];
	Bubble a[][] = null;
	
	Random r = new Random();

	Game(int sizeX,int sizeY,int numberOfColors) {
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.numberOfColors=numberOfColors;
		a=new Bubble[sizeX][sizeY];
	}
	
	public void fill() {
		for (int i = 0; i < sizeX; i++)
			for (int j = 0; j < sizeY; j++)
				//a[i][j] = new Bubble(4, a, i, j);
				a[i][j] = new Bubble(r.nextInt(numberOfColors) + 1, a, i, j);
	}
	
	public ArrayList<Bubble> findSame(int i,int j) {
		ArrayList<Bubble> l1 = new ArrayList<Bubble>();
		l1 = a[i][j].searchColor(i, j);
		return l1;
	}

	public int countSameBubble(int i, int j) {
		return a[i][j].searchColor(i, j).size();
	}

	public void deleteSameBubble(int i, int j) {
		List<Bubble> l1 = new ArrayList<Bubble>();
		l1 = a[i][j].searchColor(i, j);
		for (int k = 0; k < l1.size(); k++) {
			l1.get(k).color = 0;
		}
	}

	public void moveBubbleDown() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				a[i][j].moveDown(i, j);
			}
		}
	}

	public void generateColumn() {
		boolean b = true;
		for (int i = 0; i < sizeX; i++) {
			if (a[i][0].color == 0)
				continue;
			else {
				b = false;
				break;
			}
		}
		if (b == true) {
			for (int i = 0; i < sizeX; i++)
				a[i][0].color = r.nextInt(numberOfColors) + 1;
		}
	}

	public void checkIfColumnsEmpty() {
		int j = 0;
		boolean b = false;
		while (j <sizeY) {
			for (int i = 0; i <sizeX; i++) {
				if (a[i][j].color == 0) {
					b = true;
				} else {
					b = false;
					break;
				}
				if(b==true) {
					
					moveColumnRight();
					generateColumn();
				}
			}
			j++;
		}
	}

	public void moveColumnRight() {
		int j = sizeY - 1;
		boolean b = false;
		try {
			while (j >= 0) {
				for (int i = 0; i < sizeX; i++) {
					if (a[i][j].color == 0) {
						b = true;
					} else {
						b = false;
						break;
					}
				}
				if (b == true) {
					for (int i = 0; i < sizeX; i++) {
						a[i][j] = a[i][j - 1];
						a[i][j - 1] = new Bubble(0, a, i, j);
					}
				}
				j--;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	boolean checkIfGameFinish() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (a[i][j].color == 0)
					continue;
				if(a[i][j].color==11)
					return false;
				if(a[i][j].color==12)
					return false;
				try {
					if (a[i][j].color == a[i + 1][j].color)
						return false;

				} catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (a[i][j].color == a[i - 1][j].color)
						return false;
				} catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (a[i][j].color == a[i][j + 1].color)
						return false;
				} catch (ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (a[i][j].color == a[i][j - 1].color)
						return false;

				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}
		}
		return true;
	}

	public void print() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				System.out.print(a[i][j].color + " ");
			}
			System.out.println();
		}
		System.out.println("\n==========================\n");
	}
}