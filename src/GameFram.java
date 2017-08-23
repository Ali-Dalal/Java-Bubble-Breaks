
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import ScorePackage.*;

public class GameFram extends JFrame {

    private static final long serialVersionUID = 1;
    Game g1 = null;
    Bubble[][] tmp = null;
    int numberOfColors = 0, sizeX, sizeY, x, y;
    int scoure = 0;
    int tmpscoure = 0;

    // GUI components
    JButton[][] btn = null;
    JPanel pnl = new JPanel();
    JPanel pnl2 = new JPanel();
    JPanel pnl3 = new JPanel();
    JLabel scorelbl = new JLabel("0");
    JLabel score = new JLabel("Score:");
    JButton NewGamebtn = new JButton("New game");
    JButton Undobtn = new JButton("Undo");
    JLabel counterLbl = null;
    int sec, min, hour;
    Timer counterTimer = new Timer(1000, new CounterAction());

    // special bubbles items
    Timer t1 = null;
    Timer t2 = null;
    Timer t3 = new Timer(500, new CheckIfGameFinish());
    Random r = new Random();
    int delay;
    int duration;
    int tmpi = 0, tmpj = 0;
    int tmpColor;

    // Menu items :
    JMenuBar mbar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenu menu2 = new JMenu("Help");
    JMenuItem item1 = new JMenuItem("exit");
    JMenuItem item2 = new JMenuItem("save game");
    JMenuItem item3 = new JMenuItem("Main menu");
    JMenuItem item4 = new JMenuItem("top scores");
    JMenuItem item5 = new JMenuItem("About us");
    JMenuItem item6 = new JMenuItem("How to play");

