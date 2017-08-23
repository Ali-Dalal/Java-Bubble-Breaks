import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ScorePackage.TopScore;
import ScorePackage.TopScoreReader;


public class MainMenu extends JFrame {

	
	private static final long serialVersionUID = 1;
	
	JButton easyBtn = new JButton("Easy");
	JButton mediumBtn = new JButton("medium");
	JButton hardBtn = new JButton("hard");
	JButton customBtn = new JButton("custom ");
	JButton loadbtn = new JButton("load saved game");
	JButton defult = new JButton("deafult setting");
	JButton Setup = new JButton("Setup new setting");
	JButton topscr = new JButton("Top scores");
	JLabel lbl1 =new JLabel("Bubble Game");
	JLabel lbl =new JLabel("Select difficulties");
	
	JPanel pnl = new JPanel();
	
	public MainMenu() {
		pnl.setLayout(new FlowLayout());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		lbl1.setOpaque(true);
		lbl1.setBackground(Color.gray);
		lbl1.setForeground(Color.white);
		pnl.add(lbl1);
		
		lbl.setOpaque(true);
		lbl.setBackground(Color.gray);
		lbl.setForeground(Color.white);
		pnl.add(lbl);
		
		easyBtn.setFocusable(false);
		easyBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFram(8,8,5,500,400,2,4);
			}
		});
		pnl.add(easyBtn);
		
		mediumBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFram(20,18,8,800,600,4,5);
				
			}
		});
		mediumBtn.setFocusable(false);
		pnl.add(mediumBtn);
		
		hardBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFram(30,25,10,1000,700,5,3);
				
			}
		});
		hardBtn.setFocusable(false);
		pnl.add(hardBtn);
		
		customBtn.setFocusable(false);
		customBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CustomGame();				
			}
		});
		pnl.add(customBtn);
		
		
		loadbtn.setFocusable(false);
		loadbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GameFram(0, 0 ,0 ,0 ,0 , 0, 0);
			}
		});
		pnl.add(loadbtn);
		
		defult.setFocusable(false);
		defult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new DefaultSetting();
			}
		});
		pnl.add(defult);
		Setup.setFocusable(false);
		Setup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SetSetting();
				
			}
		});
		pnl.add(Setup);
		topscr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TopScoreReader tsr = new TopScoreReader();
				TopScore ts = new TopScore();
				ts=tsr.returnScore();
				ts.viewScores();
				
			}
		});
		topscr.setFocusable(false);
		pnl.add(topscr);
		
		
		add(pnl);
		setSize(150,330);
		setLocation(610, 100);
		pnl.setBackground(Color.GRAY);
	}
}
