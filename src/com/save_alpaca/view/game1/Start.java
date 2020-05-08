package com.save_alpaca.view.game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.save_alpaca.view.Sound;

public class Start {

    public void StartMain() {
        JFrame frame = new JFrame();
        
        
        
      //미니게임1 bgm 시작
        Sound.bgmThread.interrupt();
        URL mg1sURL = getClass().getClassLoader().getResource("mg1bgm.wav");
        Sound mg1s = new Sound(mg1sURL,84000);
        Sound.bgmThread = new Thread(mg1s);
        Sound.bgmThread.start();
        

        frame.setTitle(" start ");
        frame.setBounds(630,200,615,630);
        frame.setResizable(false);
        

        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBounds(0,0,600,600);
        

        JLabel lbl = new JLabel("15초 안에 못 끝내면 점수 읍다잉?ㅋ ");
        lbl.setBounds(180, 450, 300, 30);
        lbl.setForeground(Color.pink);
        lbl.setFont(new Font("굴림",Font.BOLD,15));

        
        URL gameStartURL =  getClass().getClassLoader().getResource("MiniGame_01_images/2.png");
        JLabel gameStart = new JLabel(new ImageIcon(gameStartURL));
        gameStart.setSize(600,600);
        pan.add(gameStart);
        gameStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Game1 ga = new Game1();
            }

        });
        pan.add(lbl);
        pan.add(gameStart);

        frame.add(pan);
        frame.setVisible(true);
        
     // 미니게임1 게임창 X버튼 종료 이벤트
        frame.addWindowListener(new WindowAdapter() { 
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
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}