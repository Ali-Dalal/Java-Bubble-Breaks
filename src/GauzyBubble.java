import java.util.ArrayList;

@SuppressWarnings("serial")
class GauzyBubble extends Bubble {

	public GauzyBubble(int color, Bubble[][] b, int i, int j) {
		super(color, b, i, j);
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
			if ((b[i][j].color == b[i + 1][j].color || b[i+1][j].color == 11)
					&& !l1.contains(b[i + 1][j])) {
				searchColor(i + 1, j, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if ((b[i][j].color == b[i][j + 1].color || b[i][j+1].color == 11)
					&& !l1.contains(b[i][j + 1])) {
				searchColor(i, j + 1, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if ((b[i][j].color == b[i - 1][j].color || b[i-1][j].color == 11)
					&& !l1.contains(b[i - 1][j])) {
				searchColor(i - 1, j, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if ((b[i][j].color == b[i][j - 1].color || b[i][j-1].color == 11)
					&& !l1.contains(b[i][j - 1])) {
				searchColor(i, j - 1, l1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		if (l1.size() == 1)
			l1.clear();
		return l1;

	}
}

