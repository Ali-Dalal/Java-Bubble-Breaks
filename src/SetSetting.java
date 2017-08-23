import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class SetSetting extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField txt1 = new JTextField(4);
	JTextField txt2 = new JTextField(4);
	JTextField txt3 = new JTextField(4);
	JTextField txt4 = new JTextField(4);
	JTextField txt5 = new JTextField(4);
	JTextField txt6 = new JTextField(4);
	JTextField txt7 = new JTextField(4);

	JLabel lbl1 = new JLabel("Size on X: ");
	JLabel lbl6 = new JLabel("Size on Y: ");
	JLabel lbl2 = new JLabel("Colors:");
	JLabel lbl3 = new JLabel("Dimension x:");
	JLabel lbl4 = new JLabel("Dimension y:");
	JLabel lbl5 = new JLabel("delay in second:");
	JLabel lbl7 = new JLabel("duration in second:");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();

	JButton btn1 = new JButton("ok");
	JButton btn2 = new JButton("cancel");

	Timer t = new Timer(100, this);

	SetSetting() {
		t.start();
		setLayout(new BorderLayout());
		setSize(1000, 100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		txt1.addKeyListener(new NumbersOnly());
		txt6.addKeyListener(new NumbersOnly());
		txt2.addKeyListener(new NumbersOnly());
		txt3.addKeyListener(new NumbersOnly());
		txt4.addKeyListener(new NumbersOnly());
		txt5.addKeyListener(new NumbersOnly());
		txt7.addKeyListener(new NumbersOnly());
		txt1.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt1.getText()) > 100) {
						JOptionPane.showMessageDialog(null,
								"Maximum size is 100", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {

				}

			}
		});
		txt6.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt1.getText()) > 100) {
						JOptionPane.showMessageDialog(null,
								"Maximum size is 100", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {

				}

			}
		});
		txt2.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt2.getText()) > 10) {
						JOptionPane.showMessageDialog(null,
								"Maximum colors are 10", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {

				}

			}
		});
		txt3.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt3.getText()) > 1300) {
						JOptionPane.showMessageDialog(null,
								"Maximum dimension on x  is 1300", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {

				}
			}
		});
		txt4.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt4.getText()) > 700) {
						JOptionPane.showMessageDialog(null,
								"Maximum dimension on y is 700", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {

				}

			}
		});
		txt5.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt5.getText()) > 100) {
						JOptionPane.showMessageDialog(null,
								"Maximum timer is 100 second", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
					}
				} catch (Exception e1) {

				}
			}
		});
		txt7.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					if (Integer.parseInt(txt5.getText()) > 100) {
						JOptionPane.showMessageDialog(null,
								"Maximum timer is 100 second", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
					}
				} catch (Exception e1) {

				}
			}
		});

		btn1.setEnabled(false);
		btn1.addActionListener(new TakeValues());
		pnl1.add(lbl1);
		pnl1.add(txt1);
		pnl1.add(lbl6);
		pnl1.add(txt6);
		pnl1.add(lbl2);
		pnl1.add(txt2);
		pnl1.add(lbl3);
		pnl1.add(txt3);
		pnl1.add(lbl4);
		pnl1.add(txt4);
		pnl1.add(lbl5);
		pnl1.add(txt5);
		pnl1.add(lbl7);
		pnl1.add(txt7);
		add(pnl1, BorderLayout.NORTH);
		pnl2.add(btn2);
		pnl2.add(btn1);
		add(pnl2, BorderLayout.CENTER);
		setTitle("Setup setting");
		btn2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainMenu();
			}
		});
	}

	class NumbersOnly implements KeyListener {

		public void keyTyped(KeyEvent ke) {
			char c = ke.getKeyChar();
			if ((!(Character.isDigit(c)))) {
				ke.consume();
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		}
	}

	class TakeValues implements ActionListener {
		
		int sizeX;;
		int sizeY;
		int colors;
		int x;
		int y;
		int delay; 
		int duration;
		

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			sizeX = Integer.parseInt(txt1.getText());
			sizeY = Integer.parseInt(txt6.getText());
			colors = Integer.parseInt(txt2.getText());
			x = Integer.parseInt(txt3.getText());
			y = Integer.parseInt(txt4.getText());
			delay = Integer.parseInt(txt5.getText());
			duration = Integer.parseInt(txt7.getText());
			
			ObjectOutputStream oos = null;
			File f = new File("Setting.BGsitting");
			
			try {
				FileOutputStream fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(sizeX);
				oos.writeObject(sizeY);
				oos.writeObject(colors);
				oos.writeObject(delay);
				oos.writeObject(duration);
				oos.writeObject(x);
				oos.writeObject(y);
				JOptionPane.showMessageDialog(null, "settup done sucessfully",
						"Done", JOptionPane.INFORMATION_MESSAGE);
				new GameFram(sizeX, sizeY, colors, x, y, delay, duration);

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
					System.out.println(ex1);
				}
			}
		}

	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (Integer.parseInt(txt1.getText()) > 0
					&& Integer.parseInt(txt1.getText()) <= 100
					&& Integer.parseInt(txt6.getText()) > 0
					&& Integer.parseInt(txt6.getText()) <= 100
					&& Integer.parseInt(txt2.getText()) > 0
					&& Integer.parseInt(txt2.getText()) <= 10
					&& Integer.parseInt(txt3.getText()) > 0
					&& Integer.parseInt(txt3.getText()) <= 1300
					&& Integer.parseInt(txt4.getText()) > 0
					&& Integer.parseInt(txt4.getText()) <= 700
					&& Integer.parseInt(txt5.getText()) > 0
					&& Integer.parseInt(txt5.getText()) <= 100
					&& Integer.parseInt(txt7.getText()) > 0
					&& Integer.parseInt(txt7.getText()) <= 100) {
				btn1.setEnabled(true);
			} else
				btn1.setEnabled(false);
		} catch (Exception e1) {
		}
		if (txt1.getText().equals("") || txt2.getText().equals("")
				|| txt3.getText().equals("") || txt4.getText().equals("")
				|| txt5.getText().equals("") || txt6.getText().equals("")
				|| txt7.getText().equals("")) {
			btn1.setEnabled(false);
		}
	}
}
