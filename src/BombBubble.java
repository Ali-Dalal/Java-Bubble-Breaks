import java.util.ArrayList;

@SuppressWarnings("serial")
class BombBubble extends Bubble {

	public BombBubble(int color, Bubble[][] b, int i, int j) {
		super(color, b, i, j);
	}
	
	public ArrayList<Bubble> searchColor(int i, int j) {
		ArrayList<Bubble> l1 = new ArrayList<Bubble>();
		l1.add(b[i][j]);
		try {
			if(b[i+1][j].color!=0)
				l1.add(b[i + 1][j]);

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if(b[i-1][j].color!=0)
				l1.add(b[i - 1][j]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if(b[i][j+1].color!=0)
				l1.add(b[i][j + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if(b[i][j-1].color!=0)
				l1.add(b[i][j - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if(b[i+1][j+1].color!=0)
				l1.add(b[i + 1][j + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if(b[i-1][j-1].color!=0)
				l1.add(b[i - 1][j - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			if(b[i+1][j-1].color!=0)
				l1.add(b[i + 1][j - 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {if(b[i-1][j+1].color!=0)
				l1.add(b[i - 1][j + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
		return l1;
	}
}