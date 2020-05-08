package com.save_alpaca.view.game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.save_alpaca.controller.MainController;
import com.save_alpaca.view.Sound;

public class End {
	
	MainController mc = new MainController();
  
	
	public void Timer3(long result) {
        JFrame frame = new JFrame();
        frame.setTitle(" end ");
        frame.setBounds(620,200,615,630);
        frame.setResizable(false);

        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBounds(0,0,600,600);

        JLabel lbl = new JLabel("        " + result);
        lbl.setBounds(280, 139, 230, 40);
        lbl.setForeground(Color.pink);
        lbl.setFont(new Font("굴림",Font.BOLD,35));


        JLabel lbl2 = new JLabel();

        int num = (int)result;
        if(num > 0 && num <= 15) {
            lbl2 = new JLabel("Win!!");
            System.out.println(" 올~ 성공추키팡키!");
            mc.gameHungerResult(10);
            

        }else if(num > 15){
            lbl2 = new JLabel("Fail");
            System.out.println(" 실패지룽 ^ㅡㅡ^");

        }
        lbl2.setForeground(Color.pink);
        lbl2.setBounds(330, 240, 260, 40);
        lbl2.setFont(new Font("굴림",Font.BOLD,35));

        pan.add(lbl);
        pan.add(lbl2);

        URL game1ImageIURL = getClass().getClassLoader().getResource("MiniGame_01_images/6.png");
        JLabel game1Image = new JLabel(new ImageIcon(game1ImageIURL));
        game1Image.setSize(600,600);
        pan.add(game1Image);
        game1Image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                
              //미니게임1 종료 후 bgm 시작
                Sound.bgmThread.interrupt();
                URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
                Sound mains = new Sound(mainsURL,131000);
                Sound.bgmThread = new Thread(mains);
                Sound.bgmThread.start();
            }
        });


        
        frame.add(pan);
        frame.setVisible(true);
        
        
        
        
    }
    public End() {}
    

}