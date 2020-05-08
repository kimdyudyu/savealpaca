package com.save_alpaca.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.save_alpaca.controller.MainController;

public class CreateAlpaca extends JFrame {

    private static final long serialVersionUID = 9214831313090474843L;
    MainController mainController = new MainController();

    //로그인 화면
    public CreateAlpaca() {
       super("Save Alpaca Login");
       
       //main bgm 시작
       

       URL mainsURL = getClass().getClassLoader().getResource("mainbgm.wav");
       Sound mains = new Sound(mainsURL,131000); 
       //Sound mains = new Sound(,131000);
       Sound.bgmThread = new Thread(mains);
       Sound.bgmThread.start();
       
        JFrame main = new JFrame();
        main.setSize(600, 600);
        main.setBounds(630,200,600,600);
        setLocationRelativeTo(null);
        main.setLayout(null);


        
        
        URL imageURL = getClass().getClassLoader().getResource("loginimages/login.png");
        
        JLabel Image = new JLabel(new ImageIcon(imageURL));
        Image.setSize(600,600);
        main.add(Image);

        //ALPACA NAME:
        JLabel uname = new JLabel("    USER   NAME    ");
        uname.setLocation(120,150);
        uname.setSize(150,60);
        Border border = BorderFactory.createLineBorder(Color.GRAY,3);
        uname.setBorder(border);

        //폰트
        Font unamefont = new Font("필기",Font.BOLD,16);
        uname.setFont(unamefont);

        //USER ID:
        JLabel aname = new JLabel("   ALPACA NAME   ");
        aname.setLocation(120,230);
        aname.setSize(150,50);
        Border border2 = BorderFactory.createLineBorder(Color.GRAY,3);
        aname.setBorder(border2);

        //폰트
        Font font2 = new Font("필기",Font.BOLD,16);
        aname.setFont(font2);

        // 화면에 글을 입력할 수 있는 컴포넌트 --> ALPACA NAME
        JTextField usertextField = new JTextField(20);
        usertextField.setOpaque(true);
        usertextField.setLocation(283,163);
        usertextField.setSize(160,36);

        //폰트
        Font font3 = new Font("필기",Font.BOLD,20);

        usertextField.setFont(font3);

        //화면에 글을 입력할 수 있는 컴포넌트 --> USER ID
        JTextField alpacatextField = new JTextField(20);
        alpacatextField.setOpaque(true);
        alpacatextField.setLocation(283,247);
        alpacatextField.setSize(160,36);

        //폰트
        Font font4 = new Font("필기",Font.BOLD,20);
        alpacatextField.setFont(font4);

        //로그인 버튼
        JButton loginbtn = new JButton();
        loginbtn.setLocation(255,326);
        loginbtn.setSize(80,40);
        loginbtn.setBackground(new Color(0,0,0,0));
        loginbtn.setBorderPainted(false);
        loginbtn.setOpaque(false);


        main.add(uname);
        main.add(aname);
        main.add(usertextField);
        main.add(alpacatextField);
        main.add(loginbtn);
        
        // SaveAlpaca와 연동
        loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usertextFieldValue = usertextField.getText();   //유저이름 받아오기
                String alpacatextFieldValue = alpacatextField.getText();   //알파카이름 받아오기
                mainController.createAlpaca(usertextFieldValue, alpacatextFieldValue);
                main.dispose();
            }
        });

        
        main.setResizable(false);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}