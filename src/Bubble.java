import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Bubble implements Serializable {
	int color;
	int i;
	int j;
	Bubble[][] b;

	public Bubble(int color, Bubble[][] b, int i, int j) {
		this.color = color;
		this.b = b;
		this.i = i;
		this.j = j;
	}

	ArrayList<Bubble> searchColor(int i, int j) {
		ArrayList<Bubble> l1 = new ArrayList<Bubble>();
		return searchColor(i, j, l1);
	}

	ArrayList<Bubble> searchColor(int i, int j, ArrayList<Bubble> l1) {
		try {
			l1.add(b[i][j]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {
			if ((b[i][j].color == b[i + 1][j].color || b[i + 1][j].color==11) && !l1.contains(b[i + 1][j])) {
				b[i + 1][j].color=b[i][j].color;
				searchColor(i + 1, j, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if ((b[i][j].color == b[i][j + 1].color||b[i][j + 1].color==11) && !l1.contains(b[i][j + 1])) {
				b[i][j +1].color=b[i][j].color;
				searchColor(i, j + 1, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if ((b[i][j].color == b[i - 1][j].color ||b[i-1][j].color==11) && !l1.contains(b[i - 1][j])) {
				b[i - 1][j].color=b[i][j].color;
				searchColor(i - 1, j, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if ((b[i][j].color == b[i][j - 1].color ||b[i][j - 1].color==11) && !l1.contains(b[i][j - 1])) {
				b[i][j-1].color=b[i][j].color;
				searchColor(i, j - 1, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		if (l1.size() == 1)
			l1.clear();
		return l1;

	}

	public void moveDown(int i, int j) {
		try {
			if (b[i + 1][j].color == 0) {
				for (int k = i; k >= 0; k--) {
					b[k + 1][j] = b[k][j];
					b[k][j] = new Bubble(0, b, i, j);
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}
}
