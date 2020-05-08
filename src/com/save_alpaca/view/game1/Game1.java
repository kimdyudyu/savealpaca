package com.save_alpaca.view.game1;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.save_alpaca.view.Sound;

public class Game1 extends JFrame{
    Container c = getContentPane();
    private JLabel[] gameLabels = new JLabel[20];
    private int nextPressed = 0;
    private long result = 0L;
    
    // 생성되는 숫자 뒤에 들어갈 배경.
    URL numIURL = getClass().getClassLoader().getResource("MiniGame_01_images/1.png");
    JLabel numI = new JLabel(new ImageIcon(numIURL));

    public Game1() {
    	
        super("game 중");
        long start =  System.currentTimeMillis();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        for(int i=0; i<gameLabels.length; i++) {
        	String numstr = Integer.toString(i);
        	URL gameLabelsURL = getClass().getClassLoader().getResource("MiniGame_01_images/1.png");
            gameLabels[i] = new JLabel(numstr,new ImageIcon(gameLabelsURL),JLabel.CENTER);
            gameLabels[i].setVerticalTextPosition(0);
            gameLabels[i].setHorizontalTextPosition(0);
            gameLabels[i].setFont(new Font("고딕",Font.BOLD,22));
            gameLabels[i].setLayout(null);
            gameLabels[i].setForeground(Color.black);

            c.add(gameLabels[i]);

            gameLabels[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    JLabel la = (JLabel)e.getSource();
                    if(Integer.parseInt(la.getText()) == nextPressed) {
                        nextPressed++;
                        if(nextPressed == 21) {

                        }else {
                            la.setVisible(false);
                        }
                        if(nextPressed == 20) {
                            long end = System.currentTimeMillis();
                            result = ((end-start)/1000);
                            System.out.println("실행시간 : " + result);

                            End tm2 = new End();
                            tm2.Timer3(result);

                            dispose();
                        }
                    }
                }
            });
        }
        
        URL gameBackgroundURL = getClass().getClassLoader().getResource("MiniGame_01_images/3.png");
        JLabel gameBackground = new JLabel(new ImageIcon(gameBackgroundURL));
        gameBackground.setSize(600,600);

        this.add(gameBackground);
        this.setSize(615,650);
        this.setVisible(true);
        this.setLocation(630, 200);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

     // 미니게임1 게임창 X버튼 종료 이벤트 
        this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
               Sound.bgmThread.interrupt();
               if(Sound.bgmThread.isAlive()) {
                  Sound.bgmThread.interrupt();
               }
               URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
               Sound mains = new Sound(mainsURL,131000);
               Sound.bgmThread = new Thread(mains);
               Sound.bgmThread.start();
            }
         });  
        
        configure();
    }

    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;

    }

    private void configure() {
        for(int i=0; i<gameLabels.length; i++) {
            gameLabels[i].setSize(45,45);	
            int x = (int)(Math.random()*470)+40;
            int y = (int)(Math.random()*310)+70;
            gameLabels[i].setLayout(null);
            gameLabels[i].setLocation(x,y);
            gameLabels[i].setVisible(true);
            

        }
    }

}