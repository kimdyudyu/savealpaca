package com.save_alpaca.view;

import com.save_alpaca.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MainButtons extends JFrame{
    MainController mainController = new MainController();
    JButton game1;
    JButton game2;
    private JPanel gamePanel1;
    private JPanel gamePanel2;

    public MainButtons() {

   	 	URL iconURL = getClass().getClassLoader().getResource("buttonimages/mini1.png");
        ImageIcon icon = new ImageIcon(iconURL); //현재 이미지 불러오기
        Image img = icon.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon = new ImageIcon(img.getScaledInstance(120, 90, java.awt.Image.SCALE_SMOOTH));
        game1 = new JButton(icon);

        game1.setLocation(940,30);
        game1.setSize(120,90);
        game1.setBackground(new Color(255,0,0,0));
        game1.setBorderPainted(false); // 버튼 테두리 제거
        game1.setContentAreaFilled(false); //버튼 내용영역 채우기 않함
        game1.setFocusPainted(false); //포커스표시설정 해제

        gamePanel1 = new JPanel();
        gamePanel1.setLocation(1050,30);
        gamePanel1.setSize(120,100);
        gamePanel1.setOpaque(false); //배경 제거
        gamePanel1.setBackground(new Color(0,0,0,0));

        
   	 	URL icon2URL = getClass().getClassLoader().getResource("buttonimages/mini2.png");
        ImageIcon icon2 = new ImageIcon(icon2URL); //현재 이미지 불러오기
        Image img2 = icon2.getImage() ;   //바꿀이미지에 현재 이미지를 넣기
        icon2 = new ImageIcon(img2.getScaledInstance(120, 90,  java.awt.Image.SCALE_SMOOTH));

        game2 = new JButton(icon2);

        game2.setLocation(940,120);
        game2.setSize(120,90);
        game2.setBorderPainted(false);
        game2.setContentAreaFilled(false);
        game2.setFocusPainted(false); //포커스표시설정 해제

        gamePanel2 = new JPanel();
        gamePanel2.setLocation(1050,120);
        gamePanel2.setSize(120,100);
        gamePanel2.setOpaque(false); //배경 제거
        gamePanel2.setBackground(new Color(0,0,0,0));

        gamePanel1.add(game1); //미니게임 1
        gamePanel2.add(game2); //미니게임 2

        game1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mainController.mainScreen(1);
            }
        });

        game2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mainController.mainScreen(2);
            }
        });
    }

    public JPanel getGamePanel1() {
        return gamePanel1;
    }

    public void setGamePanel1(JPanel gamePanel1) {
        this.gamePanel1 = gamePanel1;
    }

    public JPanel getGamePanel2() {
        return gamePanel2;
    }

    public void setGamePanel2(JPanel gamePanel2) {
        this.gamePanel2 = gamePanel2;
    }
}