    GameFram(int sizeX, int sizeY, int numberOfColors, int x, int y, int delay, int duration) {
        if (sizeX == 0 && sizeY == 0 && numberOfColors == 0 && x == 0 && y == 0
                && delay == 0 && duration == 0) {
            ReaderClass rc = new ReaderClass();
            if (rc.chekIfFileExist() == true) {
                g1 = rc.g1;
                this.sizeX = rc.sizeX;
                this.sizeY = rc.sizeY;
                this.numberOfColors = rc.numberOfColors;
                this.delay = rc.delay;
                this.duration = rc.duration;
                this.x = rc.x;
                this.y = rc.y;
                this.scoure = rc.scoure;
                this.sec = rc.sec;
                this.min = rc.min;
                this.hour = rc.hour;
            } else {
                new MainMenu();
                return;
            }
        } else {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.x = x;
            this.y = y;
            this.numberOfColors = numberOfColors;
            this.delay = delay;
            this.duration = duration;
            g1 = new Game(sizeX, sizeY, numberOfColors);
            g1.fill();
        }
        counterLbl = new JLabel(String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec));
        t1 = new Timer((this.delay * 1000), new SpecialBubbleAction());
        t2 = new Timer((this.duration * 1000), new RemoveEffects());
        tmp = new Bubble[this.sizeX][this.sizeY];
        btn = new JButton[this.sizeX][this.sizeY];
        this.setJMenuBar(mbar);
        item1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
        item2.addActionListener(new SaveAction());
        item3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MainMenu();
            }
        });
        item4.addActionListener(new LoadTopScores());
        item5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "This game designed by Ali Dalal and Soha agned";
                JOptionPane.showMessageDialog(null, s, "About us", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        item6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "Strike bubbles with same color to collect points.\n"
                        + "Strike biggest set of same color to collect more score.";
                JOptionPane.showMessageDialog(null, s, "How to play", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        mbar.add(menu);
        mbar.add(menu2);
        menu2.add(item5);
        menu2.add(item6);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.add(item1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pnl.setLayout(new GridLayout(this.sizeX, this.sizeY));
        pnl.setBackground(Color.LIGHT_GRAY);
        this.add(pnl, BorderLayout.CENTER);
        generateButtons();
        coloring();
        generateActions();
        t1.start();
        //t2.start();
        t3.start();
        counterTimer.start();
        insertButtonsIntoPnl();
        pnl2.setBackground(Color.LIGHT_GRAY);
        pnl3.setBackground(Color.LIGHT_GRAY);
        score.setOpaque(true);
        score.setBackground(Color.LIGHT_GRAY);
        score.setForeground(Color.red);
        NewGamebtn.setFocusable(false);
        NewGamebtn.addActionListener(new NewGameAction());
        Undobtn.setFocusable(false);
        Undobtn.setEnabled(false);
        Undobtn.addActionListener(new UndoBtnAction());
        pnl3.add(Undobtn);
        pnl3.add(NewGamebtn);
        pnl2.add(score);
        scorelbl.setText(String.valueOf(scoure));
        pnl2.add(scorelbl);
        pnl2.add(counterLbl, FlowLayout.LEFT);

        add(pnl2, BorderLayout.SOUTH);
        add(pnl3, BorderLayout.NORTH);
        setTitle("Bubble game");
        setLocation(10, 10);
        setVisible(true);
        setResizable(false);
        setSize(this.x, this.y);
        //g1.print();
    }

    public void fillTmpArray() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                tmp[i][j] = new Bubble(g1.a[i][j].color, tmp, i, j);
            }
        }
        tmpscoure = scoure;
    }

    public void returnToOrignColor() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (g1.a[i][j].color == 12 || g1.a[i][j].color == 11) {
                    g1.a[i][j] = new Bubble(tmpColor, g1.a, i, j);
                }
            }
        }
    }

    public void disableAllButtons() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                btn[i][j].setEnabled(false);
            }
        }
    }

    public void coloring() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (g1.a[i][j].color == 0) {
                    btn[i][j].setBorder(BorderFactory
                            .createLoweredBevelBorder());
                    // btn[i][j].setVisible(false);
                    btn[i][j].setBackground(Color.gray);
                    btn[i][j].setEnabled(false);
                }
                if (g1.a[i][j].color == 1) {
                    // btn[i][j].setVisible(true);
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setBackground(Color.red);
                }
                if (g1.a[i][j].color == 2) {
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBackground(Color.blue);
                }
                if (g1.a[i][j].color == 3) {
                    btn[i][j].setEnabled(true);
                    btn[i][j].setVisible(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setBackground(Color.yellow);
                    //btn[i][j].setIcon(yellow);
                    //btn[i][j].repaint();
                }
                if (g1.a[i][j].color == 4) {
                    btn[i][j].setEnabled(true);
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setBackground(Color.green);
                }
                if (g1.a[i][j].color == 5) {
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBackground(Color.cyan);
                }
                if (g1.a[i][j].color == 6) {
                    btn[i][j].setEnabled(true);
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setBackground(new Color(172, 90, 156));
                }
                if (g1.a[i][j].color == 7) {
                    btn[i][j].setEnabled(true);
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setBackground(new Color(54, 205, 193));
                }
                if (g1.a[i][j].color == 8) {
                    btn[i][j].setEnabled(true);
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    btn[i][j].setBackground(Color.PINK);
                }
                if (g1.a[i][j].color == 9) {
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBackground(Color.orange);
                }
                if (g1.a[i][j].color == 10) {
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBackground(new Color(255, 128, 128));
                }
                if (g1.a[i][j].color == 11) {
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBackground(new Color(213, 219, 245));
                }
                if (g1.a[i][j].color == 12) {
                    btn[i][j].setEnabled(true);
                    btn[i][j].setBorder(BorderFactory
                            .createBevelBorder(BevelBorder.RAISED));
                    // btn[i][j].setVisible(true);
                    btn[i][j].setBackground(Color.black);
                    // btn[i][j].setBackground(new Color(130,47,49));
                }

            }
        }
    }

    public void insertButtonsIntoPnl() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                pnl.add(btn[i][j]);

            }
        }
    }

    public void generateActions() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                btn[i][j].addActionListener(new BtnAction(i, j));
            }
        }
    }

    public void generateButtons() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                btn[i][j] = new JButton();
            }
        }
    }

    class BtnAction implements ActionListener {

        int i;
        int j;

        public BtnAction(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void actionPerformed(ActionEvent e) {
            int tmp = g1.countSameBubble(i, j);
            if (tmp > 0) {
                Undobtn.setEnabled(true);
                fillTmpArray();
            }
            scoure += tmp * (tmp - 1);
            scorelbl.setText(Integer.toString(scoure));
            g1.deleteSameBubble(i, j);
            g1.checkIfColumnsEmpty();
            g1.moveBubbleDown();
            coloring();
        }
    }

    class SpecialBubbleAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            while (true) {
                tmpi = r.nextInt(sizeX);
                tmpj = r.nextInt(sizeY);
                tmpColor = g1.a[tmpi][tmpj].color;
                if (tmpColor != 0) {
                    break;
                }
            }
            int randomnumber = r.nextInt(2) + 1;
            if (randomnumber == 1) {
                g1.a[tmpi][tmpj] = new BombBubble(12, g1.a, tmpi, tmpj);
            } else {
                g1.a[tmpi][tmpj] = new GauzyBubble(11, g1.a, tmpi, tmpj);
            }
            coloring();
            // g1.print();
            t1.stop();
            t2.start();

        }
    }

    class CheckIfGameFinish implements ActionListener {

        @SuppressWarnings({"deprecation", "unused"})
        public void actionPerformed(ActionEvent e) {
            if (g1.checkIfGameFinish() == true) {
                int p;
                t1.stop();
                t2.stop();
                t3.stop();
                counterTimer.stop();
                disableAllButtons();
                String name;
                name = JOptionPane.showInputDialog(null, "Enter your name",
                        "new score", JOptionPane.INFORMATION_MESSAGE);
                if (name == null || name.equals("")) {
                    name = "No name";
                }
                item2.setEnabled(false);
                Undobtn.setEnabled(false);
                TopScoreReader tsr = new TopScoreReader();
                TopScore tmp = tsr.returnScore();
                Time t = new Time(hour, min, sec);
                Score scr = new Score(scoure, name, t);
                if (tmp.addScore(scr) == true) {
                    TopScoreWriter tsw = new TopScoreWriter(tmp);
                }
                Object[] options = {"Yes", "No", "change difficulties"};
                p = JOptionPane.showOptionDialog(
                        null,
                        "Time: " + String.format("%02d", hour) + ":"
                        + String.format("%02d", min) + ":"
                        + String.format("%02d", sec)
                        + "\nYour score: " + scoure
                        + "\nStart new game ?", "Ops",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (p == 0) {
                    setVisible(false);
                    new GameFram(sizeX, sizeY, numberOfColors, x, y, delay, duration);
                } else if (p == 2) {
                    setVisible(false);
                    new MainMenu();
                }
            }
        }
    }

    class RemoveEffects implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            returnToOrignColor();
            coloring();
            t2.stop();
            t1.start();
        }
    }

    class SaveAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            ObjectOutputStream oos = null;
            File f = new File("e:\\");
            String path = new String();
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(f);
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getPath();
            } else {
                return;
            }
            try {
                returnToOrignColor();
                coloring();
                FileOutputStream fos = new FileOutputStream(path + ".BGsv");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(g1);
                oos.writeObject(sizeX);
                oos.writeObject(sizeY);
                oos.writeObject(numberOfColors);
                oos.writeObject(delay);
                oos.writeObject(duration);
                oos.writeObject(x);
                oos.writeObject(y);
                oos.writeObject(scoure);
                oos.writeObject(sec);
                oos.writeObject(min);
                oos.writeObject(hour);
                JOptionPane.showMessageDialog(null, "game saved successfully",
                        "Done", JOptionPane.INFORMATION_MESSAGE);

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

    class NewGameAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new GameFram(sizeX, sizeY, numberOfColors, x, y, delay, duration);

        }

    }

    class UndoBtnAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    g1.a[i][j].color = tmp[i][j].color;
                }
            }
            scoure = tmpscoure;
            scorelbl.setText(String.valueOf(scoure));
            returnToOrignColor();
            coloring();
            Undobtn.setEnabled(false);
        }
    }

    class CounterAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (sec >= 59) {
                min += 1;
                sec = 0;
            } else {
                sec += 1;
            }
            if (min > 59) {
                hour += 1;
                min = 0;
            }
            counterLbl.setText(String.format("%02d", hour) + ":"
                    + String.format("%02d", min) + ":"
                    + String.format("%02d", sec));
        }
    }

    class LoadTopScores implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            TopScoreReader tsr = new TopScoreReader();
            TopScore ts = new TopScore();
            ts = tsr.returnScore();
            ts.viewScores();
        }

    }
}
