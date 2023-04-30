import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.Timer;
import java.util.*;

public class JunglePuzzle
 	{
	static AudioInputStream music;
	static Clip sPlayer,clicksound ;
    static int i, j, c, num, click1, prevc, win,C,min,sec,pass,click;
    static JLabel bg,timecount,cl;
    static String dsec,dmin;
    static JButton[] Card;
    static JButton mainmenuButton;
    static ImageIcon[] image,ht;
    static Timer delay,tc;
    static boolean songenable = true;
    static ImageIcon sound,ima,icon,img,mainmenu,soundIG1,soundIG,sound1,next,next1,quit1,quitIG,htp,Ht;
    static JFrame f;
    static JToggleButton soundBGButton = new JToggleButton();
    static DecimalFormat dFormat = new DecimalFormat("00"); //สร้างรูปแบบของตัวหนังสือเพื่อให้แสดงในรูป 00 เช่น ถ้าใส่เลข 1 จะแสดงเป็น 01
    static ActionListener DelayListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sec++;
            dsec = dFormat.format(sec);
            dmin = dFormat.format(min);
            timecount.setText("         "+dmin + " : " + dsec);
            if (sec == 59){
                sec = -1;
                min++;
            }
            if (min == 59){

            }
        }
    };


	public JunglePuzzle(){
		JFrame window = new JFrame("Jungle Puzzle");
		if (songenable)	try {
			music = AudioSystem.getAudioInputStream(new File("image\\jpSound1.wav"));
			sPlayer = AudioSystem.getClip();
			sPlayer.open(music);
			sPlayer.start();
			sPlayer.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
            e.printStackTrace();
        }
        songenable = true; //เซ็ตเป็น True เพื่อรองรับการโดน set ค่าเป็น False จาก How to play
        img = new ImageIcon("image\\bg2.png");
        image = new ImageIcon[9];
        ht = new ImageIcon[2];
        image[0] = new ImageIcon("image\\backc.png");        
        image[1] = new ImageIcon("image\\ant.png");
        image[2] = new ImageIcon("image\\bear.png");
        image[3] = new ImageIcon("image\\bird.png");
        image[4] = new ImageIcon("image\\cat.png");
        image[5] = new ImageIcon("image\\lion.png");
        image[6] = new ImageIcon("image\\monkey.png");
        image[7] = new ImageIcon("image\\panda.png");
        image[8] = new ImageIcon("image\\pig.png");
        sound = new ImageIcon("image\\sound.png");
        ima = new ImageIcon("image\\bg.png");
        icon = new ImageIcon("image\\icon1.png");
        ImageIcon start1 = new ImageIcon("image\\start1.png");
        next = new ImageIcon("image\\next.png");
        next1 = new ImageIcon("image\\next1.png");
        quit1 = new ImageIcon("image\\clickquit1.png");
        quitIG = new ImageIcon("image\\clickquit2.png");
        soundIG = new ImageIcon("image\\soundingame.png");
        soundIG1 = new ImageIcon("image\\clicksound.png");
        Ht = new ImageIcon("image\\howto1.png");
        ht[0] = new ImageIcon("image\\howto2.png");
        ht[1] = new ImageIcon("image\\howto3.png");
		 JButton buttonStart = new JButton(new ImageIcon("image\\start2.png"));
		 //สร้างตัวแปรรูปทั้งหมดเพื่อนำไปใช้ตลอดทั้ง Class เพราะไม่สามารถที่จะแอดใน static methode อื่นๆได้
	 	buttonStart.setPressedIcon(start1);
		buttonStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event){
				window.dispose();
				Game();
				sPlayer.stop();
				soundBGButton.setSelected(songenable);
				try {
		         	music = AudioSystem.getAudioInputStream(new File("image\\soundClick03.wav"));
		         	clicksound = AudioSystem.getClip();
		         	clicksound.open(music);
		         	clicksound.start();
			} catch (Exception e) {}
				if (soundBGButton.isSelected())
				try {
		         	music = AudioSystem.getAudioInputStream(new File("image\\jpSound1.wav"));
					sPlayer = AudioSystem.getClip();
					sPlayer.open(music);
					sPlayer.start();
					sPlayer.loop(Clip.LOOP_CONTINUOUSLY);
					soundBGButton.setSelected(false);
			} catch (Exception e) {}
				else {
					soundBGButton.setSelected(true);
				}
			}
		});			 
		buttonStart.setBounds(880,350,350,110);
        mainmenu = new ImageIcon("image\\clickquit.png");
		JButton buttonHowTo = new JButton(new ImageIcon("image\\howtoplay.png"));
		ImageIcon htp1 = new ImageIcon("image\\clickhowtoplay.png");
		buttonHowTo.setPressedIcon(htp1);
		buttonHowTo.setBounds(880,500,350,110);
		buttonHowTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
		         	music = AudioSystem.getAudioInputStream(new File("image\\soundClick03.wav"));
		         	clicksound = AudioSystem.getClip();
		         	clicksound.open(music);
		         	clicksound.start();
			} catch (Exception y) {}
				window.dispose();
				htp();
			}
		});
		JButton buttonExit = new JButton(new ImageIcon("image\\quit.png"));
		buttonExit.setBounds(880,650,350,110);
		buttonExit.setPressedIcon(mainmenu);
		buttonExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try {
		         	music = AudioSystem.getAudioInputStream(new File("image\\soundClick03.wav"));
		         	clicksound = AudioSystem.getClip();
		         	clicksound.open(music);
		         	clicksound.start();
			} catch (Exception e) {}
				System.exit(0);				
			}
		});
        soundBGButton = new JToggleButton();
        soundBGButton.setIcon(sound);
        soundBGButton.setBounds(980,0,300,90);
        soundBGButton.setPressedIcon(soundIG1);
        soundBGButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
			try {
	         	music = AudioSystem.getAudioInputStream(new File("image\\soundClick03.wav"));
				clicksound = AudioSystem.getClip();
				clicksound.open(music);
				clicksound.start();
		} catch (Exception e) {}
            if (soundBGButton.isSelected()) {
            	songenable = false;
                sPlayer.stop();
            }
            else {
            	songenable = true;
                sPlayer.start();
                sPlayer.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }});
		
		JLabel logo = new JLabel(new ImageIcon("image\\logo1resize.png"));
		logo.setBounds(-50,50,1000,1000);
		window.setSize(1280, 1000);
		window.setResizable(false);
		setcenter(window);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(icon.getImage());						
		window.add(soundBGButton);
		window.add(buttonStart);
		window.add(buttonExit);
		window.add(buttonHowTo);
		window.add(logo);
		setbg(window);
	    img = new ImageIcon("image\\bg.jpg");
	    htp = new ImageIcon("image\\howtobg.png");
		window.setVisible(true);
        delay = new Timer(1000, new TimerListener());
	}
		
    public static void Game() {
        f = new JFrame("Jungle Puzzle");
        f.setSize(1280, 1000);
        f.setLayout(null);
        f.setIconImage(icon.getImage());
        f.setResizable(false);
        timecount = new JLabel("         00 : 00");
        JLabel time = new JLabel("Time");
        JLabel ge = new JLabel("", ima, JLabel.CENTER);
        JPanel p = new JPanel();
        time.setBounds(35,20,30,20);
        timecount.setBounds(5,45,70,20);
        ge.setBounds(0,0,100,100);
        p.setBounds(1100,50,100,100);
        p.setLayout(null);
        p.add(time);
        p.add(timecount);
        p.add(ge);
        f.add(p);
        setcenter(f);
        small_lv(f);
        setbg(f);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
       
    public static void small_lv(JFrame f) {
    	soundBGButton.setIcon(soundIG1);
        soundBGButton.setBounds(10,10,250,90);
        soundBGButton.setPressedIcon(soundIG);
        mainmenuButton = new JButton(quitIG);		
        mainmenuButton.setBounds(1000,860,250,90);
		mainmenuButton.setPressedIcon(quit1);
        mainmenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try {
		         	music = AudioSystem.getAudioInputStream(new File("soundClick03.wav"));
		         	clicksound = AudioSystem.getClip();
		         	clicksound.open(music);
		         	clicksound.start();
			} catch (Exception y) {}
				songenable = true;
                tc.stop();
				min = 0;
                sec = 0;
                sPlayer.stop();
                new JunglePuzzle();                
                f.dispose();
            }
        });
        
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (i = 0; i < 2; i++) {
            for (j = 1; j <= 8; j++)
                count.add(j);
        }
        Collections.shuffle(count);
        int h = 280;
        int w = 10;
        c = 0;
        win = 0;
        Card = new JButton[17];
        click = 0;
        for (i = 1; i < 5; i++) {
            for (j = 1; j < 5; j++) {
                if (c < 17) {
                    Card[c] = new JButton(image[0]);
                    Card[c].setBounds(h, w, 150, 215);
                    h = h + 180;
                    num = 0;
                    prevc = 1;
                    Card[c].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            for (c = 0; c < 17; c++) {
                                if (e.getSource() == Card[c]) {
                                    if (click == 0) {
                                		try {
                                         	music = AudioSystem.getAudioInputStream(new File("MouseClick .wav"));
                            				clicksound = AudioSystem.getClip();
                            				clicksound.open(music);
                            				clicksound.start();
                                		} catch (Exception y) {}
                                        click++;
                                        Card[c].setIcon(image[count.get(c)]);
                                        num = count.get(c);
                                        prevc = c;
                                    } else if (prevc == c);
                                    else if (click == 1 && num == count.get(c)) {
                                        Card[c].setIcon(image[count.get(c)]);
                                        Card[c].setEnabled(false);
                                        Card[prevc].setEnabled(false);
                                		try {
                                         	music = AudioSystem.getAudioInputStream(new File("Rattle.wav"));
                                         	clicksound = AudioSystem.getClip();
                                         	clicksound.open(music);
                                         	clicksound.start();
                                		} catch (Exception y) {}
                                        click = 0;
                                        win++;
                                        if (win == 8) {
                                            tc.stop();
                                            JFrame win = new JFrame("Card Game");
                                            win.setIconImage(icon.getImage());
                                            win.setSize(280,200);
                                            win.setResizable(false);
                                            JPanel panelwin = new JPanel();
                                            panelwin.setBounds(0,0,300,200);
                                            panelwin.setLayout(null);
                                            JLabel wintime = new JLabel(""+dmin+" : "+dsec);
                                            JLabel text = new JLabel("Congratulations !!");
                                            JButton next = new JButton(" Main menu ");
                                            JButton agian = new JButton(" Try Again");
                                            text.setBounds(85,22,150,20);
                                            wintime.setBounds(110,50,150,20);
                                            agian.setBounds(60, 90, 150, 20);
                                            next.setBounds(60,130, 150, 20);
                                            next.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    sPlayer.stop();
                                                    min = 0;
                                                    sec = 0;
                                    				songenable = true;
                                                	win.dispose();
                                                    f.dispose();
                                                    new JunglePuzzle();
                                                }
                                            });
                                            agian.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    min = 0;
                                                    sec = 0;
                                                    win.dispose();
                                                    Game();
                                                    f.dispose();
                                                }
                                            });
                                            panelwin.add(agian);
                                            panelwin.add(next);
                                            panelwin.add(text);
                                            panelwin.add(wintime);
                                            win.add(panelwin);
                                            setcenter(win);
                                            win.setVisible(true);
                                            win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        }
                                    } else if (click == 1){
                                		try {
                                         	music = AudioSystem.getAudioInputStream(new File("MouseClick .wav"));
                            				clicksound = AudioSystem.getClip();
                            				clicksound.open(music);
                            				clicksound.start();
                                		} catch (Exception y) {}
                                        Card[c].setIcon(image[count.get(c)]);
                                        click++;
                                        C = c;
                                        delay.start();
                                    }
                                }
                            }
                        }
                    });
                    f.add(Card[c]);
                    c++;
                }
            }
            w = w + 240;
            h = 280;
        }
        tc = new Timer(1000, DelayListener);
        tc.start();
        f.add(soundBGButton);
        f.add(mainmenuButton);
    }

    public static void htp() {
    	JFrame HTP = new JFrame("Jungle Puzzle");
    	HTP.setSize(1280,1000);
    	img = htp;
    	setcenter(HTP);
    	JButton nht = new JButton();
    	nht.setBounds(1000,10,250,90);
    	nht.setIcon(next);
    	nht.setPressedIcon(next1);
    	c = 0;
    	JLabel inhtp = new JLabel("",Ht,JLabel.CENTER);
    	nht.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent event) {
				try {
		         	music = AudioSystem.getAudioInputStream(new File("soundClick03.wav"));
		         	clicksound = AudioSystem.getClip();
		         	clicksound.open(music);
		         	clicksound.start();
			} catch (Exception y) {}
    			if (c == 2) 
    			{
    			songenable = false; // set เป็น False เพื่อไม่ให้หน้า mainmenu เล่นเพลงซ้ำ
                new JunglePuzzle();                
                HTP.dispose();
                }
    			inhtp.setIcon(ht[c]);
    	    	c++;
    		}
    	});
    	inhtp.setBounds(40,200,1169,710);
    	HTP.add(nht);
    	HTP.add(inhtp);
    	setbg(HTP);
    	HTP.setIconImage(icon.getImage());
    	HTP.setResizable(false);
    	HTP.setVisible(true);
    	HTP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public static void setcenter(JFrame f){
        Dimension d = f.getToolkit().getScreenSize();
        int screenWidth = d.width;
        int screenHeight = d.height;
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;
        int xPos = centerX - f.getWidth() / 2;
        int yPos = centerY - f.getHeight() / 2;
        f.setLocation(xPos,yPos);
	}

 	public static void setbg(JFrame window) {
        JLabel bg = new JLabel("",img,JLabel.CENTER);
        bg.setBounds(0,0,1280,1000);
        window.add(bg);     
    }
	
    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Card[C].setIcon(image[0]);
            Card[prevc].setIcon(image[0]);
            click = 0;
            delay.stop();
        }
    }
    	
    public static void main(String[] args) {
		new JunglePuzzle();
		}
 	}
